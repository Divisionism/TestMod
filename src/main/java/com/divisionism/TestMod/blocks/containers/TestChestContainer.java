package com.divisionism.TestMod.blocks.containers;

import com.divisionism.TestMod.tileentity.TestChestTileEntity;
import com.divisionism.TestMod.util.Registries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;

import java.util.Objects;

public class TestChestContainer extends Container {

    public final TestChestTileEntity tileEntity;
    private final IWorldPosCallable canInteractWithCallable;

    public TestChestContainer(final int windowId, final PlayerInventory playerInventory, final TestChestTileEntity tileEntity) {
        super(Registries.TEST_CHEST_CONTAINER.get(), windowId);
        this.tileEntity = tileEntity;
        this.canInteractWithCallable = IWorldPosCallable.of(tileEntity.getWorld(), tileEntity.getPos());

        //Main Inventory
        int startX = 8;
        int startY = 18;
        int SlotSizePlus2 = 18;
        for (int row = 0; row < 4; row++) {
            for (int column =0; column < 9; column++) {
                this.addSlot(new Slot(tileEntity, (row * 9) + column, startX + (column * SlotSizePlus2),
                        startY + (row * SlotSizePlus2)));
            }
        }

        //Player Inventory
        int startPlayerInvY = startY * 5 + 12;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 9; column++) {
                this.addSlot(new Slot(playerInventory, 9 + (row * 9) + column, startX + (column * SlotSizePlus2),
                        startPlayerInvY + (row * SlotSizePlus2)));
            }
        }

        //Hotbar
        int hotbarY = startPlayerInvY + (startPlayerInvY / 2) + 7;
        for (int column = 9; column < 9; column++) {
            this.addSlot(new Slot(playerInventory, column, startX + (column * SlotSizePlus2), hotbarY));
        }
    }

    public static TestChestTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null");
        Objects.requireNonNull(data, "Data cannot be null");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof TestChestTileEntity) {
            return (TestChestTileEntity)tileAtPos;
        }
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

    public TestChestContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) {
        this(windowId, playerInventory, getTileEntity(playerInventory, data));
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(canInteractWithCallable, playerIn, Registries.TEST_CHEST.get());
    }

    public ItemStack transferStackInSlot(PlayerEntity player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index < 36) {
                if (!this.mergeItemStack(itemstack1, 36, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, 0, 36, false)) {
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }
        return itemstack;
    }
}

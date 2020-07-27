package com.divisionism.TestMod.util;

public interface IModFluidContainer {

    int maxCap();
    int currentFill();
    boolean canReceive();
    boolean canOutput();
    int fillRate();
    int flowRate();
}

package com.divisionism.TestMod.util;

public enum Fluids {

    NONE(0),
    WATER(1),
    LAVA(2);

    Fluids(final int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }
}

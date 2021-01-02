package com.divisionism.TestMod.util;

public enum RemoteControllerTypes {

    NONE(0),
    DETONATOR(1),
    MACHINE_CONTROLLER(2);

    RemoteControllerTypes(final int id) { this.id = id; }
    private final int id;
    public int getId() { return this.id; }
}

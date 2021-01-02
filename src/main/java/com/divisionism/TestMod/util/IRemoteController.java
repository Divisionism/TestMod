package com.divisionism.TestMod.util;

public interface IRemoteController {

    RemoteControllerTypes type();
    boolean sentSignal();
    void activate();
}

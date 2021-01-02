package com.divisionism.TestMod.util;

public interface IModFluidContainer {

    Fluids fluidContaining();
    void setFluidContaining(Fluids fluid);
    void fill();
    void drain();
    float maxCap();
    float currentFill();
    boolean canReceive();
    boolean canOutput();
    float fillRate();
    float flowRate();
    void setFillRate(float amount);
    void setMaxCap(float amount);
    void setFlowRate(float amount);
    void setCanOutput(boolean bool);
    void setCanReceive(boolean bool);
    void setCurrentFill(float amount);
}

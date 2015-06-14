package com.parking;

public class Slot {
    public String name;
    public int slotPos;
    public Slot(String myCar, int allocated) {
        name=myCar;
        slotPos=allocated;
    }
}

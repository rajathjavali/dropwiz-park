package com.tw.ll.parking.lot;


import com.fasterxml.jackson.annotation.JsonProperty;

public class Slot{

    public String name;

    public int slotPos;

    public Slot(String myCar, int allocated) {
        name=myCar;
        slotPos=allocated;
    }
}

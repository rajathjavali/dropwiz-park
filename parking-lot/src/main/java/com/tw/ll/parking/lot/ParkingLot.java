package com.tw.ll.parking.lot;

import io.dropwizard.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot{
    public int maxSlots;
    public int allocated = 0;
    public int[] available;
    public List<Slot> slots = new ArrayList<Slot>();

    public ParkingLot(int i) {
        maxSlots = i;
        available = new int[i];
    }

    public List<Slot> getFilledSlots() {
        return slots;

    }
    private boolean isDupEntry(String name)
    {
        for(Slot slot: slots)
        {
            if(slot.name.equals(name))
                return true;
        }
        return false;
    }

    public synchronized Slot park(String myCar) {

        if(isDupEntry(myCar))
        {
            throw new RuntimeException("Only one ticket for a car");
        }
        if (allocated < maxSlots) {
            int avail = -1;
            while (available[++avail] == 1) ;
            Slot newSlot = new Slot(myCar, avail);
            available[avail]=1;
            slots.add(newSlot);
            allocated++;
            return newSlot;
        } else {
            throw new RuntimeException("Sorry, cannot park as the lot is full");
        }
    }

    public void unPark(String myCar) {
        for(Slot slot : slots) {
            if (slot.name.equals(myCar)) {
                available[slot.slotPos]=0;
                slots.remove(slot);
                allocated--;
                return;
            }
        }
        throw new RuntimeException("No such car found");
    }
}

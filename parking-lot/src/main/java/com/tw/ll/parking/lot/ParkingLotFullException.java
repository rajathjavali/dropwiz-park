package com.tw.ll.parking.lot;

public class ParkingLotFullException extends RuntimeException {

    public ParkingLotFullException(String s) {
        super(s);
    }

    @Override
    public boolean equals(Object obj) {
        return this.getMessage().equals(((ParkingLotFullException) obj).getMessage());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

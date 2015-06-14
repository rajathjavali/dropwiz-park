package com.tw.ll.parking.lot;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ParkingLotTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void shouldParkInALot() {

        ParkingLot aParkingLot = new ParkingLot(10);

        Slot myCarSlot = aParkingLot.park("myCar");

        List<Slot> filledSlots = aParkingLot.getFilledSlots();
        assertEquals(1, filledSlots.size());
        assertEquals(myCarSlot, filledSlots.get(0));
    }

    @Test
    public void shouldNotParkInALotWhenLotIsFull() {

        ParkingLot aParkingLot = new ParkingLot(2);

        Slot myCarSlot = aParkingLot.park("myCar");
        Slot anotherCarSlot = aParkingLot.park("anotherCar");
        List<Slot> filledSlots = aParkingLot.getFilledSlots();

        assertEquals(2, filledSlots.size());
        assertTrue(filledSlots.contains(myCarSlot));
        assertTrue(filledSlots.contains(anotherCarSlot));
        try {
            Slot unparked = aParkingLot.park("unparked");
        } catch (RuntimeException e) {
            assertNotNull(e);
            assertEquals("Sorry, cannot park as the lot is full", e.getMessage());
        }

    }

    @Test
    public void shouldUnPark() {

        ParkingLot aParkingLot = new ParkingLot(2);

        Slot myCarSlot = aParkingLot.park("myCar");
        List<Slot> filledSlots = aParkingLot.getFilledSlots();
        assertTrue(filledSlots.contains(myCarSlot));
        try {
            aParkingLot.unPark("myCar");
        } catch (RuntimeException e) {
            assertNull(e);
        }
    }

    @Test
    public void shouldNotUnPark() {
        ParkingLot aParkingLot = new ParkingLot(2);

        Slot myCarSlot = aParkingLot.park("myCar");
        Slot anotherCarSlot = aParkingLot.park("anotherCar");
        List<Slot> filledSlots = aParkingLot.getFilledSlots();

        assertFalse(filledSlots.contains("someOtherCar"));
        try {
            aParkingLot.unPark("someOtherCar");
        } catch (RuntimeException e) {
            assertNotNull(e);
            assertEquals("No such car found", e.getMessage());
        }
    }

    @Test
    public void shouldBeFullIfAllSlotsAreOccupied() {
        ParkingLot aParkingLot = new ParkingLot(2);

        Slot myCarSlot = aParkingLot.park("myCar");
        Slot anotherCarSlot = aParkingLot.park("anotherCar");

        List<Slot> filledSlots = aParkingLot.getFilledSlots();
        assertEquals(2, filledSlots.size());
    }

    @Test
    public void shouldAllowParkingWhenASlotIsAvailableDueToUnParkOfCar() {
        ParkingLot aParkingLot = new ParkingLot(2);

        Slot myCarSlot = aParkingLot.park("myCar");
        Slot anotherCarSlot = aParkingLot.park("anotherCar");
        List<Slot> filledSlots = aParkingLot.getFilledSlots();

        assertTrue(filledSlots.contains(myCarSlot));
        try {
            aParkingLot.unPark("myCar");
        } catch (RuntimeException e) {
            assertNull(e);
        }

        assertEquals(1, filledSlots.size());

        try {
            aParkingLot.park("MyNewCar");
        } catch (RuntimeException e) {
            assertNull(e);
        }
    }

    @Test
    public void shouldNotAllocateAnAllocatedSlotWhenACarIsParked() {
        ParkingLot aParkingLot = new ParkingLot(2);

        Slot myCarSlot = aParkingLot.park("myCar");
        Slot anotherCarSlot = aParkingLot.park("anotherCar");
        assertNotEquals(myCarSlot.slotPos, anotherCarSlot.slotPos);
    }

    @Test
    public void shouldNotAllowSameCarTwoTickets() {
        ParkingLot aParkingLot = new ParkingLot(2);
        String myCar = new String("myCar");
        Slot myCarSlot = aParkingLot.park(myCar);
        String myCarAgain = new String("myCar");

        expectedException.expectMessage("Only one ticket for a car");
        Slot anotherCarSlot = aParkingLot.park(myCarAgain);


    }

}

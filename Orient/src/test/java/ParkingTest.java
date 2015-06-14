import com.parking.ParkingLot;
import com.parking.Slot;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParkingTest {


    @Test
    public void shouldParkInALot() {

        ParkingLot aParkingLot = new ParkingLot(10);
        Slot myCarSlot = aParkingLot.park("myCar");
        assertEquals(0,myCarSlot.slotPos);
    }
}

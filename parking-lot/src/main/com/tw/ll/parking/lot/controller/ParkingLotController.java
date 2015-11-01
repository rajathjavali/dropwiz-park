package com.tw.ll.parking.lot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.ll.parking.lot.ParkingLot;
import org.json.simple.JSONObject;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("/lot")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class ParkingLotController {
    private final ParkingLot aParkingLot;
    private final int maxSlots;
    public ParkingLotController(int maxSlots) {
        this.maxSlots=maxSlots;
        aParkingLot = new ParkingLot(maxSlots);
    }
    @PUT
    @Path("/park")
    public Response park(final String body) throws IOException {
        JSONObject carRequest = new ObjectMapper().readValue(body, JSONObject.class);
        JSONObject response = new JSONObject();
        try {
            String name = (String) carRequest.get("name");
            ParkingLot.Slot newSlot = aParkingLot.park(name);

            response.put("slot", newSlot);
            response.put("thread", Thread.currentThread().getId());
            response.put("whichParkingLot", aParkingLot.hashCode());
            return Response.status(200)
                    .entity(response)
                    .build();

        } catch (RuntimeException e) {

            response.put("message", e.getMessage());
            response.put("thread", Thread.currentThread().getId());
            response.put("whichParkingLot", aParkingLot.hashCode());
            return Response.status(400)
                    .entity(response)
                    .build();
        }

    }

    @DELETE
    @Path("/unpark")
    public Response unpark(@FormParam("name") String myCar) {
        try {
            aParkingLot.unPark(myCar);
        } catch (RuntimeException e) {
            return Response.status(400)
                    .entity(e.getMessage())
                    .build();

        }
        return Response.status(200)
                .entity("Your car " + myCar + " was unparked successfully")
                .build();
    }

    @GET
    @Path("/filledSlots")
    public Response filledSlots() {
        List<ParkingLot.Slot> filledSlots = null;
        filledSlots = aParkingLot.getFilledSlots();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("slots", filledSlots);
        return Response.status(200)
                .entity(jsonObject)
                .build();

    }

}

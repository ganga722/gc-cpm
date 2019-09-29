package domain;

import domain.exception.ParkingUnavailableException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CarParkArray implements CarPark {

    private static int TOTAL_SPACES = 10;
    private AtomicInteger ticketSequence = new AtomicInteger(5000);
    private Slot[] slots = new Slot[TOTAL_SPACES];


    public void park(Car car) {
        int availableSlot = getAvailableSlot();
        if (isValidParkingSlot(availableSlot)){
            int ticketId = ticketSequence.getAndIncrement();
            slots[availableSlot] = new Slot(ticketId, car); // add new car to next available slot
        }
    }

    public void unPark(int ticketId) {
        int parkSlot = findSlotWithTicketId(ticketId);
        slots[parkSlot] = null; //sets unparks car slot to null
    }

    public void compact(){
        List<Slot> tempList = new ArrayList<>();
        for(Slot s : slots){
            if (s != null)
                tempList.add(s); // add non null values to the front of the list
        }
        slots = tempList.toArray(new Slot[TOTAL_SPACES]); // convert list back into array and assign to slots
    }

    public void print() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < TOTAL_SPACES;i++){
            sb.append(slots[i] == null? "" : slots[i].getCar().getId()); // ignore null values
            if (i+1 < TOTAL_SPACES)
                sb.append(","); // comma for first 9 elements
        }
        System.out.println(sb.toString());
    }

    private boolean isValidParkingSlot(int availableSlot) {
        return availableSlot >=0 && availableSlot < TOTAL_SPACES; //check within range
    }

    private int getAvailableSlot() {
        for(int i = 0; i < TOTAL_SPACES; i++){
            if (slots[i] == null){
                return i; // free slot
            }
        }
        return -1;
    }

    private int findSlotWithTicketId(int ticketId) {
        for(int i = 0; i < TOTAL_SPACES;i++){
            if (slots[i] != null && slots[i].getTicketId() == ticketId){
                return i; // matching slot
            }
        }
        return -1;
    }

}

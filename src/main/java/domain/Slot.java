package domain;

public class Slot {
    private int ticketId;
    private Car car;

    public Slot(int ticketId, Car car) {
        this.ticketId = ticketId;
        this.car = car;
    }

    public int getTicketId() {
        return ticketId;
    }

    public Car getCar() {
        return car;
    }

}

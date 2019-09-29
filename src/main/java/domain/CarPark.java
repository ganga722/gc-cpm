package domain;

import domain.exception.ParkingUnavailableException;

public interface CarPark {

    void park(Car car) throws ParkingUnavailableException;

    void unPark(int ticketId);

    void compact();

    void print();

}

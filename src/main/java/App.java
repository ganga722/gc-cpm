import domain.Car;
import domain.CarPark;
import domain.CarParkArray;
import domain.exception.ParkingUnavailableException;

import java.util.Scanner;

public class App {
    public static void main(String[] args) throws ParkingUnavailableException {

        CarPark carPark = new CarParkArray();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.next(); //pABC,pXYZ,pEFG,u5000,c

        String[] parkingSteps = input.split(",");
        for (String instruction : parkingSteps){
            char command = instruction.charAt(0);
            String data = instruction.substring(1);
            if (command == 'p'){ //insert car
                carPark.park(new Car(data));
            } else if (command == 'u'){ //unpark car
                carPark.unPark(Integer.parseInt(data));
            } else if (command == 'c'){ //compact
                carPark.compact();
            }
        }

        carPark.print();

    }
}

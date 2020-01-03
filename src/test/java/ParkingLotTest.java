import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author huisheng.jin
 * @version 2019/12/21.
 */
class ParkingLotTest {
    @Test
    void should_park_success_when_parkingLot_is_empty() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car car = new Car();
        String credential = parkingLot.park(car);
        assertThat(credential).isNotNull();
    }

    @Test
    void should_park_success_when_has_parkingSpace() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car());
        String credential = parkingLot.park(new Car());
        assertThat(credential).isNotNull();
    }

    @Test
    void should_park_success_when_has_no_parkingSpace() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Car());
        parkingLot.park(new Car());

        Assertions.assertThrows(RuntimeException.class, () -> parkingLot.park(new Car()), "车位已满");
    }

    @Test
    void should_takeout_success_when_park_lot_has_car() {
        ParkingLot parkingLot = new ParkingLot(2);
        Car parkCar = new Car();
        String credential = parkingLot.park(parkCar);
        Car takeOutCar = parkingLot.takeout(credential);
        assertThat(takeOutCar).isEqualTo(parkCar);
    }

    @Test
    void should_takeout_failure_when_park_lot_takeout_over() {
        ParkingLot parkingLot = new ParkingLot(2);
        String credential = parkingLot.park(new Car());
        parkingLot.takeout(credential);

        Assertions.assertThrows(RuntimeException.class, () -> parkingLot.takeout(credential), "车已取");
    }
}
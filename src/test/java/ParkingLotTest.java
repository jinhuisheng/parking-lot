import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.in;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author huisheng.jin
 * @date 2020/1/3.
 */
public class ParkingLotTest {
    //    @Test
//    void demo() {
//        ParkingLot parkingLot = new ParkingLot(2);
//        String credential = parkingLot.park(new Car());
//        assertThat(credential).isNotNull();
//    }

    private ParkingLot parkingLot;

    @BeforeEach
    void setUp() {
        parkingLot = new ParkingLot(2);
    }

    @Test
    void should_park_success_when_parking_lot_is_empty() {
        String credential = parkingLot.park(new Car());
        assertThat(credential).isNotNull();
    }

    @Test
    void should_park_success_when_parking_lot_has_capacity() {
        parkingLot.park(new Car());
        String credential = parkingLot.park(new Car());
        assertThat(credential).isNotNull();
    }

    @Test
    void should_park_success_when_parking_lot_is_full() {
        parkingLot.park(new Car());
        parkingLot.park(new Car());

        assertThrows(ParkingLotFullException.class, () -> parkingLot.park(new Car()), "车位已满");
    }

    @Test
    void should_take_car_success_by_credential() {
        parkingLot = new ParkingLot(2);
        Car parkingCar = new Car();
        String credential = parkingLot.park(parkingCar);
        Car takeCar = parkingLot.take(credential);

        assertThat(takeCar).isEqualTo(parkingCar);
    }

    @Test
    void should_take_car_failure_by_invalid_credential() {
        Car parkingCar = new Car();
        String credential = parkingLot.park(parkingCar);
        String invalidCredential = "1111";
        assertThat(credential).isNotEqualTo(invalidCredential);

        assertThrows(InvalidCredentialException.class, () -> parkingLot.take(invalidCredential), "无效凭证");
    }

    @Test
    void should_take_car_failure_by_same_credential_take_two_times() {
        Car parkingCar = new Car();
        String credential = parkingLot.park(parkingCar);
        parkingLot.take(credential);

        assertThrows(InvalidCredentialException.class, () -> parkingLot.take(credential), "无效凭证");
    }


}

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
        Boolean result = parkingLot.park();
        assertThat(result).isEqualTo(true);
    }

    @Test
    void should_park_success_when_has_parkingSpace() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park();
        assertThat(parkingLot.park()).isEqualTo(true);
    }

    @Test
    void should_park_success_when_has_no_parkingSpace() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park();
        parkingLot.park();

        Boolean result = parkingLot.park();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void should_takeout_success_when_park_lot_is_empty() {
        ParkingLot parkingLot = new ParkingLot(2);
        Boolean result = parkingLot.takeout();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void should_takeout_failure_when_park_lot_takeout_over() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park();
        parkingLot.takeout();
        Boolean result = parkingLot.takeout();
        assertThat(result).isEqualTo(false);
    }

    @Test
    void should_takeout_success_when_park_lot_has_car() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park();
        assertThat(parkingLot.takeout()).isEqualTo(true);
    }

    @Test
    void should_takeout_success_when_park_lot_is_full_car() {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park();
        parkingLot.park();

        assertThat(parkingLot.takeout()).isEqualTo(true);
    }

}
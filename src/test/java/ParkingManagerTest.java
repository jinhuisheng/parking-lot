import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author huisheng.jin
 * @date 2020/1/5.
 */
class ParkingManagerTest {

    @Test
    void should_park_success_when_manager_park() {
        ParkingManager parkingManager = new ParkingManager(null, Collections.singletonList(new ParkingLot(2)));
        String credential = parkingManager.park(new Car());
        assertThat(credential).isNotNull();
    }

    @Test
    void should_park_success_by_manager_assign_specific_parking_boy() {
        ParkingBoy parkingBoy1 = new ParkingBoy(Collections.singletonList(new ParkingLot(2)));
        ParkingBoy parkingBoy2 = new ParkingBoy(Collections.singletonList(new ParkingLot(2)));
        ParkingManager parkingManager = new ParkingManager(Arrays.asList(parkingBoy1, parkingBoy2), null);
        String credential = parkingManager.park(new Car());
        assertThat(credential).isNotNull();
    }

    @Test
    void should_init_failure_when_manager_has_no_assigned_parking_boy_and_parking_lot() {
        assertThrows(InvalidManagerException.class, () -> new ParkingManager(null, null), "初始化失败");
    }

    @Test
    void should_park_failure_when_parking_boys_have_no_parking_lot_and_manager_has_no_parking_lot() {
        ParkingBoy parkingBoy1 = new ParkingBoy(Collections.singletonList(new ParkingLot(1)));
        ParkingManager parkingManager = new ParkingManager(Collections.singletonList(parkingBoy1), Collections.singletonList(new ParkingLot(1)));
        parkingManager.park(new Car());
        parkingManager.park(new Car());

        assertThrows(ManagerHasNoParkingSpaceException.class, () -> parkingManager.park(new Car()), "经理停车位已满");
    }
}

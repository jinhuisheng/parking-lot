import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author huisheng.jin
 * @date 2019/12/29.
 */
public class ParkingManagerTest {
    @Test
    void should_park_success_self() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLotList.add(parkingLot);
        ParkingManager parkingManager = new ParkingManager(parkingLotList);
        assertThat(parkingManager.park(new Car())).isNotNull();
    }

//    @Test
//    void should_park_success_by_specific_parkingBoy() {
//        List<ParkingLot> parkingLotList = new ArrayList<>();
//        ParkingLot parkingLot = new ParkingLot(1);
//        parkingLotList.add(parkingLot);
//        new ParkingBoy(parkingLotList);
//
//        ParkingManager parkingManager = new ParkingManager(parkingLotList);
//        assertThat(parkingManager.park(new Car())).isEqualTo(true);
//    }
//
//    @Test
//    void should_park_success_by_specific_multiple_parkingBoys() {
//        List<ParkingLot> parkingLotList = new ArrayList<>();
//        ParkingLot parkingLot = new ParkingLot(1);
//        parkingLotList.add(parkingLot);
//
//        ParkingBoy parkingBoy1 = new ParkingBoy();
//        ParkingBoy parkingBoy2 = new ParkingBoy();
//        List<ParkingBoy> parkingBoyList = new ArrayList<>();
//        parkingBoyList.add(parkingBoy1);
//        parkingBoyList.add(parkingBoy2);
//
//        ParkingManager parkingManager = new ParkingManager(parkingLotList);
//        assertThat(parkingManager.park(parkingBoyList, Constant.SORT_BY_FREE_COUNT)).isEqualTo(true);
//    }

}

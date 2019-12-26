import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author huisheng.jin
 * @version 2019/12/25.
 */
class ParkingBoyTest {
    @Test
    void should_park_car_success_when_parking_lot_not_full() {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));

        assertThat(parkingBoy.park(Constant.SORT_BY_FREE_COUNT)).isEqualTo(true);
    }

    @Test
    void should_park_car_failure_when_parking_lot_is_full() {
        ParkingLot parkingLot = new ParkingLot(2);
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(parkingLot));
        parkingBoy.park(Constant.SORT_BY_FREE_COUNT);
        parkingBoy.park(Constant.SORT_BY_FREE_COUNT);

        Boolean result = parkingBoy.park(Constant.SORT_BY_FREE_COUNT);

        assertThat(result).isEqualTo(false);
    }

    @Test
    void should_get_current_parkingLotState_when_park() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        parkingBoy.park(Constant.SORT_BY_FREE_COUNT);

        List<ParkingLotState> parkingLotStateList = parkingBoy.getParkingLotState();

        assertThat(parkingLotStateList.size()).isEqualTo(1);
        ParkingLotState parkingLotState = parkingLotStateList.get(0);
        assertThat(parkingLotState.getFreeCount()).isEqualTo(1);
    }

    @Test
    void should_park_success_by_free_count() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(4));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        parkingBoy.park(Constant.SORT_BY_FREE_COUNT);

        List<ParkingLotState> parkingLotStateList = parkingBoy.getParkingLotState();

        ParkingLotState parkingLotState1 = parkingLotStateList.get(1);
        assertThat(parkingLotState1.getFreeCount()).isEqualTo(3);

        ParkingLotState parkingLotState2 = parkingLotStateList.get(0);
        assertThat(parkingLotState2.getFreeCount()).isEqualTo(2);
    }


    @Test
    void should_park_success_order_by_sort() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        parkingLotList.add(new ParkingLot(2));
        parkingLotList.add(new ParkingLot(4));
        parkingLotList.add(new ParkingLot(6));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        parkingBoy.park(Constant.SORT_BY_PARKING_LOT_ID);

        List<ParkingLotState> parkingLotStateList1 = parkingBoy.getParkingLotState();
        ParkingLotState parkingLotState1 = parkingLotStateList1.get(0);
        assertThat(parkingLotState1.getFreeCount()).isEqualTo(1);

        parkingBoy.park(Constant.SORT_BY_PARKING_LOT_ID);

        List<ParkingLotState> parkingLotStateList2 = parkingBoy.getParkingLotState();
        ParkingLotState parkingLotState2 = parkingLotStateList2.get(1);
        assertThat(parkingLotState2.getFreeCount()).isEqualTo(3);


        parkingBoy.park(Constant.SORT_BY_PARKING_LOT_ID);

        List<ParkingLotState> parkingLotStateList3 = parkingBoy.getParkingLotState();
        ParkingLotState parkingLotState3 = parkingLotStateList3.get(2);
        assertThat(parkingLotState3.getFreeCount()).isEqualTo(5);

        parkingBoy.park(Constant.SORT_BY_PARKING_LOT_ID);

        List<ParkingLotState> parkingLotStateList4 = parkingBoy.getParkingLotState();
        ParkingLotState parkingLotState4 = parkingLotStateList4.get(0);
        assertThat(parkingLotState4.getFreeCount()).isEqualTo(0);

    }


}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author huisheng.jin
 * @date 2020/1/4.
 */
class ParkingBoyTest {
//    @Test
//    void demo() {
//        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(new ParkingLot(2)));
//        String credential = parkingBoy.park(new Car());
//        assertThat(credential).isNotNull();
//    }

    @Test
    void should_park_success_with_one_parking_lot() {
        ParkingBoy parkingBoy = new ParkingBoy(Collections.singletonList(new ParkingLot(2)));
        String credential = parkingBoy.park(new Car());
        assertThat(credential).isNotNull();
    }

    @Test
    void should_park_success_with_multiple_parking_lots() {
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(new ParkingLot(2), new ParkingLot(2)));
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        String credential = parkingBoy.park(new Car());
        assertThat(credential).isNotNull();
    }

    @Test
    void should_park_failure_when_all_parking_lots_are_full() {
        ParkingBoy parkingBoy = new ParkingBoy(Arrays.asList(new ParkingLot(2), new ParkingLot(2)));
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        assertThrows(AllParkingLotsFullException.class, () -> parkingBoy.park(new Car()), "所有停车场已满");
    }

    @Test
    void should_park_success_order_by_parking_lots_sequence() {
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(2);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBySequenceParkingBoy parkingBoy = new ParkingBySequenceParkingBoy(parkingLotList);

        parkingBoy.park(new Car());
        assertThat(parkingLot1.getFreeCount()).isEqualTo(1);

        parkingBoy.park(new Car());
        assertThat(parkingLot2.getFreeCount()).isEqualTo(1);

        parkingBoy.park(new Car());
        assertThat(parkingLot1.getFreeCount()).isEqualTo(0);

        parkingBoy.park(new Car());
        assertThat(parkingLot2.getFreeCount()).isEqualTo(0);
    }

    @Test
    void should_park_failure_order_by_parking_lots_sequence_skip_full_parking_lot() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBySequenceParkingBoy parkingBoy = new ParkingBySequenceParkingBoy(parkingLotList);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        assertThat(parkingLot2.getFreeCount()).isEqualTo(1);
    }

    @Test
    void should_park_failure_order_by_parking_lots_sequence_when_all_parking_lots_full() {
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(2);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1, parkingLot2);
        ParkingBySequenceParkingBoy parkingBoy = new ParkingBySequenceParkingBoy(parkingLotList);

        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());
        parkingBoy.park(new Car());

        assertThrows(AllParkingLotsFullException.class, () -> parkingBoy.park(new Car()), "所有停车场已满");
    }

    @Test
    void should_park_success_order_by_parking_lots_free_count() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(4);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1, parkingLot2);
        ParkingByFreeCountParkingBoy parkingBoy = new ParkingByFreeCountParkingBoy(parkingLotList);

        parkingBoy.park(new Car());
        assertThat(parkingLot2.getFreeCount()).isEqualTo(3);

        parkingBoy.park(new Car());
        assertThat(parkingLot2.getFreeCount()).isEqualTo(2);
    }

    @Test
    void should_park_success_order_by_parking_lots_free_count_when_free_count_equal_by_sequence() {
        ParkingLot parkingLot1 = new ParkingLot(2);
        ParkingLot parkingLot2 = new ParkingLot(4);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1, parkingLot2);
        ParkingByFreeCountParkingBoy parkingBoy = new ParkingByFreeCountParkingBoy(parkingLotList);

        parkingBoy.park(new Car());
        assertThat(parkingLot2.getFreeCount()).isEqualTo(3);

        parkingBoy.park(new Car());
        assertThat(parkingLot2.getFreeCount()).isEqualTo(2);

        parkingBoy.park(new Car());
        assertThat(parkingLot1.getFreeCount()).isEqualTo(1);
    }

    @Test
    void should_park_failure_order_by_parking_lots_free_count_when_all_parking_lots_full() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1, parkingLot2);
        ParkingByFreeCountParkingBoy parkingBoy = new ParkingByFreeCountParkingBoy(parkingLotList);

        parkingBoy.park(new Car());
        assertThat(parkingLot2.getFreeCount()).isEqualTo(1);

        parkingBoy.park(new Car());
        assertThat(parkingLot1.getFreeCount()).isEqualTo(0);

        parkingBoy.park(new Car());
        assertThat(parkingLot2.getFreeCount()).isEqualTo(0);

        Assertions.assertThrows(AllParkingLotsFullException.class, () -> parkingBoy.park(new Car()), "所有停车场已满");
    }

    @Test
    void should_park_success_order_by_free_rate() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(3);
        ParkingLot parkingLot3 = new ParkingLot(5);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1, parkingLot2, parkingLot3);
        ParkingByFreeRateParkingBoy parkingBoy = new ParkingByFreeRateParkingBoy(parkingLotList);

        parkingBoy.park(new Car());
        assertThat(parkingLot1.getFreeCount()).isEqualTo(0);
        parkingBoy.park(new Car());
        assertThat(parkingLot2.getFreeCount()).isEqualTo(2);
        parkingBoy.park(new Car());
        assertThat(parkingLot3.getFreeCount()).isEqualTo(4);

        parkingBoy.park(new Car());
        assertThat(parkingLot3.getFreeCount()).isEqualTo(3);
    }

    @Test
    void should_park_fail_order_by_free_rate_when_all_parking_lots_full() {
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(2);
        ParkingLot parkingLot3 = new ParkingLot(3);
        List<ParkingLot> parkingLotList = Arrays.asList(parkingLot1, parkingLot2, parkingLot3);
        ParkingByFreeRateParkingBoy parkingBoy = new ParkingByFreeRateParkingBoy(parkingLotList);

        parkingBoy.park(new Car());
        assertThat(parkingLot1.getFreeCount()).isEqualTo(0);
        parkingBoy.park(new Car());
        assertThat(parkingLot2.getFreeCount()).isEqualTo(1);
        parkingBoy.park(new Car());
        assertThat(parkingLot3.getFreeCount()).isEqualTo(2);

        parkingBoy.park(new Car());
        assertThat(parkingLot3.getFreeCount()).isEqualTo(1);

        parkingBoy.park(new Car());
        assertThat(parkingLot2.getFreeCount()).isEqualTo(0);

        parkingBoy.park(new Car());
        assertThat(parkingLot3.getFreeCount()).isEqualTo(0);

        Assertions.assertThrows(AllParkingLotsFullException.class, () -> parkingBoy.park(new Car()), "所有停车场已满");
    }
}

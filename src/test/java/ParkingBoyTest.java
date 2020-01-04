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
public class ParkingBoyTest {
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
    void should_park_success_order_by_parkingLots_sequence() {
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
    void should_park_failure_order_by_parkingLots_sequence_skip_full_parkingLot() {
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
    void should_park_failure_order_by_parkingLots_sequence_when_all_parkingLots_full() {
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
}

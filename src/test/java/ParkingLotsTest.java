import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author huisheng.jin
 * @date 2019/12/29.
 */
class ParkingLotsTest {

    @Test
    void should_return_null_when_parkingLots_all_full() {
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLotList.add(parkingLot);

        ParkingLots parkingLots = new ParkingLots(parkingLotList);
        parkingLot.park(new Car());

        Optional<ParkingLot> parkingLotInTurn = parkingLots.getParkingLotInTurn();
        assertThat(parkingLotInTurn.isPresent()).isEqualTo(false);
    }
}
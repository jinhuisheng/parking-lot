import java.util.List;
import java.util.Optional;

/**
 * @author huisheng.jin
 * @date 2020/1/4.
 */
public class ParkingByFreeRateParkingBoy extends AbstractParkingBoy {
    public ParkingByFreeRateParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Optional<ParkingLot> getFreeParkingLot() {
        return parkingLotList.stream()
                .filter(ParkingLot::hasParkingSpace)
                .min(this::compare);
    }

    private int compare(ParkingLot a, ParkingLot b) {
        float compareResult = b.getFreeRate() - a.getFreeRate();
        if (compareResult > 0) {
            return 1;
        } else if (compareResult == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}

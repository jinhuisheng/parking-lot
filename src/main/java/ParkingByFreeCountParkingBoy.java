import java.util.List;
import java.util.Optional;

/**
 * @author huisheng.jin
 * @date 2020/1/4.
 */
public class ParkingByFreeCountParkingBoy extends AbstractParkingBoy {
    public ParkingByFreeCountParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Optional<ParkingLot> getFreeParkingLot() {
        return parkingLotList.stream()
                .filter(ParkingLot::hasParkingSpace)
                .min((a, b) -> b.getFreeCount() - a.getFreeCount());
    }
}

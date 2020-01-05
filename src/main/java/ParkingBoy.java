import java.util.List;
import java.util.Optional;

/**
 * @author huisheng.jin
 * @date 2020/1/4.
 */
public class ParkingBoy extends AbstractParkingBoy {

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Optional<ParkingLot> getFreeParkingLot() {
        return parkingLotList.stream()
                .filter(ParkingLot::hasParkingSpace)
                .findFirst();
    }

    protected boolean hasParkingSpace() {
        return parkingLotList.stream()
                .anyMatch(ParkingLot::hasParkingSpace);
    }
}

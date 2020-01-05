import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author huisheng.jin
 * @date 2020/1/4.
 */
public class ParkingBySequenceParkingBoy extends AbstractParkingBoy {

    private Integer previousIndex;

    public ParkingBySequenceParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public Optional<ParkingLot> getFreeParkingLot() {
        if (Objects.isNull(previousIndex)) {
            return setPreviousIndexAndGetParkingLot(0);
        }
        for (int i = previousIndex + 1; i < parkingLotList.size(); i++) {
            if (parkingLotList.get(i).hasParkingSpace()) {
                return setPreviousIndexAndGetParkingLot(i);
            }
        }
        for (int i = 0; i < previousIndex + 1; i++) {
            if (parkingLotList.get(i).hasParkingSpace()) {
                return setPreviousIndexAndGetParkingLot(i);
            }
        }
        return Optional.empty();
    }

    private Optional<ParkingLot> setPreviousIndexAndGetParkingLot(int index) {
        ParkingLot parkingLot = parkingLotList.get(index);
        previousIndex = index;
        return Optional.of(parkingLot);
    }

}

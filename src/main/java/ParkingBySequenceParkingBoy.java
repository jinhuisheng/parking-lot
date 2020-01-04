import java.util.List;
import java.util.Objects;

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
    public ParkingLot getFreeParkingLot() {
        return getNextParkingLot();
    }

    private ParkingLot getNextParkingLot() {
        if (Objects.isNull(previousIndex)) {
            return setPreviousIndexAndGetParkingLot(0);
        }
        for (int i = previousIndex + 1; i < parkingLotList.size(); i++) {
            if (parkingLotList.get(i).hasFree()) {
                return setPreviousIndexAndGetParkingLot(i);
            }
        }
        for (int i = 0; i < previousIndex + 1; i++) {
            if (parkingLotList.get(i).hasFree()) {
                return setPreviousIndexAndGetParkingLot(i);
            }
        }
        throw new AllParkingLotsFullException("所有停车场已满");
    }

    private ParkingLot setPreviousIndexAndGetParkingLot(int index) {
        ParkingLot parkingLot = parkingLotList.get(index);
        previousIndex = index;
        return parkingLot;
    }

}

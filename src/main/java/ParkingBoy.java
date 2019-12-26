import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author huisheng.jin
 * @version 2019/12/25.
 */
public class ParkingBoy {
    private List<ParkingLot> parkingLotList;
    private Integer lastParkingLotIndex;

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
        this.lastParkingLotIndex = null;
    }

    public Boolean park(String parkSpecification) {
        ParkingLot parkingLot = getParkingLotBy(parkSpecification);
        return parkingLot.park();
    }

    private ParkingLot getParkingLotBy(String parkSpecification) {
        if (Constant.SORT_BY_PARKING_LOT_ID.equals(parkSpecification)) {
            return getParkingLotInTurn();
        } else {
            return getParkingLotByFreeCount();
        }
    }

    private ParkingLot getParkingLotByFreeCount() {
        return parkingLotList.stream()
                .sorted((a, b) -> b.getFreeCount() - a.getFreeCount())
                .findFirst()
                .get();
    }

    private ParkingLot getParkingLotInTurn() {
        if (Objects.isNull(lastParkingLotIndex) || lastParkingLotIndex + 1 >= parkingLotList.size()) {
            ParkingLot parkingLot = parkingLotList.get(0);
            lastParkingLotIndex = 0;
            return parkingLot;
        }
        ParkingLot parkingLot = parkingLotList.get(lastParkingLotIndex + 1);
        lastParkingLotIndex += 1;
        return parkingLot;
    }

    public List<ParkingLotState> getParkingLotState() {
        return parkingLotList.stream()
                .map(item -> new ParkingLotState(item.getFreeCount()))
                .collect(Collectors.toList());
    }
}

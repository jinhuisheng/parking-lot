import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author huisheng.jin
 * @date 2019/12/29.
 */
class ParkingLots {
    private List<ParkingLot> parkingLotList;
    private Integer lastParkingLotIndex;

    ParkingLots(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
        this.lastParkingLotIndex = null;
    }

    List<ParkingLotState> getParkingLotState() {
        return parkingLotList.stream()
                .map(item -> new ParkingLotState(item.getFreeCount()))
                .collect(Collectors.toList());
    }

    Optional<ParkingLot> getParkingLotBy(String parkSpecification) {
        if (Constant.SORT_BY_PARKING_LOT_ORDER.equals(parkSpecification)) {
            return getParkingLotInTurn();
        } else {
            return getParkingLotOrderByFreeCount();
        }
    }

    Optional<ParkingLot> getParkingLotInTurn() {
        if (isAllFull()) {
            return Optional.empty();
        }
        ParkingLot nextParkingLot = getNextParkingLot();
        while (nextParkingLot.isFull()) {
            nextParkingLot = getNextParkingLot();
        }
        return Optional.of(nextParkingLot);
    }

    private boolean isAllFull() {
        return parkingLotList.stream().noneMatch(ParkingLot::hasFree);
    }

    private ParkingLot getNextParkingLot() {
        if (Objects.isNull(lastParkingLotIndex)
                || lastParkingLotIndex + 1 >= parkingLotList.size()) {
            ParkingLot parkingLot = parkingLotList.get(0);
            lastParkingLotIndex = 0;
            return parkingLot;
        }
        ParkingLot parkingLot = parkingLotList.get(lastParkingLotIndex + 1);
        lastParkingLotIndex += 1;

        return parkingLot;
    }

    private Optional<ParkingLot> getParkingLotOrderByFreeCount() {
        return parkingLotList.stream()
                .filter(ParkingLot::hasFree).min((a, b) -> b.getFreeCount() - a.getFreeCount());
    }

}

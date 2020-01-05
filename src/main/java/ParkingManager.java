import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @author huisheng.jin
 * @date 2020/1/5.
 */
public class ParkingManager extends ParkingBoy {
    private final List<ParkingBoy> parkingBoyList;

    public ParkingManager(List<ParkingBoy> parkingBoyList, List<ParkingLot> parkingLotList) {
        super(parkingLotList);
        checkInitValid(parkingBoyList, parkingLotList);
        this.parkingBoyList = parkingBoyList;
    }

    private void checkInitValid(List<ParkingBoy> parkingBoyList, List<ParkingLot> parkingLotList) {
        if (CollectionUtils.isEmpty(parkingBoyList)
                && CollectionUtils.isEmpty(parkingLotList)) {
            throw new InvalidManagerException("初始化失败");
        }
    }

    @Override
    public String park(Car car) {
        Optional<ParkingBoy> parkingBoy = chooseParkingBoy();
        if (parkingBoy.isPresent()) {
            return parkingBoy.get().park(car);
        }
        if (CollectionUtils.isNotEmpty(parkingLotList) && hasParkingSpace()) {
            return super.park(car);
        }

        throw new ManagerHasNoParkingSpaceException("经理停车位已满");
    }

    private Optional<ParkingBoy> chooseParkingBoy() {
        if (CollectionUtils.isEmpty(parkingBoyList)) {
            return Optional.empty();
        }
        return parkingBoyList.stream()
                .filter(ParkingBoy::hasParkingSpace)
                .findFirst();
    }

}

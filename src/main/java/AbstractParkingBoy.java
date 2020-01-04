import java.util.List;
import java.util.Optional;

/**
 * @author huisheng.jin
 * @date 2020/1/4.
 */
public abstract class AbstractParkingBoy {
    List<ParkingLot> parkingLotList;

    AbstractParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public String park(Car car) {
        ParkingLot parkingLot = getFreeParkingLotInterval();
        return parkingLot.park(car);
    }

    private ParkingLot getFreeParkingLotInterval() {
        return getFreeParkingLot().orElseThrow(() -> new AllParkingLotsFullException("所有停车场已满"));
    }

    /**
     * 获取有空闲的停车场
     *
     * @return
     */
    public abstract Optional<ParkingLot> getFreeParkingLot();
}

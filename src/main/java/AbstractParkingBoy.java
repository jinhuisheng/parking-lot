import java.util.List;

/**
 * @author huisheng.jin
 * @date 2020/1/4.
 */
public abstract class AbstractParkingBoy {
    protected List<ParkingLot> parkingLotList;

    public AbstractParkingBoy(List<ParkingLot> parkingLotList) {
        this.parkingLotList = parkingLotList;
    }

    public String park(Car car) {
        ParkingLot parkingLot = getFreeParkingLot();
        return parkingLot.park(car);
    }

    /**
     * 获取有空闲的停车场
     *
     * @return
     */
    public abstract ParkingLot getFreeParkingLot();
}

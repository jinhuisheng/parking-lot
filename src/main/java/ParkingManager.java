import java.util.List;

/**
 * @author huisheng.jin
 * @date 2019/12/29.
 */
public class ParkingManager extends ParkingBoy {

    public ParkingManager(List<ParkingLot> parkingLotList) {
        super(parkingLotList, Constant.SORT_BY_FREE_COUNT);
    }

}

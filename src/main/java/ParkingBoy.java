import java.util.List;

/**
 * @author huisheng.jin
 * @date 2020/1/4.
 */
public class ParkingBoy extends AbstractParkingBoy {

    public ParkingBoy(List<ParkingLot> parkingLotList) {
        super(parkingLotList);
    }

    @Override
    public ParkingLot getFreeParkingLot() {
        return parkingLotList.stream()
                .filter(ParkingLot::hasFree)
                .findFirst()
                .orElseThrow(() -> new AllParkingLotsFullException("所有停车场已满"));
    }
}

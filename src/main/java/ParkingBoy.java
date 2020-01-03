import java.util.List;
import java.util.Optional;

/**
 * @author huisheng.jin
 * @version 2019/12/25.
 */
public class ParkingBoy {
    private ParkingLots parkingLots;
    private String sortType;

    public ParkingBoy(List<ParkingLot> parkingLotList, String sortType) {
        parkingLots = new ParkingLots(parkingLotList);
        this.sortType = sortType;
    }

    public List<ParkingLotState> getParkingLotState() {
        return parkingLots.getParkingLotState();
    }

    public String park(Car car) {
        Optional<ParkingLot> nullableParkingLot = parkingLots.getParkingLotBy(sortType);
        return nullableParkingLot.get().park(car);
    }
}

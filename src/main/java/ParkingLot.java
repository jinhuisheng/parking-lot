import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

/**
 * @author huisheng.jin
 * @version 2019/12/21.
 */
public class ParkingLot {
    private int space;
    private HashMap<String, Car> parkingLotCarList;

    public ParkingLot(int space) {
        parkingLotCarList = new HashMap<>();
        this.space = space;
    }

    public String park(Car car) {
        if (parkingLotCarList.size() == space) {
            throw new RuntimeException("车位已满");
        }
        String credential = UUID.randomUUID().toString();
        parkingLotCarList.put(credential, car);
        return credential;
    }


    Integer getFreeCount() {
        return space - parkingLotCarList.size();
    }

    boolean hasFree() {
        return getFreeCount() > 0;
    }

    boolean isFull() {
        return getFreeCount() == 0;
    }

    public Car takeout(String credential) {
        Car car = parkingLotCarList.get(credential);
        if (Objects.isNull(car)) {
            throw new RuntimeException("车已取");
        }
        parkingLotCarList.remove(credential);
        return car;
    }
}
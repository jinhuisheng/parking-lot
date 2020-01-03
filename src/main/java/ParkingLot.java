import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

/**
 * @author huisheng.jin
 * @date 2020/1/3.
 */
public class ParkingLot {
    private int capacity;
    private HashMap<String, Car> parkingCarList;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkingCarList = new HashMap<>();
    }

    public String park(Car car) {
        if (parkingCarList.size() == capacity) {
            throw new ParkingLotFullException("车位已满");
        }
        String credential = UUID.randomUUID().toString();
        parkingCarList.put(credential, car);
        return credential;
    }

    public Car take(String credential) {
        Car car = parkingCarList.get(credential);
        if (Objects.isNull(car)) {
            throw new InvalidCredentialException("无效凭证");
        }
        parkingCarList.remove(credential);
        return car;
    }
}

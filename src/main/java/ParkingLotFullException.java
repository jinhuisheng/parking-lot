/**
 * @author huisheng.jin
 * @date 2020/1/3.
 */
public class ParkingLotFullException extends RuntimeException {
    private String msg;

    ParkingLotFullException(String msg) {
        this.msg = msg;
    }
}

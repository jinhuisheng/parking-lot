/**
 * @author huisheng.jin
 * @date 2020/1/4.
 */
public class AllParkingLotsFullException extends RuntimeException {
    private String msg;

    AllParkingLotsFullException(String msg) {
        this.msg = msg;
    }
}

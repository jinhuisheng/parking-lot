/**
 * @author huisheng.jin
 * @date 2020/1/5.
 */
public class InvalidManagerException extends RuntimeException {
    private String msg;

    public InvalidManagerException(String msg) {

        this.msg = msg;
    }
}

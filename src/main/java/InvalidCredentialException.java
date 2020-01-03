/**
 * @author huisheng.jin
 * @date 2020/1/3.
 */
public class InvalidCredentialException extends RuntimeException {
    private String msg;

    InvalidCredentialException(String msg) {
        this.msg = msg;
    }
}

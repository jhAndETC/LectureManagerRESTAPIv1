package hyundai.cc.exception;

import org.springframework.dao.DataAccessException;

public class UserCreationException extends DataAccessException {

    public UserCreationException(String msg) {
        super(msg);
    }
}

package cn.bestsort.lic.exception;

import org.springframework.http.HttpStatus;

/**
 * @author bestsort
 * @version 1.0
 * @date 2020/3/16 下午2:30
 */
public class NotFoundException extends LicException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}

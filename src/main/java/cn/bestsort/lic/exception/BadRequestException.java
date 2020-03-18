package cn.bestsort.lic.exception;

import org.springframework.http.HttpStatus;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2020/3/18 上午11:55
 */
public class BadRequestException extends LicException{
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}

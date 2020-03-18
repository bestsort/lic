package cn.bestsort.lic.exception;

/**
 * @author bestsort
 * @version 1.0
 * @date 2/29/20 3:38 PM
 */
public class isExistException extends BadRequestException{

    public isExistException(String message) {
        super(message);
    }

    public isExistException(String message, Throwable cause) {
        super(message, cause);
    }
}

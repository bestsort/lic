package cn.bestsort.lic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * TODO
 *
 * @author bestsort
 * @version 1.0
 * @date 2020/3/16 下午2:31
 */
public abstract class LicException extends RuntimeException {
    /**
     * Error errorData.
     */
    private Object errorData;

    public LicException(String message) {
        super(message);
    }

    public LicException(String message, Throwable cause) {
        super(message, cause);
    }

    @NonNull
    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    /**
     * Sets error errorData.
     *
     * @param errorData error data
     * @return current exception.
     */
    @NonNull
    public LicException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}

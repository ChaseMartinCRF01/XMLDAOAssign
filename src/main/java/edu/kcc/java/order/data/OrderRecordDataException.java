package edu.kcc.java.order.data;

/**
 * A custom Exception for use with data operations and OrderRecord data.
 *
 * @author Bob Trapp
 */
public class OrderRecordDataException extends Exception {

    /**
     * No-argument constructor for OrderRecordDataException
     */
    public OrderRecordDataException() {
    }

    /**
     * Constructor for OrderRecordDataException
     *
     * @param message
     */
    public OrderRecordDataException(String message) {
        super(message);
    }

    /**
     * Constructor for OrderRecordDataException
     *
     * @param message
     * @param cause
     */
    public OrderRecordDataException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor for OrderRecordDataException
     *
     * @param cause
     */
    public OrderRecordDataException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor for OrderRecordDataException
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public OrderRecordDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }



}

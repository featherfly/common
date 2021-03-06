
package cn.featherfly.common.exception;

import java.util.Locale;

import cn.featherfly.common.locale.LocalizedMessage;

/**
 * <p>
 * UnsupportedException
 * </p>
 * .
 *
 * @author zhongj
 * @since 1.7.9
 */
public class UnsupportedException extends LocalizedException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1456430559125327924L;

    /**
     * Instantiates a new inits the exception.
     */
    public UnsupportedException() {
        super("#unsupported");
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public UnsupportedException(String message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message message
     * @param locale  locale
     */
    public UnsupportedException(String message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message message
     * @param args    args
     * @param locale  locale
     * @param ex      ex
     */
    public UnsupportedException(String message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public UnsupportedException(String message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public UnsupportedException(String message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param args    the args
     */
    public UnsupportedException(String message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public UnsupportedException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param message the message
     */
    public UnsupportedException(String message) {
        super(message);
    }

    /**
     * Instantiates a new inits the exception.
     *
     * @param ex the ex
     */
    public UnsupportedException(Throwable ex) {
        super(ex);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param locale  the locale
     * @param ex      the ex
     */
    public UnsupportedException(LocalizedMessage message, Locale locale, Throwable ex) {
        super(message, locale, ex);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param locale  the locale
     */
    public UnsupportedException(LocalizedMessage message, Locale locale) {
        super(message, locale);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     * @param ex      the ex
     */
    public UnsupportedException(LocalizedMessage message, Object[] args, Locale locale, Throwable ex) {
        super(message, args, locale, ex);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param args    the args
     * @param locale  the locale
     */
    public UnsupportedException(LocalizedMessage message, Object[] args, Locale locale) {
        super(message, args, locale);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param args    the args
     * @param ex      the ex
     */
    public UnsupportedException(LocalizedMessage message, Object[] args, Throwable ex) {
        super(message, args, ex);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param args    the args
     */
    public UnsupportedException(LocalizedMessage message, Object[] args) {
        super(message, args);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     * @param ex      the ex
     */
    public UnsupportedException(LocalizedMessage message, Throwable ex) {
        super(message, ex);
    }

    /**
     * Instantiates a new unsupported exception.
     *
     * @param message the message
     */
    public UnsupportedException(LocalizedMessage message) {
        super(message);
    }
}

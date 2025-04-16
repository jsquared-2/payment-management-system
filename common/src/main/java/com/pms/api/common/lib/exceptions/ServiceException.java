package com.pms.api.common.lib.exceptions;

/**
 * Utility {@link RuntimeException} for use in services.
 */
public class ServiceException extends RuntimeException {
    /**
     * Constructs a new {@code RuntimeException} related to a service.
     *
     * @param message
     *         the exception's message
     */
    public ServiceException(String message) {
        super(message);
    }
}

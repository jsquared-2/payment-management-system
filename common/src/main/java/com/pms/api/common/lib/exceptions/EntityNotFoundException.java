package com.pms.api.common.lib.exceptions;

/**
 * Utility {@link ServiceException} for when an entity is not found in a repository.
 */
public class EntityNotFoundException extends ServiceException {
    /**
     * Constructs a new {@code ServiceException} when an entity cannot be found.
     *
     * @param message
     *         the exception's message
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}

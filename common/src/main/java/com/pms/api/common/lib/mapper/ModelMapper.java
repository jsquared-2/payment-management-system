package com.pms.api.common.lib.mapper;

/**
 * Utility interface for a standard mapper.
 *
 * @param <Model>
 *         the entity model linked to a repository
 * @param <Request>
 *         the Data Transfer Object (DTO) containing request information from a controller
 * @param <Response>
 *         the Data Transfer Object (DTO) containing response information for a controller
 */
public interface ModelMapper<Model, Request, Response> {
    /**
     * Converts a request Data Transfer Object (DTO) into an entity model.
     *
     * @param request
     *         the {@code Request} from a controller
     *
     * @return a {@code Model} for a repository
     */
    Model toModel(Request request);

    /**
     * Converts an entity model into a response Data Transfer Object (DTO).
     *
     * @param model
     *         the {@code Model} linked to a repository
     *
     * @return a {@code Response} for a controller
     */
    Response toDTO(Model model);
}

package com.hollonconsulting.solarwars.server.appconfig.exception;

import com.hollonconsulting.solarwars.server.model.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import javax.ws.rs.core.Response;

public abstract class AbstractExceptionMapper {
    private static Logger logger = LoggerFactory.getLogger(AbstractExceptionMapper.class);

    /**
     * Creates a response based on the status and response entity.
     *
     * @param status         The http status code.
     * @param responseEntity Represents an HTTP request or response entity, consisting of headers
     *                       and body. Includes a HttpStatus code.
     * @return A Response object.
     */
    protected Response errorResponse(int status, ResponseEntity responseEntity) {
        logger.error("Error status: " + status);

        return customizeResponse(status, responseEntity);
    }

    /**
     * Creates a response based on the status and response entity.
     *
     * @param status         The http status code.
     * @param responseEntity Represents an HTTP request or response entity, consisting of headers
     *                       and body. Includes a HttpStatus code.
     * @param throwable      Represents the error or exception thrown.
     * @return A Response object.
     */
    protected Response errorResponse(int status, ResponseEntity responseEntity, Throwable throwable) {
        logger.error("Error status: " + status, throwable);

        return customizeResponse(status, responseEntity);
    }

    /**
     * Creates a response based on the status and response entity.
     *
     * @param status    The http status code.
     * @param throwable Represents the error or exception thrown.
     * @return A Response object.
     */
    protected Response errorResponse(int status, ErrorResponse response,
                                     Throwable throwable) {
        logger.error("Error status: " + status, throwable);

        return customizeResponse(status, response);
    }

    /**
     * Creates the response.
     *
     * @param status         The http status code.
     * @param responseEntity Represents an http requires or response entity, consisting of headers
     *                       and body. Includes a HttpStatus code.
     * @return A Response object.
     */
    private Response customizeResponse(int status, ResponseEntity responseEntity) {
        return Response.status(status).entity(responseEntity).build();
    }

    /**
     * Creates the response
     *
     * @param status The http status code.
     * @param resp   Represents an http requires or response entity wrapped in a custom response,
     *               consisting of headers and body. Includes a HttpStatus code.
     * @return A Response object.
     */
    private Response customizeResponse(int status, ErrorResponse resp) {
        return Response.status(status).entity(resp).build();
    }
}

package com.hollonconsulting.solarwars.server.appconfig.exception;

import com.hollonconsulting.solarwars.server.model.response.ErrorResponse;
import com.hollonconsulting.solarwars.server.model.response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Maps to any exception not mapped elsewhere.
 *
 * @see <a href="http://bit.ly/1HKCVWA">7.3. WebApplicationException and Mapping Exceptions to
 * Responses</a>
 */
@Provider
public class WebApplicationExceptionMapper extends AbstractExceptionMapper implements
        ExceptionMapper<WebApplicationException> {

    private static Logger logger = LoggerFactory.getLogger(WebApplicationExceptionMapper.class);
    private Integer code;
    private Status status;
    private String message;

    public WebApplicationExceptionMapper() {
        this.code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        this.status = Status.FAIL;
    }

    /**
     * Map an exception to a Response. Returning null results in a Response.Status.NO_CONTENT
     * response. Throwing a runtime exception results in a Response.Status.INTERNAL_SERVER_ERROR
     * response.
     *
     * @param exception The runtime exception that is caught a mapped to a response.
     * @return A Response object.
     */
    @Override
    public Response toResponse(WebApplicationException exception) {
        message = exception.getMessage();
        this.code = exception.getResponse().getStatus();

        ErrorResponse response = new ErrorResponse(code, status, message);

        logger.error(response.toString());

        return errorResponse(code, response, exception);
    }
}
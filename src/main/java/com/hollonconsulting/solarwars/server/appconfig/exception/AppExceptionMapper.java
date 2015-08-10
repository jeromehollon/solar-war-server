package com.hollonconsulting.solarwars.server.appconfig.exception;


import com.hollonconsulting.solarwars.server.model.response.ErrorResponse;
import com.hollonconsulting.solarwars.server.model.response.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AppExceptionMapper extends AbstractExceptionMapper implements
        ExceptionMapper<Exception> {

    private static Logger logger = LoggerFactory.getLogger(AppExceptionMapper.class);
    private Integer code;
    private Status status;
    private String message;

    public AppExceptionMapper() {
        code = HttpStatus.INTERNAL_SERVER_ERROR.value();
        status = Status.FAIL;
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
    public Response toResponse(Exception exception) {
        message = exception.getMessage();

        ErrorResponse response = new ErrorResponse(code, status, message);

        logger.error(response.toString());

        return errorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), response, exception);
    }
}

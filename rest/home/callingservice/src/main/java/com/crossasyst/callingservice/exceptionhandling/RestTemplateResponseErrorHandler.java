package com.crossasyst.callingservice.exceptionhandling;

import com.crossasyst.callingservice.exceptions.BadRequestException;
import com.crossasyst.callingservice.exceptions.ClientException;
import com.crossasyst.callingservice.exceptions.PersonNotFoundException;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {
        return httpResponse.getStatusCode().is4xxClientError() || httpResponse.getStatusCode().is5xxServerError();
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {

        if (httpResponse.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            throw new ClientException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        } else if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new PersonNotFoundException(HttpStatus.NOT_FOUND, "Person with this id not found");
        } else if (httpResponse.getStatusCode().is4xxClientError()) {
            throw new ClientException(HttpStatus.INTERNAL_SERVER_ERROR,"Client Exception occured");
        } else if (httpResponse.getStatusCode().is5xxServerError()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST, "5xx Server Error");
        }
    }
}



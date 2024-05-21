package com.clientside.mbkm.utils;

import java.io.IOException;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;

public class RequestInterceptorUtil implements ClientHttpRequestInterceptor {

    @Override
    @SuppressWarnings("null")
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        Authentication auth = AuthSessionUtil.getAuthentication();
        if (!request.getURI().getPath().equals("/auth/login")) {
            request
                    .getHeaders()
                    .add(
                            "Authorization",
                            "Basic " +
                                    BasicHeaderUtil.createBasicToken(
                                            auth.getName(),
                                            auth.getCredentials().toString()));
        }

        ClientHttpResponse response = execution.execute(request, body);
        return response;
    }
}
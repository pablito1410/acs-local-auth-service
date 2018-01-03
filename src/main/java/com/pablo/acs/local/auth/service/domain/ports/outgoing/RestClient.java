package com.pablo.acs.local.auth.service.domain.ports.outgoing;

import com.pablo.acs.local.auth.service.domain.exception.RestClientException;
import com.pablo.acs.local.auth.service.domain.ports.incoming.Response;
import org.springframework.util.MultiValueMap;

import java.util.Map;

public interface RestClient {

    <R> Response<R> post(String url, Object body, Class<R> response);

    Response<Void> post(String url, Object body) throws RestClientException;

    <R> Response<R> get(String url, Class<R> response);

    <R> Response<R> get(String url, Class<R> response, Map<String, ?> uriVariables);

    <R> Response<R> get(String url, Class<R> response,
                        Map<String, ?> uriVariables, MultiValueMap<String, String> headers);
}

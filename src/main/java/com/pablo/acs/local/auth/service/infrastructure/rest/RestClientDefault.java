package com.pablo.acs.local.auth.service.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablo.acs.local.auth.service.domain.exception.RestClientException;
import com.pablo.acs.local.auth.service.domain.ports.incoming.Response;
import com.pablo.acs.local.auth.service.domain.ports.outgoing.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

public class RestClientDefault implements RestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public RestClientDefault(final RestTemplate restTemplate, final ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }

    @Override
    public <R> Response<R> post(final String url, final Object body, final Class<R> response) throws RestClientException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(body), headers);
            final ResponseEntity<R> responseEntity = restTemplate.postForEntity(url, entity, response);
            return new Response<R>() {
                @Override
                public int getStatusCode() {
                    return responseEntity.getStatusCodeValue();
                }

                @Override
                public R getBody() {
                    return responseEntity.getBody();
                }
            };
        } catch (final JsonProcessingException e) {
            throw new RestClientException(e);
        }
    }

    @Override
    public Response<Void> post(final String url, final Object body) {
        return post(url, body, Void.class);
    }

    @Override
    public <R> Response<R> get(final String url, final Class<R> response) {
        return get(url, response, Collections.emptyMap());
    }

    @Override
    public <R> Response<R> get(final String url, final Class<R> response, final Map<String, ?> uriVariables) {
        final ResponseEntity<R> responseEntity = restTemplate.getForEntity(url, response, uriVariables);
        return new Response<R>() {
            @Override
            public int getStatusCode() {
                return responseEntity.getStatusCodeValue();
            }

            @Override
            public R getBody() {
                return responseEntity.getBody();
            }
        };
    }

    @Override
    public <R> Response<R> get(final String url, final Class<R> response,
                               final Map<String, ?> uriVariables, final  MultiValueMap<String, String> headers) {
        HttpEntity entity = new HttpEntity(headers);
        final ResponseEntity<R> responseEntity = restTemplate.exchange(
                url, HttpMethod.GET, entity, response, uriVariables);
        return new Response<R>() {
            @Override
            public int getStatusCode() {
                return responseEntity.getStatusCodeValue();
            }

            @Override
            public R getBody() {
                return responseEntity.getBody();
            }
        };
    }
}

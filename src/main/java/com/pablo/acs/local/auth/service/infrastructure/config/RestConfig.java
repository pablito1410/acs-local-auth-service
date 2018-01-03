package com.pablo.acs.local.auth.service.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pablo.acs.local.auth.service.infrastructure.rest.RestClientDefault;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplateBuilder().build();
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        // Note: here we are making this converter to process any kind of response,
//        // not only application/*json, which is the default behaviour
//        converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
//        messageConverters.add(converter);
//        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestClientDefault restClient(final RestTemplate restTemplate, final ObjectMapper objectMapper) {
        return new RestClientDefault(restTemplate, objectMapper);
    }
}

package ru.redcollar.autoservice.configurations;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class ApplicationConfig {

    @Value("${ordersService.url.base}")
    private String urlOrders;

    @Bean
    public WebClient webClientBean() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(urlOrders);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.URI_COMPONENT);
        return WebClient.builder().uriBuilderFactory(factory).baseUrl(urlOrders).build();
    }
}
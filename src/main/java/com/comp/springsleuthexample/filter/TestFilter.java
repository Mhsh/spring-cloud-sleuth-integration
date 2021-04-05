package com.comp.springsleuthexample.filter;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

/**
 *
 * Logging Filter
 * 
 */
@Component
public class TestFilter extends AbstractGatewayFilterFactory<Object> {

	/**
	 * log.
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public GatewayFilter apply(final Object config) {
		return (exchange, chain) -> {
			LOGGER.info("Test Filter Incoming-1");
			return testAsync().flatMap(test -> {
				LOGGER.info("Test Filter Incoming-2");
				ServerHttpRequest modifiedRequest = exchange.getRequest().mutate().header("userId", "test").build();
				return chain.filter(exchange.mutate().request(modifiedRequest).build());
			});
		};
	}

	public Mono<String> testAsync() {
		return Mono.just("test").delayElement(Duration.ofSeconds(5));
	}
}

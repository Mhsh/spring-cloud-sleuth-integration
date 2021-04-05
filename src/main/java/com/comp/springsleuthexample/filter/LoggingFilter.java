package com.comp.springsleuthexample.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Mono;

/**
 *
 * Logging Filter
 * 
 */
@Component
public class LoggingFilter extends AbstractGatewayFilterFactory<Object> {

	/**
	 * log.
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public GatewayFilter apply(final Object config) {
		return (exchange, chain) -> {
			LOGGER.info("Incoming request");
			return chain.filter(exchange).then(Mono.fromRunnable(() -> {
				LOGGER.info("Outgoing response");
			}));
		};
	}
}

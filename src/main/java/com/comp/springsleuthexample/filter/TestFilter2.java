package com.comp.springsleuthexample.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 *
 * Logging Filter
 * 
 */
@Component
public class TestFilter2 extends AbstractGatewayFilterFactory<Object> {

	/**
	 * log.
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Override
	public GatewayFilter apply(final Object config) {
		return (exchange, chain) -> {
			LOGGER.info("Test Filter Incoming-2");
			return chain.filter(exchange);
		};
	}
}

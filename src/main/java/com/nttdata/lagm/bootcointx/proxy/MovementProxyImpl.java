package com.nttdata.lagm.bootcointx.proxy;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.nttdata.lagm.bootcointx.dto.request.TransferRequestDto;
import com.nttdata.lagm.bootcointx.dto.response.BakingMovementResponseDto;

import reactor.core.publisher.Flux;

@Component
public class MovementProxyImpl implements MovementProxy {

	private Logger LOGGER = LoggerFactory.getLogger(MovementProxyImpl.class);

	@Value("${config-eureka.base.movement.endpoint}")
	private String endpointMovement;

	@Autowired
	@Qualifier("wcLoadBalanced")
	private WebClient.Builder webClientBuilder;

	@Override
	public Flux<BakingMovementResponseDto> transfer(TransferRequestDto transferRequestDto) {
		Map<String, Object> params = new HashMap<>();
		
		return webClientBuilder
			//.clientConnector(RestUtils.getDefaultClientConnector())
			.build()
			.post()
			.uri(endpointMovement + "/transfer")
			.bodyValue(transferRequestDto)
			.retrieve()
			.bodyToFlux(BakingMovementResponseDto.class);

	}
}
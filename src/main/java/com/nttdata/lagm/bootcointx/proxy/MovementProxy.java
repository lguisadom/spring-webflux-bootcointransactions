package com.nttdata.lagm.bootcointx.proxy;

import com.nttdata.lagm.bootcointx.dto.request.TransferRequestDto;
import com.nttdata.lagm.bootcointx.dto.response.BakingMovementResponseDto;

import reactor.core.publisher.Flux;

public interface MovementProxy {
	Flux<BakingMovementResponseDto> transfer(TransferRequestDto transferRequestDto);
}

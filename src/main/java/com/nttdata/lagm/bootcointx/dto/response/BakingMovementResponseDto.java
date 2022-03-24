package com.nttdata.lagm.bootcointx.dto.response;

import com.nttdata.lagm.bootcointx.model.movement.BankingMovement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BakingMovementResponseDto {
	private BankingMovement bankingMovement;
	private String message;
}

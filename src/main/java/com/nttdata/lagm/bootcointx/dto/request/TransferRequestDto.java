package com.nttdata.lagm.bootcointx.dto.request;

import lombok.Data;

@Data
public class TransferRequestDto {
	private String sourceAccountNumber;
	private String targetAccountNumber;
	private String amount;
}

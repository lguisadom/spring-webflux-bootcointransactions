package com.nttdata.lagm.bootcointx.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransactionRequest {
	private String id;
	private String identification;
	private String accountNumber;
	private BigDecimal amount;
	private String transactionType;
	//@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private String date;
	private String status;
}

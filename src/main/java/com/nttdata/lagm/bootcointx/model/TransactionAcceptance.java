package com.nttdata.lagm.bootcointx.model;

import lombok.Data;

@Data
public class TransactionAcceptance {
	private String id;
	private String transactionId;
	private String sellerIdentification;
	private TransactionRequest transactionRequest;
	private ExchangeRate exchangeRate;
	// @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private String date;
	private String status;
}

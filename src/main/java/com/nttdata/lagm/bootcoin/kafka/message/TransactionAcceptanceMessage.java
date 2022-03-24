package com.nttdata.lagm.bootcoin.kafka.message;

import java.io.Serializable;

import lombok.Data;

@Data
public class TransactionAcceptanceMessage implements Serializable {
	private String id;
	private String sellerIdentification;
}

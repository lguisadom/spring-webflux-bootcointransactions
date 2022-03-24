package com.nttdata.lagm.bootcointx.util;

import java.time.LocalDateTime;

public class Util {

	public static LocalDateTime getToday() {
		return LocalDateTime.now();
	}
	
	public static String getTransactionTypeDescription(Integer transactionTypeId) {
		String description = "";
		switch (transactionTypeId) {
			case 1:
				description = Constants.TRANSACTION_TYPE_YANQUI;
				break;
				
			case 2:
				description = Constants.TRANSACTION_TYPE_TRANSFER;
				break;
				
			default:
				description = Constants.TRANSACTION_TYPE_UNDEFINED;
				break;
		}
		
		return description;
	}
}

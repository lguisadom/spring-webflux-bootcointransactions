package com.nttdata.lagm.bootcointx.model.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.lagm.bootcointx.dto.request.TransferRequestDto;
import com.nttdata.lagm.bootcointx.model.Buyer;
import com.nttdata.lagm.bootcointx.model.ExchangeRate;
import com.nttdata.lagm.bootcointx.model.Seller;
import com.nttdata.lagm.bootcointx.model.Transaction;
import com.nttdata.lagm.bootcointx.model.TransactionAcceptance;
import com.nttdata.lagm.bootcointx.proxy.MovementProxy;
import com.nttdata.lagm.bootcointx.repository.TransactionRepository;
import com.nttdata.lagm.bootcointx.util.Constants;
import com.nttdata.lagm.bootcointx.util.Util;

import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private MovementProxy movementProxy;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Mono<Transaction> create(TransactionAcceptance transactionAcceptance) {
		Transaction transaction = new Transaction();
		transaction.setSeller(new Seller(transactionAcceptance.getSellerIdentification()));
		transaction.setBuyer(new Buyer(transactionAcceptance.getTransactionRequest().getIdentification()));
		transaction.setDate(Util.getToday());
		transaction.setExchangeRate(transactionAcceptance.getExchangeRate());
		transaction.setTransactionId(transactionAcceptance.getTransactionId());
		
		// Validate
		BigDecimal numberBootCoins = transactionAcceptance.getTransactionRequest().getAmount();
		ExchangeRate exchange = transactionAcceptance.getExchangeRate();
		BigDecimal purchaseAmount = exchange.getPurchase().multiply(numberBootCoins);
		BigDecimal saleAmount = exchange.getSale().multiply(numberBootCoins);
		String transactionType = transactionAcceptance.getTransactionRequest().getTransactionType();

		return transactionRepository.save(transaction)
			.flatMap(tx -> {
				
				// Forma de pago
				if (Constants.TRANSACTION_TYPE_TRANSFER.equalsIgnoreCase(transactionType)) {
					
					// Transfer
					TransferRequestDto transferRequestDto = new TransferRequestDto();
					transferRequestDto.setAmount(purchaseAmount.toString());
					transferRequestDto.setSourceAccountNumber(transactionAcceptance.getTransactionRequest().getAccountNumber());
					transferRequestDto.setTargetAccountNumber(transactionAcceptance.getAccountNumber());
					
					movementProxy.transfer(transferRequestDto).subscribe();
				} else if (Constants.TRANSACTION_TYPE_YANQUI.equalsIgnoreCase(transactionType)) {
					// TODO Invoca transferir en aplicativo Yanqui
					
				}
				
				
				return Mono.just(tx);
			});
	}
}

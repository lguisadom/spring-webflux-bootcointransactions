package com.nttdata.lagm.bootcointx.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdata.lagm.bootcointx.model.Buyer;
import com.nttdata.lagm.bootcointx.model.Seller;
import com.nttdata.lagm.bootcointx.model.Transaction;
import com.nttdata.lagm.bootcointx.model.TransactionAcceptance;
import com.nttdata.lagm.bootcointx.repository.TransactionRepository;
import com.nttdata.lagm.bootcointx.util.Util;

import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Mono<Transaction> create(TransactionAcceptance transactionAcceptance) {
		Transaction transaction = new Transaction();
		transaction.setSeller(new Seller(transactionAcceptance.getSellerIdentification()));
		transaction.setBuyer(new Buyer(transactionAcceptance.getTransactionRequest().getIdentification()));
		transaction.setDate(Util.getToday());
		transaction.setExchangeRate(transactionAcceptance.getExchangeRate());
		transaction.setTransactionId(transactionAcceptance.getTransactionId());
		return transactionRepository.save(transaction);
	}
}

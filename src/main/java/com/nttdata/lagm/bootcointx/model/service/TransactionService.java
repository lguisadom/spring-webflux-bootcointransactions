package com.nttdata.lagm.bootcointx.model.service;

import com.nttdata.lagm.bootcointx.model.Transaction;
import com.nttdata.lagm.bootcointx.model.TransactionAcceptance;

import reactor.core.publisher.Mono;

public interface TransactionService {
	Mono<Transaction> create(TransactionAcceptance transaction);
}

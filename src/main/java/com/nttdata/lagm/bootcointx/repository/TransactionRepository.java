package com.nttdata.lagm.bootcointx.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.lagm.bootcointx.model.Transaction;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {

}

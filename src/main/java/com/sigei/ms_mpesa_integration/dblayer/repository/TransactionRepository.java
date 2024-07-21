package com.sigei.ms_mpesa_integration.dblayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sigei.ms_mpesa_integration.dblayer.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}


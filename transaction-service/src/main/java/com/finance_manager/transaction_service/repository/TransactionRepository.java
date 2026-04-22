package com.finance_manager.transaction_service.repository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.finance_manager.transaction_service.entity.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository <Transaction, UUID>
{
	List <Transaction> findByUserId (UUID userId);
	@Query ("SELECT t FROM Transaction t WHERE t.userId = :userId " + "AND FUNCTION ('MONTH', t.localDateTime) = :month " + "AND FUNCTION ('YEAR', t.localDateTime) = :year")
	List <Transaction> getByMonth (@Param ("userId") UUID userId, @Param ("month") int month, @Param ("year") int year);
}
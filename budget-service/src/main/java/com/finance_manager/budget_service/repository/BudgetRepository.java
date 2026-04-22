package com.finance_manager.budget_service.repository;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.finance_manager.budget_service.entity.Budget;
public interface BudgetRepository extends JpaRepository <Budget, UUID>
{
	
}
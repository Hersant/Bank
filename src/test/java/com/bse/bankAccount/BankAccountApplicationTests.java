package com.bse.bankAccount;

import com.bse.bankAccount.dao.AccountDao;
import com.bse.bankAccount.model.Operation;
import com.bse.bankAccount.model.OperationType;
import com.bse.bankAccount.service.AccountServiceImpl;
import com.bse.bankAccount.web.exceptions.AccountNumberException;
import com.bse.bankAccount.web.exceptions.AmountException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class BankAccountApplicationTests {

	@InjectMocks
	AccountServiceImpl accountService;

	@Mock
	AccountDao accountDao;

	@Test
	public void statement_returnsStatement_whenCorrectAccountNumber() throws AccountNumberException {
		List<Operation> operations = new ArrayList<>();
		operations.add(new Operation(OperationType.DEPOSIT, String.valueOf(500), BigDecimal.valueOf(500)));
		operations.add(new Operation(OperationType.WITHDRAWAL, String.valueOf(100), BigDecimal.valueOf(400)));

		when(accountDao.statement("FR123")).thenReturn(operations);

		// Test
		List<Operation> ops = accountService.statement("FR123");

		// Verify
		assertEquals(2, ops.size());
		verify(accountDao).statement("FR123");

	}

	@Test
	public void deposit_returnsTheNewBalance_whenPositiveAmountAndCorrectAccountNumber() throws AmountException, AccountNumberException {
		Operation op = new Operation(OperationType.DEPOSIT, String.valueOf(500), BigDecimal.valueOf(500));
		BigDecimal balance =  op.getBalance();
		when(accountDao.deposit("FR123", BigDecimal.valueOf(500))).thenReturn(BigDecimal.valueOf(500));

		// Test
		BigDecimal actualBalance = accountService.deposit("FR123", BigDecimal.valueOf(500));

		// Verify
		assertThat(actualBalance.subtract(BigDecimal.valueOf(500))).isEqualTo(String.valueOf(0));
		verify(accountDao).deposit("FR123", BigDecimal.valueOf(500));
	}

	@Test
	public void withdraw_returnsAmount_whenAmountLessThanOrEqualToBalance() throws AmountException, AccountNumberException {
		Operation op = new Operation(OperationType.DEPOSIT, String.valueOf(500), BigDecimal.valueOf(500));
		BigDecimal balance =  op.getBalance();
		when(accountDao.withdraw("FR123", BigDecimal.valueOf(500))).thenReturn(BigDecimal.valueOf(500));

		// Test
		BigDecimal actualBalance = accountService.withdraw("FR123", BigDecimal.valueOf(500));

		// Verify
		assertThat(actualBalance.subtract(BigDecimal.valueOf(500))).isEqualTo(String.valueOf(0));
		verify(accountDao).withdraw("FR123", BigDecimal.valueOf(500));
	}


}

/*package com.example.demo;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.stereotype.Component;
@Component
public class AtomikosJtaPlatform extends AbstractJtaPlatform{
	private static final long serialVersionUID = 6016955542823297386L;

	static TransactionManager transactionManager;
	static UserTransaction transaction;


	@Override
	protected TransactionManager locateTransactionManager() {
		return transactionManager;
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		return transaction;
	}

}
*/
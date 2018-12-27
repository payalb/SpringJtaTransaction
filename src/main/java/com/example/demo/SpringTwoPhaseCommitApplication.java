package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

@SpringBootApplication
@EnableTransactionManagement
public class SpringTwoPhaseCommitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTwoPhaseCommitApplication.class, args);
	}

	/*@Bean(name="AtomikosTransactionManager",initMethod = "init", destroyMethod = "close")
	public UserTransactionManager transactionManager() {
		UserTransactionManager tx = new UserTransactionManager();
		tx.setStartupTransactionService(false);
		tx.setForceShutdown(false);
		AtomikosJtaPlatform.transactionManager = tx;
		return tx;
	}

	@Bean("AtomikosUserTransaction")
	public UserTransactionImp transactionImpl() {
		UserTransactionImp imp = new UserTransactionImp();
		AtomikosJtaPlatform.transaction = imp;
		return imp;
	}

	@Bean("transactionManager")
	public JtaTransactionManager jtamanager() {
		JtaTransactionManager tx = new JtaTransactionManager();
		tx.setTransactionManager(transactionManager());
		tx.setUserTransaction(transactionImpl());
		tx.setDefaultTimeout(3000);
		return tx;
	}*/
}

package com.cse.database.service;

import com.cse.database.dao.customer.CustomerDAO;
import com.cse.database.dao.employee.EmployeeDAO;
import com.cse.database.dao.employee.UsersDAO;
import com.cse.database.dao.savings.account.*;
import com.cse.database.dto.dto.DepositDto;
import com.cse.database.dto.dto.WithdrawDto;
import com.cse.database.exception.BRSException;
import com.cse.database.models.customer.Customer;
import com.cse.database.models.employee.Employee;
import com.cse.database.models.employee.User;
import com.cse.database.models.savings.account.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class SavingsAccountServiceImp implements SavingsAccountService{

    @Autowired
    private CustomerDAO customerDAO;

    @Autowired
    private SavingsAccountDAO savingsAccountDAO;

    @Autowired
    private SavingsAccountTypeDAO savingsAccountTypeDAO;

    @Autowired
    private SavingsOpenDAO savingsOpenDAO;

    @Autowired
    private UsersDAO usersDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private SavingsCloseDAO savingsCloseDAO;

    @Autowired
    private TransactionDAO transactionDAO;

    @Autowired
    private DepositDAO depositDAO;

    @Transactional
    @Override
    public void deposit(DepositDto depositDto) {

        Optional<SavingsAccount> savingsAccount = savingsAccountDAO.findByAccountNumber(depositDto.getAccountNumber());

        if(savingsAccount.isPresent()){

            if(savingsAccount.get().getStatus().equals(SavingsAccount.Status.OPEN)){

                savingsAccount.get().setBalance(savingsAccount.get().getBalance() + depositDto.getAmount());

                final UUID transactionId = UUID.randomUUID();

                final UUID depositId = UUID.randomUUID();

                Transaction transaction = new Transaction().setTransactionId(transactionId)
                                                            .setTransactionType(Transaction.TransactionType.DEPOSIT)
                                                            .setAmount(depositDto.getAmount())
                                                            .setTimestamp(new Date());

                Deposit deposit = new Deposit().setDepositId(depositId)
                                                .setTransaction(transaction);

                savingsAccountDAO.save(savingsAccount.get());

                transactionDAO.save(transaction);

                depositDAO.save(deposit);


            }

            throw new BRSException.AccountExpiredException("Account is Closed");
        }
        throw new BRSException.EntityNotFoundException("Wrong Account Number");

    }

    @Override
    public void withdraw(WithdrawDto withdrawDto) {

    }

    @Transactional
    @Override
    public Optional<SavingsAccount> openSavingsAccount(SavingsAccount savingsAccount, SavingsOpen savingsOpen,
                                                         String accountType, String employeeUsername,String customerNic) {

        Optional<SavingsAccount> sa = savingsAccountDAO.findByAccountNumber(savingsAccount.getAccountNumber());

        if(!sa.isPresent()){
            Optional<SavingsAccountType> savingsAccountType = savingsAccountTypeDAO.findByTypeName(accountType);

            if(savingsAccountType.isPresent()){

                Optional<Customer> customerOptional = customerDAO.findCustomerByNic(customerNic);

                if(customerOptional.isPresent()){

                    Optional<User> user = usersDAO.findByUserName(employeeUsername);

                    if(user.isPresent()){

                        Optional<Employee> employee = employeeDAO.findById(user.get().getEmployeeId());

                        savingsAccount.setAccountType(savingsAccountType.get());
                        savingsAccount.setOwners(Arrays.asList(customerOptional.get()));

                        savingsOpen.setOpenBy(employee.get());

                        savingsAccountDAO.save(savingsAccount);
                        savingsOpenDAO.save(savingsOpen);

                        return Optional.of(savingsAccount);

                    }

                    throw new BRSException.EntityNotFoundException("Employee Doesn't Exist");

                }

                throw new BRSException.EntityNotFoundException("Customer Doesn't Exist");
            }

            throw new BRSException.EntityNotFoundException("Account Type Doesn't Exist");

        }

        throw new BRSException.DuplicateEntityException("Account Number Already Exist");
    }

    @Override
    public void closeSavingsAccount(String savingsAccountNumber, SavingsClose savingsClose, String employeeUsername) {

    }
}

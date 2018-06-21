package atm.service;// Copyright (c) 2018 Travelex Ltd

import atm.entity.AccountEntity;
import atm.entity.CustomerEntity;
import atm.repository.AccountRepository;
import atm.repository.CustomerRepository;
import atm.exceptions.UserAlreadyExistsException;
import atm.exceptions.UserNotFoundException;
import atm.model.Account;
import atm.model.BankEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerDataAccessImpl implements CustomerDataAccess {

    private CustomerRepository customerRepository;

    private AccountRepository accountRepository;

    @Autowired
    public CustomerDataAccessImpl(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;

        /*
        UUID IDs[] = new UUID[6];

        for (int i = 0; i<6; i++){
            IDs[i] = UUID.randomUUID();
            System.out.println("ID "+ i + " " + IDs[i]);
        }

        Customer customer1 = new Customer(1234, IDs[0], new Account(new BigDecimal(100), "GBP", IDs[1] ));
        Customer customer2 = new Customer(9876, IDs[2], new Account(BigDecimal.ZERO, "AUD", IDs[3]));

        BankEmployee employee1 = new BankEmployee(1234, IDs[4]);
        BankEmployee employee2 = new BankEmployee(9876, IDs[5]);
        */

        UUID uuid = UUID.fromString("d67aa1ae-0332-4aeb-bc1f-fba2967e7bda");
        int pin1 = 1234;
        CustomerEntity customer1 = new CustomerEntity();
        customer1.setPin(pin1);
        customer1.setId(uuid);

//        CustomerEntity customer2 = new CustomerEntity(UUID.fromString("fad4ae65-edb2-4bcc-ac88-537f50fec2fb"), 9876);

        /*
        BankEmployee employee1 = new BankEmployee(1234, UUID.fromString("b967ebe3-ff54-4671-9270-7bedbd594cde"));
        BankEmployee employee2 = new BankEmployee(9876, UUID.fromString("e6ea1563-5492-415f-9e18-77f5831ee3b7"));
        */

        this.customerRepository.save(customer1);
//        this.customerRepository.save(customer2);

        AccountEntity customer1Account = new AccountEntity(new BigDecimal(100), "GBP", UUID.fromString("adf283b8-0e2c-415e-9090-43bf467f3d2f") );
        customer1Account.setCustomerId(customer1.getId());
//        AccountEntity customer2Account = new AccountEntity(BigDecimal.ZERO, "AUD", UUID.fromString("cd72489b-f2c4-4eda-b492-96169b89bb27"));
//        customer2Account.setCustomerId(customer2.getId());

        this.accountRepository.save(customer1Account);
//        this.accountRepository.save(customer2Account);

//        users.put(customer1.getUserNumber(), );
//        users.put(customer2.getUserNumber(), customer2);
//        users.put(employee1.getUserNumber(), employee1);
//        users.put(employee2.getUserNumber(), employee2);

        /*
        ID 0 d67aa1ae-0332-4aeb-bc1f-fba2967e7bda   customer ID
        ID 1 adf283b8-0e2c-415e-9090-43bf467f3d2f   account ID
        ID 2 fad4ae65-edb2-4bcc-ac88-537f50fec2fb   customer ID
        ID 3 cd72489b-f2c4-4eda-b492-96169b89bb27   account ID
        ID 4 b967ebe3-ff54-4671-9270-7bedbd594cde   employee ID
        ID 5 e6ea1563-5492-415f-9e18-77f5831ee3b7   employee ID
         */

    }




    public Optional<CustomerEntity> getCustomer(UUID userId) {
        return customerRepository.findById(userId);
    }

    public void addCustomer(CustomerEntity customer){

        Optional<CustomerEntity> userOpt = getCustomer(customer.getId());

        if (!userOpt.isPresent()){
            customerRepository.save(customer);
        }
        else{
            throw new UserAlreadyExistsException();
        }
    }

    /*
    public void addEmployee(UUID userId, int pin){

        BankEmployee employee = new BankEmployee(pin, userId);

        Optional<User> userOpt = getCustomer(employee.getUserNumber());
        if (!userOpt.isPresent()){
            users.put(employee.getUserNumber(), employee);
        }
        else{
            throw new UserAlreadyExistsException();
        }
    }
    */


    public void removeCustomer(UUID userId){

        Optional<CustomerEntity> userOpt = customerRepository.findById(userId);
        CustomerEntity customerEntity = userOpt.orElseThrow(UserNotFoundException::new);

        customerRepository.deleteById(userId);
    }

    public void modifyCustomer(UUID userId, Integer newPin){

        Optional<CustomerEntity> customerOpt = customerRepository.findById(userId);

        CustomerEntity customerEntity = customerOpt.orElseThrow(UserNotFoundException::new);

        customerEntity.setPin(newPin);

        customerRepository.deleteById(userId);
        customerRepository.save(customerEntity);
    }

    public void addAccount(UUID customerId, AccountEntity accountEntity){

        Optional<AccountEntity> accountOpt = accountRepository.findByAccountIdAndCustomerId(accountEntity.getAccountId(), customerId);

        if (accountOpt.isPresent()){
            throw new UserNotFoundException(); //TODO make new account already exist exception
        }

        accountRepository.save(accountEntity);

    }


}

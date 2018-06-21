package atm.service;// Copyright (c) 2018 Travelex Ltd

import atm.entity.CustomerEntity;
import atm.model.Account;
import atm.model.Customer;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface CustomerDataAccess {

    public Optional<CustomerEntity> getCustomer(UUID userId);

    public void addCustomer(CustomerEntity customer);

    public void removeCustomer(UUID userId);

    public void modifyCustomer(UUID userId, Integer newPin);

}

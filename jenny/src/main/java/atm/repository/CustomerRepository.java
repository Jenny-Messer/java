// Copyright (c) 2018 Travelex Ltd

package atm.repository;

import atm.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {

}

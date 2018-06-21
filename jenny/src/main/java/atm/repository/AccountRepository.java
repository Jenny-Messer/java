// Copyright (c) 2018 Travelex Ltd

package atm.repository;

import atm.entity.AccountEntity;
import atm.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;

@Transactional
public interface AccountRepository extends JpaRepository<AccountEntity, UUID> {

    Optional<AccountEntity> findByAccountIdAndCustomerId(UUID accountId, UUID customerID);

}

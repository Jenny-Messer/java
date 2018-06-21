// Copyright (c) 2018 Travelex Ltd

package atm.entity;

import static javax.persistence.GenerationType.AUTO;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class CustomerEntity {
    //TODO first name, last name, age, birthday

    @Id
    @Column
    private UUID id;

    @Column
    private int pin;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

}

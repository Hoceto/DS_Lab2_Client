package DepositTest;


import UserTest.User;

import lombok.Data;


import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Data
public class DepositAccount {
    private UUID depositId;
    private int balance;
    private Date openingDate;
    private UUID ownerId;
    private User owner;

}

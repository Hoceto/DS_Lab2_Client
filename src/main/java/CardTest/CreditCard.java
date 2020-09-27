package CardTest;

import UserTest.User;
import lombok.Data;

import java.util.UUID;

@Data
public class CreditCard {
    private UUID cardId;
    private String ccNum;
    private int expMonth;
    private int expYear;
    private int CVC;
    private int balance;
    private User owner;
    private UUID ownerId;
}

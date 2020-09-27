package UserTest;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class User {
    private UUID userId;
    private int userBalance;
    private String userName;
    private String userSurname;
    private String userPhoneNumber;
    private Date birthdayDate;
    private String userPassportId;
    private String userTin;
    private String userEmail;
}

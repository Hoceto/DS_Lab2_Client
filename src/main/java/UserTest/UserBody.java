package UserTest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserBody {
    String userName;
    String userSurname;
    String phoneNumber;
    String birthdayDate;
    String passportId;
    String userTin;
    String userEmail;
}

package CardTest;

import UserTest.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CardBody {
    String cardNum;
    int regMonth;
    int regYear;
    int CVC;
    UUID ownerId;
    UUID workerId;
}
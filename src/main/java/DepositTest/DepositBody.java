package DepositTest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DepositBody {
    int balance;
    String openingDate;
    UUID ownerId;
    UUID workerId;
}

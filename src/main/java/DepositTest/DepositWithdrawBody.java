package DepositTest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DepositWithdrawBody {
    UUID depositId;
    UUID workerId;
    String withdrawDate;
}

package CardTest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CardOperationBody {
    UUID cardId;
    UUID workerId;
    int balanceChange;
}

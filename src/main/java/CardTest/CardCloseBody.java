package CardTest;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class CardCloseBody {
    UUID cardId;
}

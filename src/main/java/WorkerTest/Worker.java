package WorkerTest;

import lombok.Data;

import java.util.UUID;

@Data
public class Worker {
    private UUID workerId;
    private String workerName;
    private String workerSurname;
    private int wage;
    private String positionName;
}

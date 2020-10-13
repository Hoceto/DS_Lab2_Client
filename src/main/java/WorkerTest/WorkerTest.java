package WorkerTest;

import Request.RequestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.io.IOException;

public class WorkerTest {
    private String requestUrl = "http://localhost:8095/worker";

    public void generateUser() throws IOException {
        WorkerBody workerBody = new WorkerBody("Super", "Worker");

        RequestService postUser = RequestService.builder()
                .type(new HttpPost(requestUrl))
                .body(workerBody)
                .response(Worker.class)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(postUser.send());
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        Gson printGson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(printGson.toJson(jsonElement));

    }

    public Worker getWorker() throws IOException{
        RequestService getUser = RequestService.builder()
                .type(new HttpGet(requestUrl))
                .body(null)
                .response(Worker[].class)
                .build();
        Worker[] workers = (Worker[]) getUser.send();
        return workers[0];
    }

    public void testWorkerService() throws IOException {
        System.out.println("WORKER SERVICE TEST");
        generateUser();
    }
}

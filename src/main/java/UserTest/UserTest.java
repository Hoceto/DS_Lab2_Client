package UserTest;

import Request.RequestService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

public class UserTest {
    private String requestUrl = "http://localhost:8095/user";

    public void generateUser() throws IOException {
        UserBody userBody = new UserBody("Lionel", "Messi", "+3804563", "2000-11-08", "11111", "22222", "messi@lionel");

        RequestService postUser = RequestService.builder()
                .type(new HttpPost(requestUrl))
                .body(userBody)
                .response(User.class)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(postUser.send());
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        Gson printGson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(printGson.toJson(jsonElement));

    }

    public User getUser() throws IOException{
        RequestService getUser = RequestService.builder()
                .type(new HttpGet(requestUrl))
                .body(null)
                .response(User[].class)
                .build();
        User[] users = (User[]) getUser.send();
        return users[0];
    }

    public void testUserService() throws IOException {
        System.out.println("USER SERVICE TEST");
        generateUser();
    }
}

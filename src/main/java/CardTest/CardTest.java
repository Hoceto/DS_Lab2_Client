package CardTest;

import Request.RequestService;
import UserTest.User;
import WorkerTest.Worker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public class CardTest {
    private String requestUrl = "http://localhost:8090/banking/creditcard";

    public UUID generateCard(User owner, Worker exec) throws IOException {
        CardBody userBody = new CardBody("4714 3142 5123 6611", 9, 2020, 123,
                owner.getUserId(), exec.getWorkerId());

        RequestService postCard = RequestService.builder()
                .type(new HttpPost(requestUrl))
                .body(userBody)
                .response(CreditCard.class)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(postCard.send());
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        Gson printGson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(printGson.toJson(jsonElement));

        return getCard().getCardId();
    }

    public CreditCard getCard() throws IOException {
        RequestService getUser = RequestService.builder()
                .type(new HttpGet(requestUrl))
                .body(null)
                .response(CreditCard[].class)
                .build();
        CreditCard[] cards = (CreditCard[]) getUser.send();
        return cards[0];
    }

    private void changeCardBalance(UUID cardId, UUID workerId, int balanceChange) throws IOException {
        CardOperationBody cardBody = new CardOperationBody(cardId, workerId, balanceChange);
        RequestService changeCardBalance = RequestService.builder()
                .type(new HttpPost(requestUrl+"/change_balance"))
                .body(cardBody)
                .response(CreditCard.class)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(changeCardBalance.send());
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        Gson printGson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(printGson.toJson(jsonElement));

    }

    private void closeCard(UUID cardId) throws IOException {
        CardCloseBody cardBody = new CardCloseBody(cardId);
        RequestService closeCard = RequestService.builder()
                .type(new HttpDelete(requestUrl))
                .body(cardBody)
                .response(CreditCard.class)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(closeCard.send());
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        Gson printGson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(printGson.toJson(jsonElement));
    }


    public void testCardService(User owner, Worker exec) throws IOException {
        System.out.println("CARD SERVICE TEST");
        UUID toChange = generateCard(owner, exec);
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        // Reading data using readLine
        //String name = reader.readLine();
        changeCardBalance(toChange, exec.getWorkerId(), 5000);
    }


}

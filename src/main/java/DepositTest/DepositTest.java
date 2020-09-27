package DepositTest;

import Request.RequestService;
import UserTest.User;
import UserTest.UserTest;
import WorkerTest.Worker;
import WorkerTest.WorkerTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.UUID;

public class DepositTest {

    @Autowired
    UserTest userService;

    @Autowired
    WorkerTest workerService;

    private String requestUrl = "http://localhost:8090/banking/deposit";

    private String openingDate = "2020-01-01";

    public UUID generateDeposit(User owner, Worker exec) throws IOException {
        int money = owner.getUserBalance()/10;
        DepositBody depositBody = new DepositBody(money, openingDate,owner.getUserId(), exec.getWorkerId());

        RequestService postDeposit = RequestService.builder()
                .type(new HttpPost(requestUrl))
                .body(depositBody)
                .response(DepositAccount.class)
                .build();

        Gson gson = new Gson();
        String json = gson.toJson(postDeposit.send());
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        Gson printGson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(printGson.toJson(jsonElement));
        return getDeposit().getDepositId();

    }

    private void withdrawDeposit(UUID toWithdrawId, UUID execId) throws IOException {
        DepositWithdrawBody withdrawBody = new DepositWithdrawBody(toWithdrawId, execId, "2020-11-01");
        RequestService withdrawDeposit = RequestService.builder()
                .type(new HttpPut(requestUrl))
                .body(withdrawBody)
                .response(DepositAccount.class)
                .build();
        Gson gson = new Gson();
        String json = gson.toJson(withdrawDeposit.send());
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        Gson printGson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(printGson.toJson(jsonElement));
    }

    public DepositAccount getDeposit() throws IOException{
        RequestService getDeposit = RequestService.builder()
                .type(new HttpGet(requestUrl))
                .body(null)
                .response(DepositAccount[].class)
                .build();
        DepositAccount[] deposits = (DepositAccount[]) getDeposit.send();
        return deposits[0];
    }

    public void testDepositService(User owner, Worker exec) throws IOException {
        System.out.println("DEPOSIT SERVICE TEST");
        UUID toWithdraw = generateDeposit(owner, exec);
        withdrawDeposit(toWithdraw, exec.getWorkerId());
    }


}

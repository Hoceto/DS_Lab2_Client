package com.resttest;

import CardTest.CardTest;
import DepositTest.DepositTest;
import UserTest.UserTest;
import UserTest.User;
import WorkerTest.Worker;
import WorkerTest.WorkerTest;

//@SpringBootApplication
public class TestingApplication {

    public static void main(String[] args) {
        try{
            UserTest userTest = new UserTest();
            userTest.testUserService();

            WorkerTest workerTest = new WorkerTest();
            workerTest.testWorkerService();

            DepositTest depositTest = new DepositTest();
            depositTest.testDepositService(userTest.getUser(), workerTest.getWorker());
//
//            CardTest cardTest = new CardTest();
//            cardTest.testCardService(userTest.getUser(), workerTest.getWorker());
        }catch (Exception e){
            e.printStackTrace();
        }



        //SpringApplication.run(TestingApplication.class, args);
    }

}

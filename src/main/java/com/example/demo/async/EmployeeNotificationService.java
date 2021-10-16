package com.example.demo.async;

import com.example.demo.async.dao.EmployeeDao;
import com.example.demo.async.dto.Employee;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class EmployeeNotificationService {

/*
 * 1. Prepare Employee Object
 * 2. Filter for new joiner
 * 3. Filter for pending learning 
 * 4. Collect email ids
 * 5. Send email
 * */
    public  CompletableFuture<Void> retrieveEmployee() {

        Executor executor=Executors.newFixedThreadPool(5);

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("fetchEmployee : " + Thread.currentThread().getName());
            return EmployeeDao.fetchEmployees();
        },executor).thenApplyAsync((employees) -> {
            System.out.println("filter new joiner employee  : " + Thread.currentThread().getName());
            return employees.stream()
                    .filter(employee -> "TRUE".equals(employee.getNewJoiner()))
                    .collect(Collectors.toList());
        },executor).thenApplyAsync((employees) -> {
            System.out.println("filter training not complete employee  : " + Thread.currentThread().getName());
            return employees.stream()
                    .filter(employee -> "TRUE".equals(employee.getLearningPending()))
                    .collect(Collectors.toList());
        },executor).thenApplyAsync((employees) -> {
            System.out.println("get emails  : " + Thread.currentThread().getName());
            return employees.stream().map(Employee::getEmail).collect(Collectors.toList());
        },executor).thenAcceptAsync((emails) -> {
            System.out.println("send email  : " + Thread.currentThread().getName());
            emails.forEach(EmployeeNotificationService::sendEmail);
        },executor);
        return voidCompletableFuture;
    }


    public static void sendEmail(String email) {
        System.out.println("sending training reminder email to : " + email);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        EmployeeNotificationService service=new EmployeeNotificationService();
        service.retrieveEmployee().get();
    }
}

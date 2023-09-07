package org.stackdrive.audit.logger;

import org.stackdrive.audit.dto.AuditDTO;

import java.util.concurrent.CompletableFuture;

public class StackDriveLogLogger {

    private final String stackDriveLogUrl;

    public StackDriveLogLogger(String stackDriveLogUrl) {
        this.stackDriveLogUrl = stackDriveLogUrl;
    }

    public void asyncSend(AuditDTO auditDTO) {
        StackDriveLogRequest stackDriveLogRequest = new StackDriveLogRequest(stackDriveLogUrl, auditDTO);
        final CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(stackDriveLogRequest);
        completableFuture.exceptionally(ex -> {
            StackDriveLogRequest httpStackDriveLogRequest = new StackDriveLogRequest(stackDriveLogUrl, auditDTO);
            return httpStackDriveLogRequest.get();
        });
    }
}
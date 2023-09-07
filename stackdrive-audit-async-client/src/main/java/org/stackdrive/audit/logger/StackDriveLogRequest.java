package org.stackdrive.audit.logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stackdrive.audit.dto.AuditDTO;
import org.stackdrive.audit.http.StackDriveLogClient;

import java.util.function.Supplier;

public class StackDriveLogRequest implements Supplier<String> {

    private static final Logger log = LoggerFactory.getLogger(StackDriveLogRequest.class);

    private final String url;

    private final AuditDTO auditDTO;

    public StackDriveLogRequest(String url, AuditDTO auditDTO) {
        this.url = url;
        this.auditDTO = auditDTO;
    }

    @Override
    public String get() {
        try {
            // Создаем клиента, указываем полную ссылку на StackDrive логер
            StackDriveLogClient stackDriveLogClient = new StackDriveLogClient(url);

            // Отправляем запрос на сервер
            stackDriveLogClient.send(auditDTO);
            return "OK";
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return "FAIL";
    }
}
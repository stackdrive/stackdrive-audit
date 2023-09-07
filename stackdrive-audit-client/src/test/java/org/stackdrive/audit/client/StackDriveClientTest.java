package org.stackdrive.audit.client;

import org.stackdrive.audit.dto.AuditDTO;
import org.stackdrive.audit.dto.InfoDTO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StackDriveClientTest {

    @Test
    @Disabled("Надо доработать с добавлением wired mock")
    void send() throws Exception {
        AuditDTO auditDTO = new AuditDTO();
        auditDTO.setCode("INSTALL");
        auditDTO.setLogin("employeesigmaemail");
        auditDTO.setProject("DevX Plugin");

        Map<String, Object> obj = new HashMap<>();
        obj.put("string", "AAA");
        obj.put("digit", 1);
        auditDTO.setExtension(obj);

        auditDTO.setVersion("1.8.0");

        assertNotNull(auditDTO);

        StackDriveClient stackDriveClient = new StackDriveClient("");
        InfoDTO send = stackDriveClient.send(auditDTO);
        assertEquals("PUBLIC", send.getDeployment());
    }
}

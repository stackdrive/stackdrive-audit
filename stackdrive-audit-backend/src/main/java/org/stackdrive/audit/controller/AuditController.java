package org.stackdrive.audit.controller;

import org.stackdrive.audit.dto.AuditDTO;
import org.stackdrive.audit.dto.InfoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditController {

    private static final Logger logger = LoggerFactory.getLogger(AuditController.class);

    @PutMapping(path = "audit")
    public InfoDTO addAuditAction(@RequestBody AuditDTO auditDTO) {
        logger.trace("addAuditAction >>> {} {} {}", auditDTO.getCode(), auditDTO.getLogin(), auditDTO.getProject());
        logger.trace("addAuditAction <<< {} {} {}", auditDTO.getCode(), auditDTO.getLogin(), auditDTO.getProject());
        return new InfoDTO("PUBLIC");
    }
}

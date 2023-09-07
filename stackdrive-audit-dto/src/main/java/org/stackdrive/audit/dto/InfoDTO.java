package org.stackdrive.audit.dto;

public class InfoDTO {

    private String deployment;

    public InfoDTO() {
    }

    public InfoDTO(String deployment) {
        this.deployment = deployment;
    }

    public String getDeployment() {
        return deployment;
    }

    public void setDeployment(String deployment) {
        this.deployment = deployment;
    }
}
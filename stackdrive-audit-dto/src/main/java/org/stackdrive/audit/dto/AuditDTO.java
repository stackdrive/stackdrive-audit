package org.stackdrive.audit.dto;

public class AuditDTO {

    //    Код эвента
    private String code;

    //    Има проекта
    private String project;

    //    Логин
    private String login;

    //    Расширеные данные по действию пользователя
    private Object extension;

    //    Версия
    private String version;

    //    Окружение
    private Environment env;

    //    Версия окружения
    private String envVersion;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Object getExtension() {
        return extension;
    }

    public void setExtension(Object extension) {
        this.extension = extension;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    public String getEnvVersion() {
        return envVersion;
    }

    public void setEnvVersion(String envVersion) {
        this.envVersion = envVersion;
    }
}

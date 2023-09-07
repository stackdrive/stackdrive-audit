# StackDrive.Log

Проект для сбора логов из StackDrive

## Версия

Актуальный релиз __0.29__

## Сборка из исходников

    mvn clean package

## Подключение http-клиента

Подключаем Maven зависимости к проекту

    <dependency>
        <groupId>org.stackdrive.audit</groupId>
        <artifactId>stackdrive-audit-dto</artifactId>
        <version>0.29</version>
    </dependency>
    
    <dependency>
        <groupId>org.stackdrive.audit</groupId>
        <artifactId>stackdrive-audit-client</artifactId>
        <version>0.29</version>
    </dependency>
    
Подключаем Gradle зависимости к проекту

    compile 'org.stackdrive.audit:stackdrive-audit-dto:0.29' 
    compile 'org.stackdrive.audit:stackdrive-audit-client:0.29' 
    
Реализуем запросы к серверу в коде

    // Создаем клиента, указываем полную ссылку на StackDrive логер
    StackDriveClient stackDriveClient = new StackDriveClient("http://localhost:8080/audit");

    // Создаем и заполняем транспортный объект
    AuditDTO auditDTO = new AuditDTO();
    auditDTO.setCode("INSTALL");
    auditDTO.setLogin("10000000");
    auditDTO.setProject("DevX Plugin");
    
    // Так же можно добавить расширенный объект
    // Например Map или DTO
    Map<String, Object> obj = new HashMap<>();
    obj.put("string", "AAA");
    obj.put("digit", 1);
    auditDTO.setExtension(obj);
    
    // Версия продукта
    auditDTO.setVersion("1.8.0");

    // Отправляем запрос на сервер
    stackDriveClient.send(auditDTO);
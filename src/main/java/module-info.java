module com.liceolapaz.dam.jvv {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.base;
    requires javafx.graphics;
    requires java.sql;
    requires mysql.connector.j;

    // Permitir que JavaFX acceda a los controladores por reflexión
    opens com.liceolapaz.dam.jvv to javafx.fxml;
    opens com.liceolapaz.dam.jvv.model to javafx.fxml;
    opens com.liceolapaz.dam.jvv.db to javafx.fxml;
    opens com.liceolapaz.dam.jvv.controller to javafx.fxml;



    // Exportar el paquete principal
    exports com.liceolapaz.dam.jvv;
    exports com.liceolapaz.dam.jvv.model;
    exports com.liceolapaz.dam.jvv.db;
    exports com.liceolapaz.dam.jvv.controller;
}

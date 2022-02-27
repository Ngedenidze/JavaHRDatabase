module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.java;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}
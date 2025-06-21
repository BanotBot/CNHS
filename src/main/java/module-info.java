module com.besere.cnhs {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.besere.model to javafx.fxml;
    opens com.besere.controller to javafx.fxml;
    opens com.besere.database to java.sql;
    opens com.besere.StudentService to javafx.fxml;
    opens com.besere.uicontroller to javafx.fxml;

    exports com.besere.model;
    exports com.besere.controller;
    exports com.besere.database;
    exports com.besere.StudentService;
    exports com.besere.uicontroller;
}


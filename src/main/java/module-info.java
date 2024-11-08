module com.adamo.produitfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;
    requires java.sql;


    opens com.adamo.produitfx to javafx.fxml;
    exports com.adamo.produitfx;
    exports com.adamo.produitfx.controllers;
    opens com.adamo.produitfx.controllers to javafx.fxml;
}
module com.adamo.produitfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.adamo.produitfx to javafx.fxml;
    exports com.adamo.produitfx;
}
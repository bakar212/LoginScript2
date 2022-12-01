module com.example.loginscript2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.loginscript2 to javafx.fxml;
    exports com.example.loginscript2;
}
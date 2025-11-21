module com.example.practicadijvv {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.practicadijvv to javafx.fxml;
    exports com.example.practicadijvv;
}
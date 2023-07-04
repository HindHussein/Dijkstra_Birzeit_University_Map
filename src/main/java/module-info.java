module com.example.thirdalgoproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.thirdalgoproject to javafx.fxml;
    exports com.example.thirdalgoproject;
}
module com.example.projet {
    requires javafx.controls;
    requires javafx.fxml;


    opens ColorAddict to javafx.fxml;
    exports ColorAddict;
    exports ColorAddict.Enums;
    opens ColorAddict.Enums to javafx.fxml;
}
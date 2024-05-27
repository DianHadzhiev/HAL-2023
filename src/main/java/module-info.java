module org.chat.hal {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens org.chat.hal2023 to javafx.fxml;
    exports org.chat.hal2023;
}
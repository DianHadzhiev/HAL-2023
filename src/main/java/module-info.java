module org.chat.hal {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.chat.hal2023 to javafx.fxml;
    exports org.chat.hal2023;
}
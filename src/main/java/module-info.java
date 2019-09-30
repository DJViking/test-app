
module no.spacetec.test.app {

    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens no.spacetec.test.app to javafx.fxml;

    exports no.spacetec.test.app;
}

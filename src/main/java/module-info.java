module com.jmsweb.fxml.conway.gol {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires javafx.fxml;

    opens com.jmsweb.fxml.conway.gol to javafx.fxml;
    opens com.jmsweb.fxml.conway.gol.controller to javafx.fxml;
    opens com.jmsweb.fxml.conway.gol.impl to javafx.fxml;
    exports com.jmsweb.fxml.conway.gol;
}

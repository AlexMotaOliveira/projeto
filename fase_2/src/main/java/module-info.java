module com.impacta.pessoa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.impacta.pessoa to javafx.fxml;
    exports com.impacta.pessoa;
}
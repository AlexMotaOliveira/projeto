module com.impacta.pessoa.fase_2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.impacta.pessoa to javafx.fxml;
    exports com.impacta.pessoa;
}
module edu.bs.proyectobd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens edu.bs.proyectobd to javafx.fxml;
    exports edu.bs.proyectobd;
    exports edu.bs.proyectobd.controladodor;
    opens edu.bs.proyectobd.controladodor to javafx.fxml;
}
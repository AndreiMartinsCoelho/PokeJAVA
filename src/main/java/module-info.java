module com.example.pokedex_com_sql {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.pokedex_com_sql to javafx.fxml;
    exports com.example.pokedex_com_sql.Model;
    opens com.example.pokedex_com_sql.Model to javafx.fxml;
    exports com.example.pokedex_com_sql.Controller;
    opens com.example.pokedex_com_sql.Controller to javafx.fxml;
    exports com.example.pokedex_com_sql;
}
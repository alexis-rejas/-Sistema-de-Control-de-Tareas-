module org.example.tarea {
  requires javafx.controls;
  requires javafx.fxml;
  requires java.desktop;


  opens org.example.tarea to javafx.fxml;
  exports org.example.tarea;
}

package vallegrade.edu.pe.model;

public class Tarea {
  private String nombre;
  private String estado;

  public Tarea(String nombre) {
    this.nombre = nombre;
    this.estado = "Pendiente";
  }

  public String getNombre() { return nombre; }
  public String getEstado() { return estado; }
  public void setEstado(String estado) { this.estado = estado; }

  public boolean isCompletada() {
    return "Completada".equals(estado);
  }
}

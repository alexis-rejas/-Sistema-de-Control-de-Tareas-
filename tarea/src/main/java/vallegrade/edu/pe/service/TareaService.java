package vallegrade.edu.pe.service;

import vallegrade.edu.pe.model.Tarea;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TareaService {
  private List<Tarea> tareas = new ArrayList<>();

  public void agregar(String nombre) {
    tareas.add(new Tarea(nombre));
  }

  public void eliminar(int indice) {
    tareas.remove(indice);
  }

  public void completar(int indice) {
    tareas.get(indice).setEstado("Completada");
  }

  public List<Tarea> getTareas() {
    return tareas;
  }

  public List<Tarea> filtrar(String filtro) {
    if ("Todas".equals(filtro)) return tareas;
    return tareas.stream()
      .filter(t -> t.getEstado().equals(filtro))
      .collect(Collectors.toList());
  }

  public int contarTotal() { return tareas.size(); }
}

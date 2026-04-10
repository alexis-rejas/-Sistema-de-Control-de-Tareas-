package vallegrade.edu.pe.controller;

import vallegrade.edu.pe.model.Tarea;
import vallegrade.edu.pe.service.TareaService;
import vallegrade.edu.pe.view.MainView;

import javax.swing.*;
import java.util.List;

public class TareaController {
  private TareaService service;
  private MainView view;

  public TareaController(TareaService service, MainView view) {
    this.service = service;
    this.view = view;
    initListeners();
  }

  private void initListeners() {

    view.getBtnAgregar().addActionListener(e -> {
      String nombre = view.getInputTarea().trim();
      if (nombre.isEmpty()) {
        JOptionPane.showMessageDialog(view, "El campo no puede estar vacío.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
      }
      service.agregar(nombre);
      view.limpiarInput();
      actualizarTabla();
      JOptionPane.showMessageDialog(view, "Tarea agregada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    });

    view.getBtnEliminar().addActionListener(e -> {
      int fila = view.getFilaSeleccionada();
      if (fila < 0) {
        JOptionPane.showMessageDialog(view, "Selecciona una tarea para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
      }
      int confirm = JOptionPane.showConfirmDialog(view, "¿Eliminar tarea?", "Confirmar", JOptionPane.YES_NO_OPTION);
      if (confirm == JOptionPane.YES_OPTION) {
        service.eliminar(fila);
        actualizarTabla();
      }
    });

    view.getBtnCompletar().addActionListener(e -> {
      int fila = view.getFilaSeleccionada();
      if (fila < 0) {
        JOptionPane.showMessageDialog(view, "Selecciona una tarea para completar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        return;
      }
      service.completar(fila);
      actualizarTabla();
      JOptionPane.showMessageDialog(view, "✔ Tarea marcada como completada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    });

    view.getFiltroCombo().addActionListener(e -> actualizarTabla());
  }

  private void actualizarTabla() {
    String filtro = view.getFiltroSeleccionado();
    List<Tarea> lista = service.filtrar(filtro);
    view.actualizarTabla(lista, service.contarTotal());
  }
}

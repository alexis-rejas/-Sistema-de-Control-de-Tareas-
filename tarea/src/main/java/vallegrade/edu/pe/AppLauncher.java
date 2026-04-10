package vallegrade.edu.pe;

import vallegrade.edu.pe.controller.TareaController;
import vallegrade.edu.pe.service.TareaService;
import vallegrade.edu.pe.view.MainView;

import javax.swing.*;

public class AppLauncher {
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception ignored) {}

    SwingUtilities.invokeLater(() -> {
      TareaService service = new TareaService();
      MainView view = new MainView();
      new TareaController(service, view);
      view.setVisible(true);
    });
  }
}

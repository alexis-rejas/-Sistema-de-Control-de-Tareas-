package vallegrade.edu.pe.view;

import vallegrade.edu.pe.model.Tarea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainView extends JFrame {

  private JTextField txtTarea;
  private JButton btnAgregar, btnEliminar, btnCompletar;
  private JTable tabla;
  private DefaultTableModel modeloTabla;
  private JLabel lblContador;
  private JComboBox<String> filtroCombo;

  public MainView() {
    setTitle("Gestor de Tareas");
    setSize(600, 450);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    initUI();
  }

  private void initUI() {
    // Colores
    Color colorFondo     = new Color(245, 245, 250);
    Color colorAzul      = new Color(59, 130, 246);
    Color colorVerde     = new Color(34, 197, 94);
    Color colorRojo      = new Color(239, 68, 68);
    Color colorTexto     = new Color(30, 30, 30);

    getContentPane().setBackground(colorFondo);
    setLayout(new BorderLayout(10, 10));

    // ── Panel superior ──
    JPanel panelTop = new JPanel(new BorderLayout(8, 0));
    panelTop.setBackground(colorFondo);
    panelTop.setBorder(BorderFactory.createEmptyBorder(15, 15, 5, 15));

    JLabel titulo = new JLabel("Mis Tareas");
    titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
    titulo.setForeground(colorTexto);

    txtTarea = new JTextField();
    txtTarea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    txtTarea.setBorder(BorderFactory.createCompoundBorder(
      BorderFactory.createLineBorder(new Color(200, 200, 200)),
      BorderFactory.createEmptyBorder(5, 8, 5, 8)));

    btnAgregar = crearBoton("Agregar", colorAzul);

    JPanel inputPanel = new JPanel(new BorderLayout(6, 0));
    inputPanel.setBackground(colorFondo);
    inputPanel.add(txtTarea, BorderLayout.CENTER);
    inputPanel.add(btnAgregar, BorderLayout.EAST);

    panelTop.add(titulo, BorderLayout.NORTH);
    panelTop.add(inputPanel, BorderLayout.CENTER);

    // ── Tabla ──
    modeloTabla = new DefaultTableModel(new String[]{"Tarea", "Estado"}, 0) {
      public boolean isCellEditable(int r, int c) { return false; }
    };
    tabla = new JTable(modeloTabla);
    tabla.setFont(new Font("Segoe UI", Font.PLAIN, 13));
    tabla.setRowHeight(28);
    tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 13));
    tabla.getTableHeader().setBackground(colorAzul);
    tabla.getTableHeader().setForeground(Color.WHITE);
    tabla.setSelectionBackground(new Color(219, 234, 254));

    JScrollPane scroll = new JScrollPane(tabla);
    scroll.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));

    // ── Panel inferior ──
    JPanel panelBot = new JPanel(new BorderLayout(10, 0));
    panelBot.setBackground(colorFondo);
    panelBot.setBorder(BorderFactory.createEmptyBorder(5, 15, 15, 15));

    lblContador = new JLabel("Total: 0");
    lblContador.setFont(new Font("Segoe UI", Font.BOLD, 13));
    lblContador.setForeground(colorTexto);

    filtroCombo = new JComboBox<>(new String[]{"Todas", "Pendiente", "Completada"});
    filtroCombo.setFont(new Font("Segoe UI", Font.PLAIN, 13));

    btnCompletar = crearBoton("✔ Completar", colorVerde);
    btnEliminar  = crearBoton("Eliminar", colorRojo);

    JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 6, 0));
    botonesPanel.setBackground(colorFondo);
    botonesPanel.add(new JLabel("Filtrar:"));
    botonesPanel.add(filtroCombo);
    botonesPanel.add(btnCompletar);
    botonesPanel.add(btnEliminar);

    panelBot.add(lblContador, BorderLayout.WEST);
    panelBot.add(botonesPanel, BorderLayout.EAST);

    add(panelTop, BorderLayout.NORTH);
    add(scroll,   BorderLayout.CENTER);
    add(panelBot, BorderLayout.SOUTH);
  }

  private JButton crearBoton(String texto, Color color) {
    JButton btn = new JButton(texto);
    btn.setBackground(color);
    btn.setForeground(Color.WHITE);
    btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
    btn.setFocusPainted(false);
    btn.setBorderPainted(false);
    btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    btn.setBorder(BorderFactory.createEmptyBorder(7, 16, 7, 16));
    return btn;
  }

  // ── Métodos para el Controller ──
  public String getInputTarea()       { return txtTarea.getText(); }
  public void limpiarInput()          { txtTarea.setText(""); }
  public int getFilaSeleccionada()    { return tabla.getSelectedRow(); }
  public JButton getBtnAgregar()      { return btnAgregar; }
  public JButton getBtnEliminar()     { return btnEliminar; }
  public JButton getBtnCompletar()    { return btnCompletar; }
  public JComboBox<String> getFiltroCombo() { return filtroCombo; }
  public String getFiltroSeleccionado() { return (String) filtroCombo.getSelectedItem(); }

  public void actualizarTabla(List<Tarea> tareas, int total) {
    modeloTabla.setRowCount(0);
    for (Tarea t : tareas) {
      modeloTabla.addRow(new Object[]{t.getNombre(), t.getEstado()});
    }
    lblContador.setText("Total: " + total);
  }
}

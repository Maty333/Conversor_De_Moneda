package conversor;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConversorPrincipalGUI extends JFrame {
	private JButton conversorMonedasButton;
	private JButton conversorTemperaturasButton;
	private JButton cerrarButton;

	public ConversorPrincipalGUI() {

		setTitle("Conversor Principal");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);

		conversorMonedasButton = new JButton("Conversor de Monedas");
		conversorMonedasButton.setBounds(100, 50, 200, 40);
		conversorMonedasButton.addActionListener(this::abrirConversorMonedas);
		add(conversorMonedasButton);

		conversorTemperaturasButton = new JButton("Conversor de Temperaturas");
		conversorTemperaturasButton.setBounds(100, 100, 200, 40);
		conversorTemperaturasButton.addActionListener(this::abrirConversorTemperaturas);
		add(conversorTemperaturasButton);

		cerrarButton = new JButton("Cerrar");
		cerrarButton.setBounds(150, 150, 100, 40);
		cerrarButton.addActionListener(this::cerrarAplicacion);
		add(cerrarButton);
	}

	private void cerrarAplicacion(ActionEvent e) {
		int opcion = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Confirmar salida",
				JOptionPane.YES_NO_OPTION);

		if (opcion == JOptionPane.YES_OPTION) {

			for (java.awt.Window window : java.awt.Window.getWindows()) {
				window.dispose();
			}
			System.exit(0);
		}
	}

	private void abrirConversorMonedas(ActionEvent e) {
		ConversorMonedaGUI conversorMonedaGUI = new ConversorMonedaGUI();
		conversorMonedaGUI.setVisible(true);
		setVisible(false);
	}

	private void abrirConversorTemperaturas(ActionEvent e) {
		ConversorTemperaturasGUI conversorTemperaturasGUI = new ConversorTemperaturasGUI();
		conversorTemperaturasGUI.setVisible(true);
		setVisible(false);
	}

	public static int mostrarDialogoContinuar() {
		String[] opciones = { "Realizar otra conversión", "Volver al menú principal", "Salir" };
		return JOptionPane.showOptionDialog(null, "¿Qué desea hacer?", "Continuar o Salir", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
	}

	public void mostrarVentanaPrincipal() {
		setVisible(true);
	}

	public void ocultarVentana() {
		this.setVisible(false);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ConversorPrincipalGUI conversorPrincipalGUI = new ConversorPrincipalGUI();
			conversorPrincipalGUI.setVisible(true);
		});
	}
}

package conversor;

import javax.swing.*;

public class ConversorMonedaGUI extends JFrame {

	private JComboBox<String> monedaOrigenCombo;
	private JComboBox<String> monedaDestinoCombo;

	private JTextField cantidadField;
	private JButton realizarConversionButton;

	public ConversorMonedaGUI() {

		// Configuración de la ventana
		setTitle("Conversor de Moneda");
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);

		JLabel etiquetaMonedaOrigen = new JLabel("Moneda de Origen:");
		etiquetaMonedaOrigen.setBounds(50, 50, 300, 30);
		add(etiquetaMonedaOrigen);

		String[] opcionesMonedasOrigen = { "Pesos Argentinos", "Dolar", "Euros", "Libras Esterlinas", "Yen Japones",
				"Won sur-coreano" };
		monedaOrigenCombo = new JComboBox<>(opcionesMonedasOrigen);
		monedaOrigenCombo.setBounds(50, 80, 300, 30);
		monedaOrigenCombo.addActionListener(e -> actualizarMonedasDestino());
		add(monedaOrigenCombo);

		JLabel etiquetaMonedaDestino = new JLabel("Moneda de Destino:");
		etiquetaMonedaDestino.setBounds(50, 120, 300, 30);
		add(etiquetaMonedaDestino);

		String[] opcionesMonedasDestino = { "Dolar", "Euros", "Libras Esterlinas", "Yen Japones", "Won sur-coreano" };
		monedaDestinoCombo = new JComboBox<>(opcionesMonedasDestino);
		monedaDestinoCombo.setBounds(50, 150, 300, 30);
		add(monedaDestinoCombo);

		cantidadField = new JTextField();
		cantidadField.setBounds(50, 200, 300, 30);
		add(cantidadField);

		realizarConversionButton = new JButton("Realizar Conversión");
		realizarConversionButton.setBounds(120, 350, 150, 40);
		realizarConversionButton.addActionListener(e -> realizarConversion());
		add(realizarConversionButton);
		cantidadField.addActionListener(e -> realizarConversion());
	}

	private void actualizarMonedasDestino() {
		String monedaOrigenSeleccionada = (String) monedaOrigenCombo.getSelectedItem();
		String[] opcionesMonedasDestino;

		if (monedaOrigenSeleccionada.equals("Pesos Argentinos")) {
			opcionesMonedasDestino = new String[] { "Dolar", "Euros", "Libras Esterlinas", "Yen Japones",
					"Won sur-coreano" };
		} else {
			opcionesMonedasDestino = new String[] { "Pesos Argentinos" };
		}

		monedaDestinoCombo.setModel(new DefaultComboBoxModel<>(opcionesMonedasDestino));
	}

	private void realizarConversion() {
		String monedaSeleccionada = (String) monedaOrigenCombo.getSelectedItem();
		String monedaDestinoSeleccionada = (String) monedaDestinoCombo.getSelectedItem();
		String cantidadTexto = cantidadField.getText();

		try {
			double cantidad = Double.parseDouble(cantidadTexto);

			ConversorDivisas conversor = new ConversorDivisas();
			double resultado = 0.0;

			if (monedaSeleccionada.equals("Pesos Argentinos")) {
				resultado = conversor.convertirHaciaPesos(cantidad, monedaDestinoSeleccionada);
			} else if (monedaDestinoSeleccionada.equals("Pesos Argentinos")) {
				resultado = conversor.convertirDesdePesos(cantidad, monedaSeleccionada);
			} else {
				resultado = conversor.convertirDesdePesos(cantidad, monedaSeleccionada);
				resultado = conversor.convertirHaciaPesos(resultado, monedaDestinoSeleccionada);
			}

			JOptionPane.showMessageDialog(null,
					cantidad + " " + monedaSeleccionada + " = " + resultado + " " + monedaDestinoSeleccionada,
					"Resultado de la conversión", JOptionPane.INFORMATION_MESSAGE);

		} catch (NumberFormatException ex) {
			mostrarError("Ingrese caracteres válidos (números)");
		} catch (IllegalArgumentException ex) {
			mostrarError("Opción inválida para la moneda de destino");
		}

		int opcion = JOptionPane.showOptionDialog(null, "¿Desea continuar con otra conversión?",
				"Continuar o Volver al menú principal", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, new String[] { "Sí", "No", "Volver al menú principal" }, "Sí");

		if (opcion == JOptionPane.YES_OPTION) {
			cantidadField.setText("");
			monedaOrigenCombo.setSelectedIndex(0);
			monedaDestinoCombo.setSelectedIndex(0);
		} else if (opcion == JOptionPane.NO_OPTION) {
			JOptionPane.showMessageDialog(null, "Programa finalizado");
			System.exit(0);
		} else {
			ConversorPrincipalGUI ventanaPrincipal = new ConversorPrincipalGUI();
			ventanaPrincipal.mostrarVentanaPrincipal();
			dispose();
		}

	}

	private void mostrarError(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ConversorMonedaGUI conversorGUI = new ConversorMonedaGUI();
			conversorGUI.setVisible(true);
		});
	}
}

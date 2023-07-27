package conversor;

import javax.swing.*;

public class ConversorTemperaturasGUI extends JFrame {

	private JComboBox<String> unidadOrigenCombo;
	private JComboBox<String> unidadDestinoCombo;
	private JTextField cantidadField;
	private JButton realizarConversionButton;

	public ConversorTemperaturasGUI() {

		setTitle("Conversor de Temperaturas");
		setSize(400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);

		JLabel etiquetaUnidadOrigen = new JLabel("Unidad de Origen:");
		etiquetaUnidadOrigen.setBounds(50, 50, 300, 30);
		add(etiquetaUnidadOrigen);

		String[] opcionesUnidadesOrigen = { "Celsius", "Fahrenheit", "Kelvin" };
		unidadOrigenCombo = new JComboBox<>(opcionesUnidadesOrigen);
		unidadOrigenCombo.setBounds(50, 80, 300, 30);
		add(unidadOrigenCombo);

		JLabel etiquetaUnidadDestino = new JLabel("Unidad de Destino:");
		etiquetaUnidadDestino.setBounds(50, 120, 300, 30);
		add(etiquetaUnidadDestino);

		String[] opcionesUnidadesDestino = { "Celsius", "Fahrenheit", "Kelvin" };
		unidadDestinoCombo = new JComboBox<>(opcionesUnidadesDestino);
		unidadDestinoCombo.setBounds(50, 150, 300, 30);
		add(unidadDestinoCombo);

		cantidadField = new JTextField();
		cantidadField.setBounds(50, 200, 300, 30);
		add(cantidadField);

		realizarConversionButton = new JButton("Realizar Conversión");
		realizarConversionButton.setBounds(120, 250, 150, 40);
		realizarConversionButton.addActionListener(e -> realizarConversion());
		add(realizarConversionButton);
	}

	private void realizarConversion() {
		String unidadOrigenSeleccionada = (String) unidadOrigenCombo.getSelectedItem();
		String unidadDestinoSeleccionada = (String) unidadDestinoCombo.getSelectedItem();
		String cantidadTexto = cantidadField.getText();

		try {
			double cantidad = Double.parseDouble(cantidadTexto);

			double resultado = 0.0;
			switch (unidadOrigenSeleccionada) {
			case "Celsius":
				resultado = ConversorTemperaturas.celsiusToFahrenheit(cantidad);
				break;
			case "Fahrenheit":
				resultado = ConversorTemperaturas.fahrenheitToCelsius(cantidad);
				break;
			case "Kelvin":
				resultado = ConversorTemperaturas.kelvinToCelsius(cantidad);
				break;
			}

			JOptionPane.showMessageDialog(null,
					cantidad + " " + unidadOrigenSeleccionada + " = " + resultado + " " + unidadDestinoSeleccionada,
					"Resultado de la conversión", JOptionPane.INFORMATION_MESSAGE);

		} catch (NumberFormatException ex) {
			mostrarError("Ingrese caracteres válidos (números)");
		} catch (IllegalArgumentException ex) {
			mostrarError("Opción inválida para la unidad de destino");
		}
		int opcion = JOptionPane.showOptionDialog(null, "¿Desea continuar con otra conversión?",
				"Continuar o Volver al menú principal", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, new String[] { "Sí", "No", "Volver al menú principal" }, "Sí");

		if (opcion == JOptionPane.YES_OPTION) {
			cantidadField.setText("");
			unidadOrigenCombo.setSelectedIndex(0);
			unidadDestinoCombo.setSelectedIndex(0);
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
			ConversorTemperaturasGUI conversorTemperaturasGUI = new ConversorTemperaturasGUI();
			conversorTemperaturasGUI.setVisible(true);
		});
	}
}

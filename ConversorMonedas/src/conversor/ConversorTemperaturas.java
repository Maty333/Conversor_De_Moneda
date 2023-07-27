package conversor;

public class ConversorTemperaturas {

	public static double convertirTemperatura(double temperatura, String escalaOrigen, String escalaDestino) {
		switch (escalaOrigen) {
		case "Celsius":
			switch (escalaDestino) {
			case "Fahrenheit":
				return celsiusToFahrenheit(temperatura);
			case "Kelvin":
				return celsiusToKelvin(temperatura);
			case "Celsius":
				return temperatura;
			default:
				throw new IllegalArgumentException("Escala de destino inválida");
			}
		case "Fahrenheit":
			switch (escalaDestino) {
			case "Celsius":
				return fahrenheitToCelsius(temperatura);
			case "Kelvin":
				return fahrenheitToKelvin(temperatura);
			case "Fahrenheit":
				return temperatura;
			default:
				throw new IllegalArgumentException("Escala de destino inválida");
			}
		case "Kelvin":
			switch (escalaDestino) {
			case "Celsius":
				return kelvinToCelsius(temperatura);
			case "Fahrenheit":
				return kelvinToFahrenheit(temperatura);
			case "Kelvin":
				return temperatura;
			default:
				throw new IllegalArgumentException("Escala de destino inválida");
			}
		default:
			throw new IllegalArgumentException("Escala de origen inválida");
		}
	}

	public static double celsiusToFahrenheit(double celsius) {
		return (celsius * 9 / 5) + 32;
	}

	public static double fahrenheitToCelsius(double fahrenheit) {
		return (fahrenheit - 32) * 5 / 9;
	}

	public static double celsiusToKelvin(double celsius) {
		return celsius + 273.15;
	}

	public static double kelvinToCelsius(double kelvin) {
		return kelvin - 273.15;
	}

	public static double fahrenheitToKelvin(double fahrenheit) {
		return (fahrenheit + 459.67) * 5 / 9;
	}

	public static double kelvinToFahrenheit(double kelvin) {
		return (kelvin * 9 / 5) - 459.67;
	}
}

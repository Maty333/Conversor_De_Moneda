package conversor;

public class ConversorDivisas {

	private static final double tasaCambioDolar = 0.0037;
	private static final double tasaCambioEuro = 0.0033;
	private static final double tasaCambioLibra = 0.0029;
	private static final double tasaCambioYen = 0.51;
	private static final double tasaCambioWon = 4.70;

	public double convertirDesdePesos(double cantidad, String monedaDestino) {
		switch (monedaDestino) {
		case "Dolar":
			return cantidad / tasaCambioDolar;
		case "Euros":
			return cantidad / tasaCambioEuro;
		case "Libras Esterlinas":
			return cantidad / tasaCambioLibra;
		case "Yen Japones":
			return cantidad * tasaCambioYen;
		case "Won sur-coreano":
			return cantidad * tasaCambioWon;
		default:
			throw new IllegalArgumentException("Moneda de destino inválida");
		}
	}

	public double convertirHaciaPesos(double cantidad, String monedaOrigen) {
		switch (monedaOrigen) {
		case "Dolar":
			return cantidad * tasaCambioDolar;
		case "Euros":
			return cantidad * tasaCambioEuro;
		case "Libras Esterlinas":
			return cantidad * tasaCambioLibra;
		case "Yen Japones":
			return cantidad / tasaCambioYen;
		case "Won sur-coreano":
			return cantidad / tasaCambioWon;
		default:
			throw new IllegalArgumentException("Moneda de origen inválida");
		}
	}
}

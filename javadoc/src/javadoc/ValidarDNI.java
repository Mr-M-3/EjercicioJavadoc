package javadoc;
/**
 * La clase ValidarDNI comprueba si un String que contenga un DNI es valido y en algunos casos
 * lo normaliza<br>
 * Devuelve el NIE con letras mayusculas si el NIE es valido
 *  
 *  
 * <br>
 * @see <a href="https://generadordni.es/#home">Ejemplo de DNI valido</a>
 * 
 * 
 * @author daw1
 * @version 20.0.1
 * @since 08/04/2024
 * 
 */

public class ValidarDNI {
	/**
	 * Devuelve el dni con 0's a la izquierda(hasta 8) y letra mayuscula si el dni es valido.
	 * <br>Devuelve null si el DNI/NIE no es valido
	 * @param dniNie String con el DNI
	 * @return String Resultado de el dni normalizado
	 * @throws IllegalArgumentException Para formato DNI invalido
	 */
	
	
	public static String validarDniNie(String dniNie) {
		// Devuelve el dni con 0s a la izquierda(hasta 8) y letra mayúscula si el dni
		// es valido.
		// Devuelve el nie con letras mayúsculas si el nie es válido.
		// null si el dni o nie no es válido
		

		char[] letras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };

		if (dniNie == null)
			return null;

		String normalizado = dniNie.trim().toUpperCase(); // Quita espacios en blanco y convierte a mayúsculas

		String numero;
		if (normalizado.matches("[0-9]{1,8}[A-Z]")) {// dni
			// Rellenamos con ceros por la izquierda
			while (normalizado.length() < 9)
				normalizado = "0" + normalizado;
			numero = normalizado.substring(0, 8);
		} else {
			if (normalizado.matches("[XYZ][0-9]{7}[A-Z]")) {// nie
				numero = normalizado.substring(1, 8);
				// Obtiene 0, 1 ó 2 según X, Y o Z
				String prefijo = "";
				switch (normalizado.charAt(0)) {// Analiza primera letra del nie
				case 'X':
					prefijo = "0";
					break;
				case 'Y':
					prefijo = "1";
					break;
				case 'Z':
					prefijo = "2";
					break;
				}
				numero = prefijo + numero;

			} else { // No es un dni ni un nie
				return null;
			}
		}

		// letra final
		char letra = normalizado.charAt(normalizado.length() - 1);// La letra es el último carácter del dni
		// Obtiene letra del array a partir del número y compara con la letra final
		if (letra != letras[Integer.valueOf(numero) % 23])
			return null;
		return normalizado;
	}

	}

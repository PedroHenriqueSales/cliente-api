package br.com.crud.util;

import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Collection;

public class Utils {

	private Utils() {
		throw new IllegalStateException("Classe utilitária! Não pode ser instanciada.");
	}

	/**
	 * Verifica se a coleção informada é nula ou vazia.
	 *
	 * @param collection
	 * @return
	 */
	private static boolean isCollectionEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * Verifica se o objeto informado é nulo, ou vazio
	 *
	 * @param object
	 * @return
	 */
	public static boolean isObjectEmpty(Object object) {
		if (object == null) return true;
		else if (object instanceof String) {
			if (((String) object).trim().length() == 0) {
				return true;
			}
		} else if (object instanceof Collection) {
			return isCollectionEmpty((Collection<?>) object);
		}
		return false;
	}

	/**
	 * Formata a {@link String} conforme os parâmetros fornecidos.
	 *
	 * @param input
	 * @param parameters
	 * @return
	 */
	public static String formatarString(String input, String... parameters) {
		return StringUtils.isNotBlank(input) ? MessageFormat.format(input, parameters) : input;
	}

}

package com.vinoigitare.util;

import java.util.Locale;

public final class I18nUtil {

	/**
	 * Sign delimiting language code from language variant.
	 */
	private static final String LANG_VARIANT_DELIMITER = "_"; //$NON-NLS-1$

	/**
	 * Default empty hidden constructor.
	 */
	private I18nUtil() {
		// empty
	}

	/**
	 * Generates {@link Locale} object from languageCode. Language code can contain language_Country_variant parts which
	 * are used to build {@link Locale} object.
	 * 
	 * @param languageCode
	 *            language or language_country or language_country_variant
	 * @return Locale for specified language code
	 * 
	 */
	public static Locale getLocale(String languageCode) {
		Locale result;
		if (languageCode.indexOf(LANG_VARIANT_DELIMITER) > -1) {
			String[] split = languageCode.split(LANG_VARIANT_DELIMITER);
			if (split.length == 2) {
				// language_country
				result = new Locale(split[0], split[1]);
			} else {
				// language_country_variant
				result = new Locale(split[0], split[1], split[2]);
			}
		} else {
			result = new Locale(languageCode);
		}
		return result;
	}

}
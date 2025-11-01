package com.marketcore.util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class SlugUtil {

    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");
    private static final Pattern MULTIPLE_HYPHENS = Pattern.compile("-{2,}");

    /**
     * Convertit un texte en slug pour les URLs
     * Exemple: "Téléphones Samsung Galaxy" -> "telephones-samsung-galaxy"
     */
    public static String toSlug(String input) {
        if (input == null || input.trim().isEmpty()) {
            return "";
        }

        String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        String slug = NONLATIN.matcher(normalized).replaceAll("");
        slug = MULTIPLE_HYPHENS.matcher(slug).replaceAll("-");

        return slug.toLowerCase(Locale.ENGLISH)
                   .replaceAll("^-+", "")  // Supprimer les tirets au début
                   .replaceAll("-+$", ""); // Supprimer les tirets à la fin
    }

    /**
     * Génère un slug unique en ajoutant l'ID
     * Exemple: "telephones-samsung-galaxy-123"
     */
    public static String toSlugWithId(String name, Long id) {
        String slug = toSlug(name);
        return slug.isEmpty() ? String.valueOf(id) : slug + "-" + id;
    }
}

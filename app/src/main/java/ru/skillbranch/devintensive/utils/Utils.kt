package ru.skillbranch.devintensive.utils

import android.content.Context
import kotlin.math.roundToInt

object Utils {
    val transliterationMap = hashMapOf(
            'а' to "a",
            'б' to "b",
            'в' to "v",
            'г' to "g",
            'д' to "d",
            'е' to "e",
            'ё' to "e",
            'ж' to "zh",
            'з' to "z",
            'и' to "i",
            'й' to "i",
            'к' to "k",
            'л' to "l",
            'м' to "m",
            'н' to "n",
            'о' to "o",
            'п' to "p",
            'р' to "r",
            'с' to "s",
            'т' to "t",
            'у' to "u",
            'ф' to "f",
            'х' to "h",
            'ц' to "c",
            'ч' to "ch",
            'ш' to "sh",
            'щ' to "sh'",
            'ъ' to "",
            'ы' to "i",
            'ь' to "",
            'э' to "e",
            'ю' to "yu",
            'я' to "ya"
            )

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(" ")

        var firstName: String? = parts?.getOrNull(0)?.ifEmpty { null }
        var lastName: String? = parts?.getOrNull(1)?.ifEmpty { null }

        return firstName to lastName
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstName = firstName.orEmpty().trim().getOrNull(0)?.toUpperCase()
        val lastName = lastName.orEmpty().trim().getOrNull(0)?.toUpperCase()

        return "${firstName ?: ""}${lastName ?: ""}".ifEmpty { null }
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val stringBuilder = StringBuilder()
        for (char in payload.trim()) {
            var transliteration  = transliterationMap[char.toLowerCase()] ?: char.toString()
            if (char.isUpperCase()) {
                transliteration = transliteration.capitalize()
            }

            stringBuilder.append(transliteration)
        }
        return stringBuilder.toString().replace(" ", divider)
    }

    fun convertPxToDp(context: Context, px: Int): Int {
        return (px / context.resources.displayMetrics.density).roundToInt()
    }

    fun convertDpToPx(context: Context, dp: Float): Int {
        return (dp * context.resources.displayMetrics.density).roundToInt()
    }

    fun convertSpToPx(context: Context, sp: Int): Int {
        return sp * context.resources.displayMetrics.scaledDensity.roundToInt()
    }
}
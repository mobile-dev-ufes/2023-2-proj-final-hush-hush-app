package com.example.a2023_2_proj_final_hush_hush_app.utils

import android.icu.text.SimpleDateFormat
import java.util.Locale

class Date {
    companion object {
        fun formatDate(
            date: String,
            inPattern: String = "yyyy-MM-dd HH:mm",
            outPattern: String = "MM/dd/yyyy HH:mm"
        ): String {
            val formatIn = SimpleDateFormat(inPattern, Locale.getDefault())
            val dateAux = formatIn.parse(date)
            val formatOut = SimpleDateFormat(outPattern, Locale.getDefault())
            return formatOut.format(dateAux).toString()
        }

        fun formatDateAccordingUserLocale(
            date: String,
            inPattern: String = "yyyy-MM-dd HH:mm",
            useTime: Boolean = true
        ): String {
            val userLocale = Locale.getDefault().language.toString()

            var outPattern = if(userLocale == "pt") {
                "dd/MM/yyyy"

            }else{
                "MM/dd/yyyy"
            }

            if (useTime) {
                outPattern += " HH:mm"
            }

            val formattedDate = Date.formatDate(date, inPattern, outPattern)

            return if (useTime) {
                val splitDate = formattedDate.split(" ")
                if (userLocale == "pt") {
                    "${splitDate[0]} às ${splitDate[1]}"
                } else {
                    "${splitDate[0]} at ${splitDate[1]}"
                }
            } else {
                formattedDate
            }
        }
    }
}
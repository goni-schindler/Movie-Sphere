package il.co.gonisch.moviesphere.api

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class TmdbDateConverter : JsonDeserializer<LocalDate> {

    private val formatter = DateTimeFormatter.ofPattern("yyy-MM-dd") // Matches "01-02-2025"

    // Convert JSON to LocalDate
    override fun deserialize(
        json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
    ): LocalDate? {
        if (!json.isJsonPrimitive || json.asString.isNullOrBlank()) {
            return null
        }
        return try {
            LocalDate.parse(json.asString, formatter)
        } catch (e: DateTimeParseException) {
            null
        }
    }
}
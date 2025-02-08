package il.co.gonisch.moviesphere.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException


/**
 * This class is highly vulnerable to bugs and should be
 * covered with tests heavily
 * */
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
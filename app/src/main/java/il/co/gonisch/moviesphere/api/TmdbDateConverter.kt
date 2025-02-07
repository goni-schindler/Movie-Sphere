package il.co.gonisch.moviesphere.api

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class TmdbDateConverter : JsonDeserializer<LocalDate> {

    private val formatter = DateTimeFormatter.ofPattern("yyy-MM-dd") // Matches "01-02-2025"

    // Convert JSON to LocalDate
    override fun deserialize(
        json: JsonElement, typeOfT: Type, context: JsonDeserializationContext
    ): LocalDate {
        return LocalDate.parse(json.asString, formatter)
    }
}
package com.example.carinspection.model

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson

@ProvidedTypeConverter
class Converts {
    @TypeConverter
    fun listNameToJson(value: List<ListNameData>) = Gson().toJson(value)

    @TypeConverter
    fun jsonTolistName(value: String) = Gson().fromJson(value, Array<ListNameData>::class.java).toList()

    @TypeConverter
    fun listValueToJson(value: List<ListNameValue>) = Gson().toJson(value)

    @TypeConverter
    fun jsonTolistValue(value: String) = Gson().fromJson(value, Array<ListNameValue>::class.java).toList()
}
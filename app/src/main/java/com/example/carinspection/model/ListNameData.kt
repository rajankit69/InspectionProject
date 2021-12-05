package com.example.carinspection.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "listname_table")
data class ListNameData(@PrimaryKey(autoGenerate = false)
                        @ColumnInfo(name = "ID")
                            var LIST_NAME : String,
                        @ColumnInfo(name = "LIST_NAME_DESCRIPTION")
                        var LIST_NAME_DESCRIPTION : String ,
                        @ColumnInfo(name = "LIST_NAME_VALUES")
                        @TypeConverters(Converts::class)
                        var LIST_NAME_VALUES : List<ListNameValue>,
                       ) {
}
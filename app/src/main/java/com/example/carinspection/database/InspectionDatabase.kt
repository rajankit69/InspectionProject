package com.example.carinspection.database

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.carinspection.model.*

@Database(entities = [LeadData::class, InspectionData::class,ListNameData::class], version = 6, exportSchema = false)
@TypeConverters(Converts::class)
abstract class InspectionDatabase : RoomDatabase() {

    abstract val listNameDataDao: ListNameDataDao
    abstract val leadDataDao: LeadDataDao
    abstract val inspectionDataDao: InspectionAnswerDao

    companion object {

        @Volatile
        private var INSTANCE: InspectionDatabase? = null

        fun getInstance(context: Context): InspectionDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    val roomConverter = Converts()
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        InspectionDatabase::class.java,
                        "inspection_database"
                    ).addTypeConverter(roomConverter)
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
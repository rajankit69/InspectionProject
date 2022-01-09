package com.example.carinspection.database

import androidx.room.*
import com.example.carinspection.model.InspectionData

@Dao
interface InspectionAnswerDao {
    @Query("SELECT * FROM answer_table")
    fun getAll(): List<InspectionData>



/*    @Query("SELECT * FROM paddata WHERE uid = :uid LIMIT 1")
    PadData findByPadData(int uid);*/

    /*    @Query("SELECT * FROM paddata WHERE uid = :uid LIMIT 1")
    PadData findByPadData(int uid);*/

    @Insert
    fun insertAll(inspectionData: List<InspectionData>)

    @Update
    fun update(inspectionData: InspectionData?)

    @Delete
    fun delete(inspectionData: InspectionData?)

    @Delete
    fun deleteAll(inspectionData: List<InspectionData?>?)

    @Query("SELECT * FROM answer_table WHERE dataId = :dataId LIMIT 1")
    fun findSingleAnswerData(dataId: Int): InspectionData?

}
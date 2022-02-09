package com.example.carinspection.database

import androidx.room.*
import com.example.carinspection.model.InspectionData

@Dao
interface InspectionAnswerDao {
    @Query("SELECT * FROM answer_table")
    suspend fun getAll(): List<InspectionData>



/*    @Query("SELECT * FROM paddata WHERE uid = :uid LIMIT 1")
    PadData findByPadData(int uid);*/

    /*    @Query("SELECT * FROM paddata WHERE uid = :uid LIMIT 1")
    PadData findByPadData(int uid);*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(inspectionData: InspectionData)

    @Insert
    suspend fun insertAll(inspectionData: List<InspectionData>)

    @Update
    suspend fun update(inspectionData: InspectionData?)

    @Delete
    suspend fun delete(inspectionData: InspectionData?)

    @Delete
    suspend fun deleteAll(inspectionData: List<InspectionData?>?)

    @Query("SELECT * FROM answer_table WHERE dataId = :dataId LIMIT 1")
    suspend fun findSingleAnswerData(dataId: Int): InspectionData?

}
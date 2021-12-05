package com.example.carinspection.database

import androidx.room.*
import com.example.carinspection.model.LeadData
@Dao
interface LeadDataDao {
    @Query("SELECT * FROM leads_table")
    suspend fun getAll(): List<LeadData>



/*    @Query("SELECT * FROM paddata WHERE uid = :uid LIMIT 1")
    PadData findByPadData(int uid);*/

    /*    @Query("SELECT * FROM paddata WHERE uid = :uid LIMIT 1")
    PadData findByPadData(int uid);*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(leadData: List<LeadData>)

    @Update
    suspend fun update(leadData: LeadData?)

    @Delete
    suspend fun delete(leadData: LeadData?)

    @Delete
    suspend fun deleteAll(leadData: List<LeadData>)

    @Query("SELECT * FROM leads_table WHERE Id = :Id LIMIT 1")
    suspend fun findSingleLeadData(Id: Int): LeadData

}

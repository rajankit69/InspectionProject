package com.example.carinspection.database

import androidx.room.*
import com.example.carinspection.model.LeadData
import com.example.carinspection.model.ListNameData

@Dao
interface ListNameDataDao {
    @Query("SELECT * FROM listname_table")
    suspend fun getAll(): List<ListNameData>



/*    @Query("SELECT * FROM paddata WHERE uid = :uid LIMIT 1")
    PadData findByPadData(int uid);*/

    /*    @Query("SELECT * FROM paddata WHERE uid = :uid LIMIT 1")
    PadData findByPadData(int uid);*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(listNameDatalist: List<ListNameData>)

    @Update
    suspend fun update(listNameData: ListNameData?)

    @Delete
    suspend fun delete(listNameData: ListNameData?)

    @Delete
    suspend fun deleteAll(listNameDatalist: List<ListNameData>)

    @Query("SELECT * FROM listname_table WHERE Id = :Id LIMIT 1")
    suspend fun findSingleListNameData(Id: Int): ListNameData
}
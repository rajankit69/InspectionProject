package com.example.carinspection.database

import com.example.carinspection.model.LeadData

interface DataBaseHelper {
    suspend fun getLeadDataList(): List<LeadData>

    suspend fun insertLeadList(leadDataList:List<LeadData>)

    suspend fun insertLeadData(leadData:LeadData)

    suspend fun getLeadData(id:Int): LeadData

    suspend fun deleteLeadData(leadData:LeadData)

}
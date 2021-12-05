package com.example.carinspection.database

import com.example.carinspection.model.LeadData

class DataBaseHelperImplementation(private val appDatabase: InspectionDatabase) : DataBaseHelper {
    override suspend fun getLeadDataList(): List<LeadData> {
       return appDatabase.leadDataDao.getAll()
    }

    override suspend fun insertLeadList(leadDataList: List<LeadData>) {
        appDatabase.leadDataDao.insertAll(leadDataList)
    }

    override suspend fun insertLeadData(leadData: LeadData) {
    }

    override suspend fun getLeadData(id:Int): LeadData {
      return  appDatabase.leadDataDao.findSingleLeadData(id)
    }

    override suspend fun deleteLeadData(leadData: LeadData) {
        return appDatabase.leadDataDao.delete(leadData)
    }
}
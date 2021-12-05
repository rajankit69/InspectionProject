package com.example.carinspection

import android.app.Application
import androidx.lifecycle.*
import com.example.carinspection.database.DataBaseHelper
import com.example.carinspection.database.InspectionDatabase
import com.example.carinspection.model.LeadData
import kotlinx.coroutines.launch

class InspectionViewModel(private val dbHelper: DataBaseHelper) :  ViewModel() {



/*    // LiveData which will be observed by UI class (Activity) for new Quote
    val leadData = MutableLiveData<LeadData>()



    fun fetchRandomQuote() {
        viewModelScope.launch {
            // fetch quote from REST API
            val quote = QuotesApiService.quotesApis.getRandomQuote()

            // save quote in db
            quotesDao.insertQuote(quote)

            // publish quote to LiveData
            liveQuote.postValue(quote)
        }
    }*/
}
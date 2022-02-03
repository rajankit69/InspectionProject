package com.example.carinspection.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class UploadMediaData(var path: String?, var mediaType:String?, var mediaName:String?, var imageDate : String?)

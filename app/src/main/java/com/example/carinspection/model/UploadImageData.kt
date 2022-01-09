package com.example.carinspection.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class UploadImageData(var imagePath: String?, var imageType:String?,var imageName:String?,var imageDate : String?)

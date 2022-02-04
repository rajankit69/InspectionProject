package com.example.carinspection.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "answer_table")
data class InspectionData(
                         @ColumnInfo(name = "Question")
                         var question: String?=null,
                         @ColumnInfo(name = "Answer")
                          var answer: String?=null,
                          @ColumnInfo(name = "screenNo")
                          var screenNo: Int ?=0,
                          @ColumnInfo(name = "objectType")
                          var objectType: String?=null,
                          @ColumnInfo(name = "editable")
                          var editable : Boolean,
                          @PrimaryKey(autoGenerate = true)
                          var dataId:Int=0)

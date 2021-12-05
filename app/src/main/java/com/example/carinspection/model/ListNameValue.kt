package com.example.carinspection.model

import androidx.room.ColumnInfo

data class ListNameValue(
    var ID: Int,
    var LIST_NAME_VALUE: String,
    var DEFAULT_REMARK_IF_SELECTED: String?=null,
    var IS_AVAILABLE_WHILE_ADDING: Int,
    var IS_AVAILABLE_WHILE_UPDATING: Int,
    var isSelected : Boolean
)

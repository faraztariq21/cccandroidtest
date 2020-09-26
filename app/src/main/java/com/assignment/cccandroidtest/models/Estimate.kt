package com.assignment.cccandroidtest.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "estimate_table")
data class Estimate(

    @PrimaryKey
    var id: String,
    var company: String,
    var address: String,
    var number: Int,
    var revision_number: Int,
    var created_date: String,
    var created_by: String,
    var requested_by: String,
    var contact: String

){
    constructor(): this("","","",-1,-1,"","","","")
}
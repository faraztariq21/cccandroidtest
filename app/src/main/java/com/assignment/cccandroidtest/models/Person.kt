package com.assignment.cccandroidtest.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_table")
data class Person(

    @PrimaryKey
    val id: String,
    val first_name: String,
    val last_name: String,
    val email: String,
    val phone_number: String
){
    constructor():this("","","","","")
}
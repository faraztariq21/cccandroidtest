package com.assignment.cccandroidtest.repositories

import androidx.lifecycle.LiveData
import com.assignment.cccandroidtest.daos.PersonsDao
import com.assignment.cccandroidtest.models.Person


class PersonRepository(private val personsDao: PersonsDao) {


    val allPersons = personsDao.getAllPersons()

    fun personsById(personId: String) = personsDao.getPersonById(personId)

    suspend fun insert(person: Person) {
        personsDao.insert(person)
    }
}
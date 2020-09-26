package com.assignment.cccandroidtest.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.assignment.cccandroidtest.models.Person
import io.reactivex.Flowable

@Dao
interface PersonsDao {

    @Query("SELECT * from person_table")
    fun getAllPersons(): Flowable<List<Person>>

    @Query("SELECT * from person_table WHERE id = :personId")
    fun getPersonById(personId: String): Flowable<Person>

    @Query("SELECT * from person_table WHERE first_name Like '%' || :personName || '%' OR last_name like '%' || :personName || '%'")
    fun getPersonByName(personName: String): Flowable<List<Person>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: Person)

    @Delete
    fun deletePerson(vararg person: Person)

    @Query("DELETE FROM person_table")
    suspend fun deleteAll()

}
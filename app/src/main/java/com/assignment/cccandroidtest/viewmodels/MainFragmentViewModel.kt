package com.assignment.cccandroidtest.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.assignment.cccandroidtest.base.BaseViewModel
import com.assignment.cccandroidtest.database.AppRoomDatabase
import com.assignment.cccandroidtest.models.DataResponse
import com.assignment.cccandroidtest.models.Estimate
import com.assignment.cccandroidtest.models.Person
import com.assignment.cccandroidtest.repositories.EstimateRepository
import com.assignment.cccandroidtest.repositories.PersonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel(application: Application) : AndroidViewModel(application) {

    private val personRepository: PersonRepository
    val allPersons: LiveData<List<Person>>

    private val estimateRepository: EstimateRepository
    val allEstimates: LiveData<List<Estimate>>


    init {
        val personsDao = AppRoomDatabase.getDatabase(application, viewModelScope).personDao()
        personRepository = PersonRepository(personsDao)
        allPersons = personRepository.allPersons

        val estimatesDao = AppRoomDatabase.getDatabase(application, viewModelScope).estimateDao()
        estimateRepository = EstimateRepository(estimatesDao)
        allEstimates = estimateRepository.allEstimates
    }

    private fun getPersonById(id: String): LiveData<Person> = personRepository.personsById(id)
    private fun getEstimateById(id: String): LiveData<Estimate> = estimateRepository.estimatesById(id)

    fun personAndEstimateLiveData(personId: String, estimateId: String): LiveData<Pair<Person, Estimate>> =
        object : MediatorLiveData<Pair<Person, Estimate>>() {
            var person: Person? = null
            var estimate: Estimate? = null

            init {
                addSource(getEstimateById(estimateId)) { estimate ->
                    this.estimate = estimate
                    person?.let { value = Pair(person!!,estimate) }
                }

                addSource(getPersonById(personId)) { person ->
                    this.person = person
                    estimate?.let { value = Pair(person,estimate!!) }
                }
            }

        }


    fun insertPerson(person: Person) = viewModelScope.launch(Dispatchers.IO) {
        personRepository.insert(person)
    }

    fun insertEstimate(estimate: Estimate) = viewModelScope.launch(Dispatchers.IO) {
        estimateRepository.insert(estimate)
    }

    fun updateEstimate(estimate: Estimate) = viewModelScope.launch(Dispatchers.IO) {
        estimateRepository.update(estimate)
    }


}
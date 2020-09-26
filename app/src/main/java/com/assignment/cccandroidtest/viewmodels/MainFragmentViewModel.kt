package com.assignment.cccandroidtest.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.assignment.cccandroidtest.ApplicationManager
import com.assignment.cccandroidtest.base.BaseViewModel
import com.assignment.cccandroidtest.database.AppRoomDatabase
import com.assignment.cccandroidtest.models.DataResponse
import com.assignment.cccandroidtest.models.Estimate
import com.assignment.cccandroidtest.models.Person
import com.assignment.cccandroidtest.repositories.EstimateRepository
import com.assignment.cccandroidtest.repositories.PersonRepository
import io.reactivex.Flowable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class MainFragmentViewModel : AndroidViewModel(ApplicationManager.instance) {


    private val personRepository: PersonRepository
    val allPersons: Flowable<List<Person>>

    private val estimateRepository: EstimateRepository
    val allEstimates: Flowable<List<Estimate>>

    init {
        val personsDao = AppRoomDatabase.getDatabase(ApplicationManager.instance, viewModelScope).personDao()
        personRepository = PersonRepository(personsDao)
        allPersons = personRepository.allPersons

        val estimatesDao = AppRoomDatabase.getDatabase(ApplicationManager.instance, viewModelScope).estimateDao()
        estimateRepository = EstimateRepository(estimatesDao)
        allEstimates = estimateRepository.allEstimates
    }

    fun updateEstimate(estimate: Estimate) = viewModelScope.launch(Dispatchers.IO) {
        estimateRepository.update(estimate)
    }

//    fun getPersonById(id: String): Flowable<Person> = personRepository.personsById(id)
//    fun getEstimateById(id: String): Flowable<Estimate> = estimateRepository.estimatesById(id)
//
//    fun insertPerson(person: Person) = viewModelScope.launch(Dispatchers.IO) {
//        personRepository.insert(person)
//    }
//
//    fun insertEstimate(estimate: Estimate) = viewModelScope.launch(Dispatchers.IO) {
//        estimateRepository.insert(estimate)
//    }
//


    //    fun personAndEstimateLiveData(personId: String, estimateId: String): Flowable<Pair<Person, Estimate>> =
//        object : MediatorLiveData<Pair<Person, Estimate>>() {
//            var person: Person? = null
//            var estimate: Estimate? = null
//
//            init {
//                addSource(getEstimateById(estimateId)) { estimate ->
//                    this.estimate = estimate
//                    person?.let { value = Pair(person!!,estimate) }
//                }
//
//                addSource(getPersonById(personId)) { person ->
//                    this.person = person
//                    estimate?.let { value = Pair(person,estimate!!) }
//                }
//            }
//
//        }

}
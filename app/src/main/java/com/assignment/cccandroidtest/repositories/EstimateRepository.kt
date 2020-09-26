package com.assignment.cccandroidtest.repositories

import androidx.lifecycle.LiveData
import com.assignment.cccandroidtest.daos.EstimatesDao
import com.assignment.cccandroidtest.models.Estimate
import io.reactivex.Flowable


class EstimateRepository (private val estimatesDao: EstimatesDao) {


    val allEstimates = estimatesDao.getAllEstimates()

    fun estimatesById(estimateId: String) = estimatesDao.getEstimateById(estimateId)


    suspend fun insert(estimate: Estimate){
        estimatesDao.insert(estimate)
    }

    suspend fun update(estimate: Estimate){
        estimatesDao.update(estimate)
    }
}
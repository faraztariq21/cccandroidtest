package com.assignment.cccandroidtest.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.assignment.cccandroidtest.models.Estimate
import com.assignment.cccandroidtest.models.Person

@Dao
interface EstimatesDao {

    @Query("SELECT * from estimate_table")
    fun getAllEstimates(): LiveData<List<Estimate>>

    @Query("SELECT * from estimate_table WHERE id = :estimateId")
    fun getEstimateById(estimateId : String): LiveData<Estimate>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(estimate: Estimate)

    @Update(entity = Estimate::class)
    suspend fun update(estimate: Estimate)

    @Delete
    fun deletePerson(vararg estimate: Estimate)

    @Query("DELETE FROM estimate_table")
    suspend fun deleteAll()

}
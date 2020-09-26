package com.assignment.cccandroidtest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.assignment.cccandroidtest.databinding.FragmentMainBinding
import com.assignment.cccandroidtest.models.Estimate
import com.assignment.cccandroidtest.models.Person
import com.assignment.cccandroidtest.viewmodels.MainFragmentViewModel

class MainFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentMainBinding
    private val personId = "85a57f85-a52d-4137-a0d1-62e61362f716"
    private val estimateId = "c574b0b4-bdef-4b92-a8f0-608a86ccf5ab"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            viewmodel =
                ViewModelProviders.of(this@MainFragment).get(MainFragmentViewModel::class.java)
            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        viewDataBinding.viewmodel?.personAndEstimateLiveData(personId,estimateId)?.observe(viewLifecycleOwner,{
            updateEstimateValues(it.first,it.second)
        })

    }

    private fun updateEstimateValues(person: Person, estimate: Estimate){
        estimate.created_by = person.id
        estimate.requested_by = person.id
        estimate.contact = person.id

        viewDataBinding.viewmodel?.updateEstimate(estimate)
    }

}
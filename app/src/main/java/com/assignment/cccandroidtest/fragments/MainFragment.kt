package com.assignment.cccandroidtest.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.assignment.cccandroidtest.databinding.FragmentMainBinding
import com.assignment.cccandroidtest.models.Estimate
import com.assignment.cccandroidtest.models.Person
import com.assignment.cccandroidtest.viewmodels.MainFragmentViewModel
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModelProviders
import com.assignment.cccandroidtest.viewmodels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentMainBinding

    private var person: Person? = null
    private var estimate: Estimate? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentMainBinding.inflate(inflater, container, false).apply {
            viewmodel =
                ViewModelProvider(
                    this@MainFragment,
                    ViewModelFactory()
                ).get(MainFragmentViewModel::class.java)

            lifecycleOwner = viewLifecycleOwner
        }
        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
    }


    private fun setupObservers() {
//        viewDataBinding.viewmodel?.personAndEstimateLiveData(personId,estimateId)?.observe(viewLifecycleOwner,{
//            updateEstimateValues(it.first,it.second)
//        })

        val personDispose = viewDataBinding.viewmodel?.allPersons?.subscribe { person ->
            this.person = person[0]
            updateEstimateValues()
            updateView()
        }

        val estimateDispose = viewDataBinding.viewmodel?.allEstimates?.subscribe { estimate ->
            this.estimate = estimate[0]
            updateEstimateValues()
            updateView()
        }
    }

    private fun updateEstimateValues() {
        if (person != null && estimate != null) {
            estimate?.created_by = person?.id!!
            estimate?.requested_by = person?.id!!
            estimate?.contact = person?.id!!
            viewDataBinding.viewmodel?.updateEstimate(estimate!!)
        }
    }


    @SuppressLint("SetTextI18n")
    private fun updateView() {
        if (person != null && estimate != null) {

            companyName.text = estimate?.company
            address.text = estimate?.address
            estNumber.text = estimate?.number.toString()
            revisionNumber.text = estimate?.revision_number.toString()
            created.text = estimate?.created_date!!.split(" ")[0]

            createdBy.text = "${person?.first_name!!} ${person?.last_name!!}"
            requester.text = "${person?.first_name!!} ${person?.last_name!!}"
            contact.text = "${person?.first_name!!} ${person?.last_name!!}"

        }
    }

}
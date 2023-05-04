package com.loureiro.bignerdranch.criminalintent

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.loureiro.bignerdranch.criminalintent.databinding.FragmentCrimeListBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CrimeListFragment : Fragment(){

    private var _binding: FragmentCrimeListBinding? = null
    private val binding
    get() = checkNotNull(_binding) {
        "Cannot access binding because it is null. Is the view visible?"
    }

    private val crimeListViewModel :CrimeListViewModel by viewModels()

   // private var job: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrimeListBinding.inflate(inflater, container, false)

        binding.crimeRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    /*override fun onStart() {
        super.onStart()

        job = viewLifecycleOwner.lifecycleScope.launch {
            val crimes = crimeListViewModel.loadCrimes()
            binding.crimeRecyclerView.adapter = CrimeListAdapter(crimes)
        }
    }

    override fun onStop() {
        super.onStop()
        job?.cancel()
    }*/

    //Getting rid of the onStart and onStop means there are fewer lifecycle methods to go through
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                crimeListViewModel.crimes.collect { crimes ->
                    binding.crimeRecyclerView.adapter =
                        CrimeListAdapter(crimes)
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
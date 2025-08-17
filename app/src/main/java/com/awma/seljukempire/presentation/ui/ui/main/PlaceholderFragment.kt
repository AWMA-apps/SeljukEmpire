package com.awma.seljukempire.presentation.ui.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.awma.seljukempire.presentation.ui.PageActivity
import com.awma.seljukempire.data.model.Characters
import com.awma.seljukempire.data.model.Events
import com.awma.seljukempire.databinding.FragmentMainBinding
import com.awma.seljukempire.presentation.ui.adapter.Adapter
import com.awma.seljukempire.presentation.viewmodel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * A placeholder fragment containing a simple view.
 */
@AndroidEntryPoint
class PlaceholderFragment : Fragment() {
    private val vm: AppViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private lateinit var adapter: Adapter


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.hasFixedSize()

        val sectionNumber = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1

        when (sectionNumber) {
            1 -> {
                adapter = Adapter(emptyList()) { item ->
                    if (item is Events) {
                        val intent = Intent(context, PageActivity::class.java)
                        intent.putExtra("event", item)
                        startActivity(intent)
                    }
                }
                    vm.events.observe(viewLifecycleOwner) {
                        adapter.updateList(it)
                        recyclerView.adapter = adapter
                    }
                    vm.viewModelScope.launch {
                        vm.loadEvents()
                    }

            }

            2 -> {
                adapter = Adapter(emptyList()) { item ->
                    if (item is Characters) {
                        val intent = Intent(context, PageActivity::class.java)
                        intent.putExtra("char", item)
                        startActivity(intent)
                    }
                }
                vm.characters.observe(viewLifecycleOwner) {
                    if (it != null) {
                        adapter.updateList(it)
                        recyclerView.adapter = adapter
                    }
                }
                vm.viewModelScope.launch {
                    vm.loadCharacters()
                }
                Log.d("PlaceholderFragment", "onCreateView: ${vm.characters.value}")
            }


        }

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
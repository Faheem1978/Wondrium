package com.coding.app.pages.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.app.R
import com.coding.app.common.networking.retrofit.Status
import com.coding.app.common.utils.BUNDLE_KEY_NAME
import com.coding.app.databinding.FragmentHomeBinding
import com.coding.app.pages.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var fetchedResults: Boolean=false
    private lateinit var productListAdapter: ItemListRecyclerAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    //    private lateinit var viewModel: HomeViewModel
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeScreenStatus()
        fetchData()
        setListeners()
        setupProductRecycler()
    }

    private fun setupProductRecycler() {
        binding.productRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.productRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                RecyclerView.VERTICAL
            )
        )
        productListAdapter = ItemListRecyclerAdapter(object : ListItemClickCallback {
            override fun onClickItem(name: String) {
                openDetailPage(name)
            }
        })
        binding.productRecyclerView.adapter = productListAdapter

    }

    override fun onResume() {
        super.onResume()
    }

    private fun setListeners() {
        binding.buttonGo.setOnClickListener {
            openDetailPage(binding.inputText.text.toString())
        }

    }

    private fun openDetailPage(name: String) {
        val bundle = Bundle()
        bundle.putString(BUNDLE_KEY_NAME, name.lowercase())
        findNavController().navigate(R.id.detailsFragment, bundle)
    }

    private fun setupAutoCompleteList(list: List<String>) {
        context?.let {
            val adapter = ArrayAdapter(
                it,
                android.R.layout.simple_list_item_1, list
            )
            binding.inputText.setAdapter(adapter)
        }
    }

    private fun observeScreenStatus() {
        homeViewModel.status.observe(viewLifecycleOwner) {
            when (it) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE

                }

                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE

                }

                null -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

    }

    override fun onStop() {
        super.onStop()
        fetchedResults=false
    }

    private fun fetchData() {
        binding.progressBar.visibility = View.VISIBLE
        homeViewModel.fetchList()
        homeViewModel.response.observe(viewLifecycleOwner) {
            if (!fetchedResults) {
                productListAdapter.updateList(it)
                setupAutoCompleteList(it)
                fetchedResults=true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.wondrium.app.pages.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wondrium.app.R
import com.wondrium.app.common.networking.retrofit.NetworkResult
import com.wondrium.app.databinding.FragmentHomeBinding
import com.wondrium.app.pages.home.models.Products
import com.wondrium.app.pages.home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var productListAdapter: ProductListRecyclerAdapter
    private  var _binding: FragmentHomeBinding?=null
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
        productListAdapter = ProductListRecyclerAdapter(object : ListItemClickCallback {
            override fun onClickItem(bundle: Bundle) {
                findNavController().navigate(R.id.detailsFragment)
            }
        })
        binding.productRecyclerView.adapter = productListAdapter

    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    private fun fetchData() {
        homeViewModel.fetchList()

        homeViewModel.response.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    updateProductList(it.data?.products)
                }

                is NetworkResult.Error -> {

                }

                is NetworkResult.Loading -> {

                }
            }
        }

    }

    private fun updateProductList(products: ArrayList<Products>?) {
        products?.let { productListAdapter.updateList(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}
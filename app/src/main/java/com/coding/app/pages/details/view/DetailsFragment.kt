package com.coding.app.pages.details.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coding.app.R
import com.coding.app.common.networking.retrofit.NetworkResult
import com.coding.app.common.utils.BUNDLE_KEY_NAME
import com.coding.app.databinding.FragmentDetailsBinding
import com.coding.app.pages.details.models.DetailsResponseModel
import com.coding.app.pages.details.viewmodels.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var name: String

    private lateinit var ImageListAdapter: ImageListRecyclerAdapter
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupImageRecycler()
    }

    private fun setupImageRecycler() {
        binding.lectureRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.lectureRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                RecyclerView.VERTICAL
            )
        )
        ImageListAdapter = ImageListRecyclerAdapter()
        binding.lectureRecyclerView.adapter = ImageListAdapter

    }

    override fun onResume() {
        super.onResume()
        fetchData()

    }

    private fun fetchData() {
        name = arguments?.getString(BUNDLE_KEY_NAME).toString()

        name.let {
            binding.title.text = name.uppercase()
            detailsViewModel.fetchDetails(name)
        }


        detailsViewModel.response.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    renderDetails(it.data)
                }

                is NetworkResult.Error -> {
                    showErrorMsg()
                }

                is NetworkResult.Loading -> {

                }
            }
        }

    }

    private fun renderDetails(data: DetailsResponseModel?) {

        data?.message?.let { updateList(it) } ?: {
            showErrorMsg()
        }

    }

    private fun showErrorMsg() {
        Toast.makeText(context, "No items to display for ${name}", Toast.LENGTH_SHORT)
            .show()
    }

    private fun updateList(items: ArrayList<String>) {

        items.let { ImageListAdapter.updateList(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
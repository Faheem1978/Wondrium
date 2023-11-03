package com.wondrium.app.pages.details.view

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
import coil.load
import com.wondrium.app.R
import com.wondrium.app.common.networking.retrofit.NetworkResult
import com.wondrium.app.databinding.FragmentDetailsBinding
import com.wondrium.app.pages.details.models.Lectures
import com.wondrium.app.pages.details.models.ProductDetailsResponseModel
import com.wondrium.app.pages.details.viewmodels.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private lateinit var productListAdapter: LactureListRecyclerAdapter
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
        setupProductRecycler()
    }

    private fun setupProductRecycler() {
        binding.lectureRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.lectureRecyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                RecyclerView.VERTICAL
            )
        )
        productListAdapter = LactureListRecyclerAdapter(object : ListItemClickCallback {
            override fun onClickItem(bundle: Bundle) {
                findNavController().navigate(R.id.videoPlayerFragment)
            }
        })
        binding.lectureRecyclerView.adapter = productListAdapter

    }

    override fun onResume() {
        super.onResume()
        fetchData()

    }

    private fun fetchData() {
        detailsViewModel.fetchDetails()

        detailsViewModel.response.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResult.Success -> {
                    renderDetails(it.data)
                }

                is NetworkResult.Error -> {

                }

                is NetworkResult.Loading -> {

                }
            }
        }

    }

    private fun renderDetails(data: ProductDetailsResponseModel?) {
        binding.pDetailsCourseName.text = data?.courseName
        binding.pDetailsCourseDescription.text = data?.courseDescription
        binding.pDetailsWondriumDescription.text = data?.wondriumDescription
        binding.pCourseProfessorName.text = "by:- ${data?.courseProfessorName}"
        binding.pCourseCategoryName.text = data?.coursePrimaryCategory

        binding.pDetailsImage.load("https://secureimages.teach12.com/tgc/images/m2/wondrium/courses/${data?.courseId}/portrait/${data?.courseId}.jpg")

        updateLacturesList(data?.lectures)

    }

    private fun updateLacturesList(products: ArrayList<Lectures>?) {
        products?.let { productListAdapter.updateList(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
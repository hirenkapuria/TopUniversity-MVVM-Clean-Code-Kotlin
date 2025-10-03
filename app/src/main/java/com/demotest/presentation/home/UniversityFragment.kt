package com.demotest.presentation.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.demotest.R
import com.demotest.databinding.FragmentHomeBinding
import com.demotest.domin.university.model.UniversityItem
import com.demotest.extenstion.gone
import com.demotest.extenstion.visible
import com.demotest.presentation.base.BaseActivity
import com.demotest.presentation.base.BaseFragment
import com.demotest.presentation.home.adapter.UniversityListAdapter
import com.demotest.presentation.home.adapter.UniversityPagingListAdapter
import com.demotest.presentation.universityDetails.UniversityDetailsFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class UniversityFragment : BaseFragment() {

    private var binding: FragmentHomeBinding? = null
    private val viewModel by viewModel<UniversityViewModel>()

    private val universityListAdapter: UniversityListAdapter by lazy {
        UniversityListAdapter(
            onUniversityClick = { website ->
                viewModel.onUniversityClick(
                    website
                )
            }
        )
    }

    private val adapter: UniversityPagingListAdapter by lazy {
        UniversityPagingListAdapter { website ->
            viewModel.onUniversityClick(website)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(): UniversityFragment {
            return UniversityFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.inflate(layoutInflater)
        containerBinding?.detailContainer?.addView(binding?.root)
        containerBinding?.swipeToRefresh?.isEnabled = true
        initToolbar()
        setUpUniversityListAdapter()
        initFragmentListener()
        viewModel.fetchUniversities()

        viewModel.uiState.observe(viewLifecycleOwner, this::handleUiState)
    }

    private fun initFragmentListener() {
        containerBinding?.swipeToRefresh?.setOnRefreshListener {
            //viewModel.getUniversities()
        }
    }

    private fun initToolbar() {
        containerBinding?.apply {
             toolbarTitle.text = getString(R.string.universities)
             toolbarBackButton.gone()
        }
    }

    private fun setUpUniversityListAdapter() {
        binding?.rvUniversityList?.adapter = adapter
    }

    fun handleUiState(uiState: UniversityViewModel.UiState) {
        handleLoading(uiState.loading)
        handleEmptyState(uiState.isEmpty)
        handleError(uiState.error)
        handleRoute(uiState.route)

        lifecycleScope.launch {
            viewModel.universities.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun handleEmptyState(empty: Boolean) {
        if (empty){
            binding?.rvUniversityList?.gone()
            binding?.tvEmpty?.visible()
        }else{
            binding?.tvEmpty?.gone()
            binding?.rvUniversityList?.visible()
        }
    }

    private fun handleRoute(route: UniversityViewModel.Route?) {
        route?.let {
            when (route) {
                is UniversityViewModel.Route.UniversityDetail -> {
                    routeToDetails(route.websiteUrl)
                }
            }
            viewModel.onRouted()
        }

    }

    private fun handleUniversities(universities: List<UniversityItem>?) {
        universities?.let {
            universityListAdapter.submitList(it)
            containerBinding?.swipeToRefresh?.isRefreshing = false
        }
    }

    private fun routeToDetails(webSite: String?) {
        (activity as BaseActivity).navigateToFragment(
            fragment = UniversityDetailsFragment.newInstance(webSite),
            addToBackStack = true,
            replace = true
        )
    }

    override fun onResume() {
        super.onResume()
        //viewModel.getUniversities()
    }
}
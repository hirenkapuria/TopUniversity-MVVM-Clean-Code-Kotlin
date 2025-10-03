package com.demotest.presentation.universityDetails

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.demotest.R
import com.demotest.databinding.FragmentUniversityDetailsBinding
import com.demotest.extenstion.visible
import com.demotest.presentation.base.BaseActivity
import com.demotest.presentation.base.BaseFragment
import com.demotest.presentation.universityDetails.UniversityDetailsFragmentViewModel.Companion.WEB_SITE
import org.koin.androidx.viewmodel.ext.android.viewModel

class UniversityDetailsFragment : BaseFragment() {

    private var binding: FragmentUniversityDetailsBinding?=null
    private val viewModel: UniversityDetailsFragmentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUniversityDetailsBinding.inflate(layoutInflater)
        containerBinding?.detailContainer?.addView(binding?.root)
        containerBinding?.swipeToRefresh?.isEnabled = true
        initToolbar()
        viewModel.uiState.observe(viewLifecycleOwner, this::handleUiState)
    }


    private fun initToolbar() {
        containerBinding?.apply {
            toolbarTitle.text = getString(R.string.details)
            toolbarBackButton.visible()
            toolbarBackButton.setOnClickListener {
                (activity as BaseActivity).onBackPressedDispatcher.onBackPressed()
            }
        }
    }


    fun handleUiState(uiState: UniversityDetailsFragmentViewModel.UiState) {
        handleError(uiState.error)
        handleWebSite(uiState.website)
    }

    private fun handleWebSite(website: String?) {
        containerBinding?.swipeToRefresh?.isRefreshing = false
        website?.let { website->
            binding?.webView?.settings?.javaScriptEnabled = true
            binding?.webView?.settings?.domStorageEnabled = true
            binding?.webView?.webViewClient = object : WebViewClient() {

                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                    handleLoading(true)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    handleLoading(false)
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    handleLoading(false)
                    handleError(error?.description.toString())
                }
            }


            binding?.webView?.loadUrl(website)
        }
    }


    companion object {
        @JvmStatic
        fun newInstance(webSite: String?) =
            UniversityDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(WEB_SITE, webSite)
                }
            }
    }

}
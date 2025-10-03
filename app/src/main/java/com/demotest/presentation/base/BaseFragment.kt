package com.demotest.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.demotest.databinding.FragmentBaseBinding

open class BaseFragment : Fragment() {

    var containerBinding: FragmentBaseBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        containerBinding = FragmentBaseBinding.inflate(inflater, container, false)
        return containerBinding?.root
    }

    override fun onDestroy() {
        containerBinding = null
        super.onDestroy()
    }

    fun handleLoading(isLoading: Boolean) {
        containerBinding?.let { containerBinding ->
            containerBinding.progressBar.apply {
                mainProgressbarContainer.apply {
                    if (isLoading && (visibility == View.GONE || visibility == View.INVISIBLE)) {
                        visibility = View.VISIBLE
                    } else if (!isLoading && visibility == View.VISIBLE) {
                        visibility = View.GONE
                    }
                }
            }
        }
    }


    fun handleError(errorMessage: String?) {
        errorMessage?.let {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }

}
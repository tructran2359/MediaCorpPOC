package com.t.mediacorp2359pocs.presentation.oc2097_liveblog.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.t.mediacorp2359pocs.databinding.FragmentLiveBlogDetailBinding
import com.t.mediacorp2359pocs.presentation.oc2097_liveblog.Oc2079LiveBlogActivity
import com.t.mediacorp2359pocs.utils.toast
import timber.log.Timber

class LiveBlogDetailFragment : Fragment() {

    private var mBinding: FragmentLiveBlogDetailBinding? = null
    private val mViewModel: LiveBlogDetailViewModel by viewModels()
    private val mAdapter = KeyPointsAdapter()

    companion object {
        fun newInstance(): LiveBlogDetailFragment {
            return LiveBlogDetailFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLiveBlogDetailBinding.inflate(inflater, container, false)
        return mBinding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()

        observe()
        mBinding?.liveBlog?.setEvent(Oc2079LiveBlogActivity.EVENT_ID)
        mViewModel.getEventKeyPoints()
    }

    private fun setupViews() {
        mBinding?.let {
            it.rvKeyPoints.let { rv ->
                rv.layoutManager = LinearLayoutManager(requireContext())
                rv.adapter = mAdapter
            }
        }
    }

    private fun observe() {
        mViewModel.let { vm ->
            vm.liveKeyPointsLoading.observe(viewLifecycleOwner) { isLoading ->
                mBinding?.let {
                    it.pbLoading.isVisible = isLoading
                }
            }

            vm.liveErrorMessage.observe(viewLifecycleOwner) { errorMessage ->
                requireContext().toast(errorMessage)
            }

            vm.liveKeyPoints.observe(viewLifecycleOwner) { keyPoints ->
                Timber.d("Size: ${keyPoints.size}")
                mAdapter.submitList(keyPoints)
            }
        }
    }
}
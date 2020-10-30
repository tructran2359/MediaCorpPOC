package com.t.mediacorp2359pocs.presentation.oc2097_liveblog.keypoints

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.t.mediacorp2359pocs.databinding.FragmentLiveBlogKeyPointsBinding
import com.t.mediacorp2359pocs.presentation.oc2097_liveblog.detail.KeyPointsAdapter
import com.t.mediacorp2359pocs.utils.load
import com.t.mediacorp2359pocs.utils.toast

class LiveBlogKeyPointsFragment : Fragment() {

    companion object {
        fun newInstance(): LiveBlogKeyPointsFragment {
            return LiveBlogKeyPointsFragment()
        }
    }

    private val mViewModel: LiveBlogEventPreviewViewModel by viewModels()
    private val mAdapter = KeyPointsAdapter(maxCount = 2)
    private var mBinding: FragmentLiveBlogKeyPointsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentLiveBlogKeyPointsBinding.inflate(inflater, container, false)
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
        mViewModel.getEvent()
    }

    private fun observe() {
        mViewModel.let { vm ->
            vm.liveError.observe(viewLifecycleOwner) {
                requireContext().toast(it)
            }

            vm.liveLoading.observe(viewLifecycleOwner) {loading ->
                mBinding?.let {
                    it.pbLoading.isVisible = loading
                    if (!loading && it.clContent.isGone) {
                        it.clContent.isVisible = true
                    }
                }
            }

            vm.liveEvent.observe(viewLifecycleOwner) { event ->
                mBinding?.let {
                    it.tvTitle.text = event.title
                    it.ivCover.load(event.cover)
                    mAdapter.submitList(event.keyPoints)
                }
            }
        }
    }

    private fun setupViews() {
        mBinding?.let {
            it.rvKeyPoints.let { rv ->
                rv.layoutManager = LinearLayoutManager(requireContext())
                rv.adapter = mAdapter
            }
        }
    }

}
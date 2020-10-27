package com.t.mediacorp2359pocs.presentation.oc2097_liveblog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.t.mediacorp2359pocs.databinding.FragmentLiveBlogDetailBinding

class LiveBlogDetailFragment : Fragment() {

    private var mBinding: FragmentLiveBlogDetailBinding? = null

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

        mBinding?.liveBlog?.setEvent(Oc2079LiveBlogActivity.EVENT_ID)
    }
}
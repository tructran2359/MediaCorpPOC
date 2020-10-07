package com.t.mediacorp2359pocs.presentation.oc1473_algolia

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.algolia.instantsearch.core.connection.ConnectionHandler
import com.algolia.instantsearch.helper.android.list.autoScrollToStart
import com.algolia.instantsearch.helper.android.searchbox.SearchBoxViewEditText
import com.algolia.instantsearch.helper.android.searchbox.connectView
import com.t.mediacorp2359pocs.databinding.ActivityOc1473Binding
import timber.log.Timber

class Oc1473Activity : AppCompatActivity() {

    companion object {

        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc1473Activity::class.java)
        }
    }

    private lateinit var mBinding: ActivityOc1473Binding
    private val mViewModel: SearchViewModel by viewModels()
    private val mResultAdapter = SearchResultAdapter()

    private val mConnectionHandler = ConnectionHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        mBinding = ActivityOc1473Binding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpViews()

        obseveLiveData()
    }


    override fun onDestroy() {
        mConnectionHandler.clear()
        super.onDestroy()
    }

    private fun obseveLiveData() {
        mViewModel.let { vm ->
            vm.liveSearchResults.observe(this) { results ->
                Timber.d("Result count: ${results.size}")
                mResultAdapter.submitList(results)
            }
        }
    }

    private fun setUpViews() {
        val seachBoxView = SearchBoxViewEditText(mBinding.etKeyword)

        mConnectionHandler += mViewModel.searchBox.connectView(seachBoxView)

        mBinding.rvResults.let { rv ->
            rv.layoutManager = LinearLayoutManager(this)
            rv.adapter = mResultAdapter
            rv.autoScrollToStart(mResultAdapter)
        }
    }
}
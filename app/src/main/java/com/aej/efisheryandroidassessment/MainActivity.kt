package com.aej.efisheryandroidassessment

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.aej.efisheryandroidassessment.common.presentation.BaseActivity
import com.aej.efisheryandroidassessment.common.utils.gone
import com.aej.efisheryandroidassessment.common.utils.visible
import com.aej.efisheryandroidassessment.databinding.ActivityMainBinding
import com.aej.efisheryandroidassessment.presentation.FishUiModel
import com.aej.efisheryandroidassessment.presentation.FishUiState
import com.aej.efisheryandroidassessment.presentation.item.FishItem
import com.google.gson.JsonObject
import com.jakewharton.rxbinding4.widget.textChanges
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.mikepenz.fastadapter.adapters.GenericItemAdapter
import com.mikepenz.fastadapter.adapters.ItemAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var fastAdapter: GenericFastItemAdapter
    private lateinit var itemAdapter: GenericItemAdapter

    override fun setupViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    override fun bindToolbar(): Toolbar? = null

    override fun setupBackButton(): Boolean = false

    override fun setupViews(savedInstanceState: Bundle?) {
        setupObserve()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        fastAdapter = FastItemAdapter()
        itemAdapter = ItemAdapter.items()
        fastAdapter.addAdapter(1, itemAdapter)

        viewBinding?.fishPriceRecyclerview?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = fastAdapter
        }

    }

    override fun setupOnClickListener() {
        viewBinding?.searchView?.searchFishPriceEditText?.textChanges()
            ?.debounce(800, TimeUnit.MILLISECONDS)
            ?.subscribe { charSequence ->

                val jsonObject = JsonObject().apply {
                    addProperty("komoditas", charSequence.toString().uppercase())
                }

                if (charSequence.isNullOrEmpty()) {
                    viewModel.fishPrice("")
                } else {
                    viewModel.fishPrice(jsonObject.toString())
                }
            }.let(disposable::add)
    }

    private fun setupObserve() {
        viewModel.singleLiveEvent.observe(this) { fishUiState ->
            handleFishPriceEvent(fishUiState)
        }
    }

    private fun handleFishPriceEvent(fishUiState: FishUiState?) {
        when (fishUiState) {
            is FishUiState.ShowLoading -> {
                viewBinding?.run {
                    fastAdapter.clear()
                    notFoundTextView.gone()
                    errorTextView.gone()
                    progressBar.visible()
                    fishPriceRecyclerview.gone()
                }
            }
            is FishUiState.HideLoading -> {
                viewBinding?.run {
                    progressBar.gone()
                    fishPriceRecyclerview.visible()
                }
            }
            is FishUiState.Success<*> -> {
                println("data nih ${fishUiState.data}")
                viewBinding?.run {
                    notFoundTextView.gone()
                    errorTextView.gone()
                }
                fastAdapter.add(FishItem(fishUiState.data as FishUiModel))
            }
            is FishUiState.ShowEmpty -> {
                fastAdapter.clear()
                viewBinding?.run {
                    fishPriceRecyclerview.gone()
                    errorTextView.gone()
                    notFoundTextView.visible()
                }
            }
            is FishUiState.Error -> {
                fastAdapter.clear()
                viewBinding?.run {
                    progressBar.gone()
                    fishPriceRecyclerview.gone()
                    errorTextView.visible()
                }
            }
            else -> {}
        }
    }

}
package com.aej.efisheryandroidassessment.common.presentation

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.aej.efisheryandroidassessment.presentation.FishViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {

    protected var viewBinding: VB? = null

    private var toolbar: Toolbar? = null

    protected val disposable by lazy { CompositeDisposable() }

    protected val viewModel by viewModels<FishViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = setupViewBinding()
        setContentView(viewBinding?.root)
        setupToolbar()
        setupBackButton()
        setupViews(savedInstanceState)
        setupOnClickListener()
        setupBackgroundActionBar()
    }

    abstract fun setupViewBinding(): VB
    abstract fun bindToolbar(): Toolbar?
    abstract fun setupBackButton(): Boolean
    abstract fun setupViews(savedInstanceState: Bundle?)
    abstract fun setupOnClickListener()

    private fun setupToolbar() {
        bindToolbar()?.let {
            toolbar = it
            setSupportActionBar(toolbar)
            supportActionBar?.apply {
                setDisplayShowTitleEnabled(false)
                setDisplayHomeAsUpEnabled(setupBackButton())
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun setupBackgroundActionBar() {
        window.statusBarColor = ContextCompat.getColor(
            applicationContext,
            android.R.color.white
        )
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
    
}
package com.aej.efisheryandroidassessment.common.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import com.aej.efisheryandroidassessment.presentation.FishViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseActivity<VB: ViewBinding>: AppCompatActivity() {
    
    private var toolbar: Toolbar? = null

    protected val disposable by lazy { CompositeDisposable() }

    protected val viewModel by viewModels<FishViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setupViewBinding().root)
        setupToolbar()
        setupBackButton()
        setupViews(savedInstanceState)
        setupOnClickListener()
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

    override fun onDestroy() {
        disposable.clear()
        super.onDestroy()
    }
    
}
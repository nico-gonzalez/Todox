package com.sample.android.todox.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.sample.android.todox.R
import com.sample.android.todox.common.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity(), HomeView {

    val itemsAdapter by lazy {
        ItemsAdapter(arrayListOf())
    }

    val layoutManager by lazy {
        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    val homePresenter: HomePresenter by lazy {
        HomePresenter(injector().provideGetItemsReducer())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homePresenter.attachView(this)

        setupRecyclerView()

        homePresenter.onGetItems()
    }

    override fun onStop() {
        super.onStop()
        homePresenter.detachView()
    }

    private fun setupRecyclerView() {
        itemsRV.adapter = itemsAdapter
        itemsRV.layoutManager = layoutManager
        itemsRV.setHasFixedSize(true)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun showItems(getItemsUIModel: GetItemsUIModel) {
        itemsAdapter.addItems(getItemsUIModel.items)
    }

    override fun showErrorMessage(errorMessage: String?) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG)
                .show()
    }
}

package com.sample.android.todox.home

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.sample.android.todox.R
import com.sample.android.todox.additem.AddItemBottomSheetDialogFragment
import com.sample.android.todox.common.BaseActivity
import com.sample.android.todox.common.UIModel.GetItemsUIModel
import com.sample.android.todox.stores.items.Item
import kotlinx.android.synthetic.main.activity_home.addItemBtn
import kotlinx.android.synthetic.main.activity_home.itemsRV
import kotlinx.android.synthetic.main.activity_home.progressBar

class HomeActivity : BaseActivity(), HomeView, ItemsAdapter.OnItemClicked {

  val itemsAdapter by lazy {
    ItemsAdapter(arrayListOf(), this)
  }

  val layoutManager by lazy {
    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
  }

  val homePresenter: HomePresenter by lazy {
    HomePresenter(injector().provideGetItemsReducer(),
        injector().provideDeleteItemReducer())
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_home)

    homePresenter.attachView(this)

    setupRecyclerView()

    addItemBtn.setOnClickListener {
      homePresenter.onAddItem()
    }

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

  override fun onItemClicked(position: Int, item: Item) {
    homePresenter.deleteItem(position, item)
  }

  override fun deleteItem(position: Int) {
    itemsAdapter.deleteItem(position)
  }

  override fun addItem(position: Int, item: Item) {
    itemsAdapter.addItem(position, item)
  }

  override fun showAddItem() {
    val addItemBottomSheet = AddItemBottomSheetDialogFragment()
    addItemBottomSheet.show(supportFragmentManager, addItemBottomSheet.tag)
  }
}

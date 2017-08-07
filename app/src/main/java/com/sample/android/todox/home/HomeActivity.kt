package com.sample.android.todox.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.sample.android.todox.R
import com.sample.android.todox.additem.AddItemFragment
import com.sample.android.todox.common.ui.UIModel.GetItemsUIModel
import com.sample.android.todox.common.application
import com.sample.android.todox.stores.items.Item
import kotlinx.android.synthetic.main.activity_home.addItemBtn
import kotlinx.android.synthetic.main.activity_home.itemsRV
import kotlinx.android.synthetic.main.activity_home.progressBar
import javax.inject.Inject


class HomeActivity : AppCompatActivity(), HomeView, ItemsAdapter.OnItemClicked {

  @Inject lateinit var homePresenter: HomePresenter

  val itemsAdapter by lazy {
    ItemsAdapter(arrayListOf(), this)
  }

  val layoutManager by lazy {
    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
  }


  override fun onCreate(savedInstanceState: Bundle?) {
    application().applicationComponent
        .homeSubcomponent()
        .inject(this)
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
    homePresenter.deleteItem(item)
  }

  override fun showAddItem() {
    val addItemBottomSheet = AddItemFragment()
    addItemBottomSheet.show(supportFragmentManager, addItemBottomSheet.tag)
  }
}

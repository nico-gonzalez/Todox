package com.sample.android.todox.additem

import android.app.Dialog
import android.content.Context
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialogFragment
import android.support.design.widget.CoordinatorLayout
import android.view.View
import android.widget.Toast
import com.sample.android.todox.R
import com.sample.android.todox.common.application
import kotlinx.android.synthetic.main.fragment_add_item_bottom_sheet.view.addItemDescription
import kotlinx.android.synthetic.main.fragment_add_item_bottom_sheet.view.addItemTitle
import kotlinx.android.synthetic.main.fragment_add_item_bottom_sheet.view.submitAddItem
import javax.inject.Inject

class AddItemFragment : BottomSheetDialogFragment(), AddItemView {

  @Inject
  lateinit var addItemPresenter: AddItemPresenter

  override fun onAttach(context: Context?) {
    application().applicationComponent.addItemSubcomponent()
        .inject(this)
    super.onAttach(context)
  }

  override fun onDetach() {
    addItemPresenter.detachView()
    super.onDetach()
  }

  private val bottomSheetBehaviorCallback = object : BottomSheetBehavior.BottomSheetCallback() {

    override fun onStateChanged(bottomSheet: View, newState: Int) {
      if (newState == BottomSheetBehavior.STATE_HIDDEN) {
        dismiss()
      }
    }

    override fun onSlide(bottomSheet: View, slideOffset: Float) {
    }
  }

  override fun setupDialog(dialog: Dialog?, style: Int) {
    val contentView = View.inflate(context, R.layout.fragment_add_item_bottom_sheet, null)
    dialog?.setContentView(contentView)

    addItemPresenter.attachView(this)

    contentView.submitAddItem.setOnClickListener {
      addItemPresenter.onAddItem(contentView.addItemTitle.text.toString(),
          contentView.addItemDescription.text.toString())
    }

    val params = (contentView.parent as View).layoutParams as CoordinatorLayout.LayoutParams
    val behavior = params.behavior

    if (behavior != null && behavior is BottomSheetBehavior<*>) {
      behavior.setBottomSheetCallback(bottomSheetBehaviorCallback)
    }
  }

  override fun showAddSuccess() {
    Toast.makeText(context, R.string.add_item_success, Toast.LENGTH_LONG).show()
    dismiss()
  }

  override fun showErrorMessage(errorMessage: String?) =
      Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
}
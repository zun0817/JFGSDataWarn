package com.ztzb.data.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import com.ztzb.data.R
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.viewmodel.WarnViewModel
import kotlinx.android.synthetic.main.activity_warn_form.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WarnFormActivity : BaseMVVMActivity(), AdapterView.OnItemClickListener {

    private val mViewModel: WarnViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_warn_form)
        initView()
    }

    private fun initView() {
        warn_form_listview.onItemClickListener = this
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }
}

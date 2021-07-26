package com.ztzb.data.ui.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.ztzb.data.R
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseMVVMActivity(),View.OnClickListener {

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: LoginViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        viewModelObserve()
    }

    private fun initView() {
        login_button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        MainActivity.startActivity(this)
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@LoginActivity, Observer {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@LoginActivity, Observer {
                ToastManager.show(it)
            })

        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@LoginActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

}
package com.ztzb.data.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.Observer
import com.ztzb.data.R
import com.ztzb.data.adapter.MessageAdapter
import com.ztzb.data.base.BaseMVVMFragment
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.MessageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MessageFragment : BaseMVVMFragment() {

    companion object {
        fun newInstance() = MessageFragment()
    }

    private lateinit var message_listview: ListView

    private val mViewModel: MessageViewModel by viewModel()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.message_fragment, container, false)
        initView(view)
        viewModelObserve()
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.e("*******Messageuser", isVisibleToUser.toString())
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.e("*******Messagehidden", hidden.toString())
    }

    private fun initView(view: View) {
        message_listview = view.findViewById(R.id.message_listview)
        val list = mutableListOf<String>()
        list.add("测试")
        list.add("测试")
        list.add("测试")
        list.add("测试")
        list.add("测试")
        list.add("测试")
        list.add("测试")
        val messageAdapter = MessageAdapter(activity!!, list)
        message_listview.adapter = messageAdapter
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(activity!!, Observer {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(activity!!, Observer {
                ToastManager.show(it)
            })
            messages.observe(activity!!, Observer {

            })
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(activity!!, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

}
package com.ztzb.data.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import com.ztzb.data.R
import com.ztzb.data.base.BaseMVVMFragment
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.ui.activity.WarnFormActivity
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.WarnViewModel
import kotlinx.android.synthetic.main.warn_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class WarnFragment : BaseMVVMFragment(), View.OnClickListener {

    companion object {
        fun newInstance() = WarnFragment()
    }

    private lateinit var warn_cake_rl: RelativeLayout

    private lateinit var warn_settlement_rl: RelativeLayout

    private lateinit var warn_cutter_rl: RelativeLayout

    private val mViewModel: WarnViewModel by viewModel()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.warn_fragment, container, false)
        initView(view)
        viewModelObserve()
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.e("*******Warnuser", isVisibleToUser.toString())
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        Log.e("*******Warnhidden", hidden.toString())
        if (!hidden) {
            mViewModel.requestOfWarn()
        }
    }

    private fun initView(view: View) {
        warn_cake_rl = view.findViewById(R.id.warn_cake_rl)
        warn_settlement_rl = view.findViewById(R.id.warn_settlement_rl)
        warn_cutter_rl = view.findViewById(R.id.warn_cutter_rl)
        warn_cake_rl.setOnClickListener(this)
        warn_settlement_rl.setOnClickListener(this)
        warn_cutter_rl.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.warn_cake_rl -> {
                WarnFormActivity.startActivity(activity!!, "mudcakeRisk")
            }
            R.id.warn_settlement_rl -> {
                WarnFormActivity.startActivity(activity!!, "subsideRisk")
            }
            R.id.warn_cutter_rl -> {
                WarnFormActivity.startActivity(activity!!, "stuckshieldRisk")
            }
        }
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
            warnBean.observe(activity!!, {
                if (it.mudcakeRisk > 0) {
                    warn_cake_count_tv.visibility = View.VISIBLE
                    warn_cake_count_tv.text = it.mudcakeRisk.toString()
                } else {
                    warn_cake_count_tv.visibility = View.GONE
                }
                if (it.subsideRisk > 0) {
                    warn_settlement_count_tv.visibility = View.VISIBLE
                    warn_settlement_count_tv.text = it.subsideRisk.toString()
                } else {
                    warn_settlement_count_tv.visibility = View.GONE
                }
                if (it.stuckshieldRisk > 0) {
                    warn_cutter_count_tv.visibility = View.VISIBLE
                    warn_cutter_count_tv.text = it.stuckshieldRisk.toString()
                } else {
                    warn_cutter_count_tv.visibility = View.GONE
                }
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
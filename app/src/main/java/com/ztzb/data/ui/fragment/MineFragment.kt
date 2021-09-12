package com.ztzb.data.ui.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.ztzb.data.BuildConfig
import com.ztzb.data.R
import com.ztzb.data.base.BaseMVVMFragment
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.common.AppManager
import com.ztzb.data.ui.activity.LoginActivity
import com.ztzb.data.ui.activity.ResetPasswordActivity
import com.ztzb.data.util.ToastManager
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.MineViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MineFragment : BaseMVVMFragment(), View.OnClickListener {

    companion object {
        fun newInstance() = MineFragment()
    }

    private lateinit var mine_modify_cb: CardView

    private lateinit var mine_version_cb: CardView

    private lateinit var mine_quit_cb: CardView

    private lateinit var mine_name_tv: TextView

    private lateinit var mine_no_tv: TextView

    private lateinit var mine_phone_tv: TextView

    private lateinit var mine_company_tv: TextView

    private lateinit var mine_job_tv: TextView

    private lateinit var mine_branch_tv: TextView

    private lateinit var mine_work_tv: TextView

    private lateinit var mine_joblabel_tv: TextView

    private lateinit var mine_worklabel_tv: TextView

    private val mViewModel: MineViewModel by viewModel()

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.mine_fragment, container, false)
        initView(view)
        viewModelObserve()
        return view
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            mViewModel.requestOfUserDetail(mViewModel.getToken())
        }
    }

    private fun initView(view: View) {
        mine_modify_cb = view.findViewById(R.id.mine_modify_cb)
        mine_version_cb = view.findViewById(R.id.mine_version_cb)
        mine_quit_cb = view.findViewById(R.id.mine_quit_cb)

        mine_name_tv = view.findViewById(R.id.mine_name_tv)
        mine_no_tv = view.findViewById(R.id.mine_no_tv)
        mine_phone_tv = view.findViewById(R.id.mine_phone_tv)
        mine_company_tv = view.findViewById(R.id.mine_company_tv)
        mine_job_tv = view.findViewById(R.id.mine_job_tv)
        mine_branch_tv = view.findViewById(R.id.mine_branch_tv)
        mine_work_tv = view.findViewById(R.id.mine_work_tv)
        mine_joblabel_tv = view.findViewById(R.id.mine_joblabel_tv)
        mine_worklabel_tv = view.findViewById(R.id.mine_worklabel_tv)

        mine_modify_cb.setOnClickListener(this)
        mine_version_cb.setOnClickListener(this)
        mine_quit_cb.setOnClickListener(this)

        val jobspannableString = SpannableString("职务职务")
        val jobforegroundColorSpan = ForegroundColorSpan(resources.getColor(R.color.color_f9f9f9))
        jobspannableString.setSpan(jobforegroundColorSpan, 1, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        mine_joblabel_tv.text = jobspannableString

        val workspannableString = SpannableString("职责职责")
        val workforegroundColorSpan = ForegroundColorSpan(resources.getColor(R.color.color_f9f9f9))
        workspannableString.setSpan(workforegroundColorSpan, 1, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        mine_worklabel_tv.text = workspannableString
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(activity!!, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(activity!!, {
                ToastManager.show(it)
            })
            loginBean.observe(activity!!, {
                mine_name_tv.text = it.realname
                mine_no_tv.text = it.phone
                mine_phone_tv.text = it.phone
                mine_company_tv.text = it.enterpriseName
                mine_job_tv.text = it.job
                mine_branch_tv.text = it.organization
                mine_work_tv.text = it.duty
            })
            data.observe(activity!!, {
                LoginActivity.startActivity(activity!!)
                activity!!.finish()
            })
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.mine_modify_cb -> {
                ResetPasswordActivity.startActivity(activity!!, 2)
            }
            R.id.mine_version_cb -> {
                showToast("已是最新版本，版本号：v" + BuildConfig.VERSION_NAME)
            }
            R.id.mine_quit_cb -> {
                mViewModel.requestOfLoginOut(mViewModel.getToken())
            }
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
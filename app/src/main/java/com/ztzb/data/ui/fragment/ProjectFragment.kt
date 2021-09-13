package com.ztzb.data.ui.fragment

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import com.ztzb.data.R
import com.ztzb.data.base.BaseMVVMFragment
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.ui.activity.ProjectDetailActivity
import com.ztzb.data.util.ToastManager
import com.ztzb.data.util.ViewTouchUtil
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.ProjectViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProjectFragment : BaseMVVMFragment(), View.OnClickListener {

    companion object {
        fun newInstance() = ProjectFragment()
    }

    private lateinit var project_detail_img: AppCompatImageView

    private lateinit var project_message_img: AppCompatImageView

    private lateinit var project_length_tv:TextView

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: ProjectViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.project_fragment, container, false)
        initView(view)
        viewModelObserve()
        return view
    }

    private fun initView(view: View) {
        project_detail_img = view.findViewById(R.id.project_detail_img)
        project_message_img = view.findViewById(R.id.project_message_img)
        project_length_tv = view.findViewById(R.id.project_length_tv)
        project_detail_img.setOnClickListener(this)
        project_message_img.setOnClickListener(this)
        ViewTouchUtil.expandViewTouchDelegate(project_detail_img)
        ViewTouchUtil.expandViewTouchDelegate(project_message_img)

        val spannableString = SpannableString("线路全长 154 km")
        val foregroundColorSpan = ForegroundColorSpan(resources.getColor(R.color.color_36F9F9))
        spannableString.setSpan(foregroundColorSpan, 5, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        project_length_tv.text = spannableString
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.project_detail_img -> {
                ProjectDetailActivity.startActivity(activity!!)
            }
            R.id.project_message_img -> {

            }
        }
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
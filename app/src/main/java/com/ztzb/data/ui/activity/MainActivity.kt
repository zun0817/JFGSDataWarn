package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.RadioGroup
import com.ztzb.data.R
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.ui.fragment.MessageFragment
import com.ztzb.data.ui.fragment.ProjectFragment
import com.ztzb.data.ui.fragment.WarnFragment
import com.ztzb.data.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseMVVMActivity(), RadioGroup.OnCheckedChangeListener {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private var projectFragment: ProjectFragment? = null
    private var warnFragment: WarnFragment? = null
    private var messageFragment: MessageFragment? = null

    private val mViewModel: MainViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        main_radiogroup.setOnCheckedChangeListener(this)
        main_project_rb.isChecked = true
        projectFragment = ProjectFragment.newInstance()
        warnFragment = WarnFragment.newInstance()
        messageFragment = MessageFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.main_container, projectFragment!!)
            .commitAllowingStateLoss()
        supportFragmentManager.beginTransaction().add(R.id.main_container, warnFragment!!)
            .commitAllowingStateLoss()
        supportFragmentManager.beginTransaction().add(R.id.main_container, messageFragment!!)
            .commitAllowingStateLoss()
        supportFragmentManager.beginTransaction().hide(warnFragment!!).commitAllowingStateLoss()
        supportFragmentManager.beginTransaction().hide(messageFragment!!).commitAllowingStateLoss()
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        when (checkedId) {
            R.id.main_project_rb -> {
                main_title_tv.text = "项目"
                checkNull()
                fragmentTransaction.hide(warnFragment!!)
                fragmentTransaction.hide(messageFragment!!)
                fragmentTransaction.show(projectFragment!!)
                fragmentTransaction.commitAllowingStateLoss()
            }
            R.id.main_warn_rb -> {
                main_title_tv.text = "预警"
                checkNull()
                fragmentTransaction.hide(projectFragment!!)
                fragmentTransaction.hide(messageFragment!!)
                fragmentTransaction.show(warnFragment!!)
                fragmentTransaction.commitAllowingStateLoss()
            }
            R.id.main_message_rb -> {
                main_title_tv.text = "消息"
                checkNull()
                fragmentTransaction.hide(warnFragment!!)
                fragmentTransaction.hide(projectFragment!!)
                fragmentTransaction.show(messageFragment!!)
                fragmentTransaction.commitAllowingStateLoss()
            }
        }
    }

    private fun checkNull() {
        if (projectFragment == null) {
            projectFragment = ProjectFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.main_container, projectFragment!!)
                .commitAllowingStateLoss()
        }
        if (warnFragment == null) {
            warnFragment = WarnFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.main_container, warnFragment!!)
                .commitAllowingStateLoss()
        }
        if (messageFragment == null) {
            messageFragment = MessageFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.main_container, messageFragment!!)
                .commitAllowingStateLoss()
        }
    }

}
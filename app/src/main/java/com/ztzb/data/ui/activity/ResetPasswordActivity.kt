package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import com.ztzb.data.R
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.util.CommonUtils
import com.ztzb.data.util.CountDownTimerUtils
import com.ztzb.data.util.ToastManager
import com.ztzb.data.util.ViewTouchUtil
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.LoginViewModel
import com.ztzb.data.viewmodel.ResetPasswordViewModel
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_reset_password.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ResetPasswordActivity : BaseMVVMActivity(), View.OnClickListener {

    companion object {
        private val TAG = ResetPasswordActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, ResetPasswordActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private var smsCode: String? = null

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: ResetPasswordViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        viewModelObserve()
        initView()
    }

    private fun initView() {
        reset_back_img.setOnClickListener(this)
        reset_sendsms_tv.setOnClickListener(this)
        reset_button.setOnClickListener(this)
        reset_clear_img.setOnClickListener(this)
        reset_sms_clear_img.setOnClickListener(this)
        reset_new_clear_img.setOnClickListener(this)
        reset_once_clear_img.setOnClickListener(this)
        ViewTouchUtil.expandViewTouchDelegate(reset_back_img)
        ViewTouchUtil.expandViewTouchDelegate(reset_clear_img)
        ViewTouchUtil.expandViewTouchDelegate(reset_sms_clear_img)
        ViewTouchUtil.expandViewTouchDelegate(reset_new_clear_img)
        ViewTouchUtil.expandViewTouchDelegate(reset_once_clear_img)
        reset_phone_edit.addTextChangedListener(phoneTextWatcher)
        reset_sms_edit.addTextChangedListener(smsTextWatcher)
        reset_new_edit.addTextChangedListener(newTextWatcher)
        reset_once_edit.addTextChangedListener(onceTextWatcher)
        if (mViewModel.getAccount().isNotBlank()) {
            reset_phone_edit.setText(mViewModel.getAccount())
            reset_phone_edit.setSelection(mViewModel.getAccount().length)
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.reset_back_img -> this.finish()
            R.id.reset_clear_img -> reset_phone_edit.setText("")
            R.id.reset_sms_clear_img -> reset_sms_edit.setText("")
            R.id.reset_new_clear_img -> reset_new_edit.setText("")
            R.id.reset_once_clear_img -> reset_once_edit.setText("")
            R.id.reset_sendsms_tv -> getSmsCode()
            R.id.reset_button -> requestOfResetPassword()
        }
    }

    private fun getSmsCode() {
        val phone = reset_phone_edit.text.toString().trim()
        if (phone.isBlank()) {
            showToast("请输入手机号")
        } else if (!CommonUtils.isMobilPhone(phone)) {
            showToast("手机号格式不正确")
        } else {
            mViewModel.requestOfSendSMS(phone)
        }
    }

    private fun requestOfResetPassword() {
        val phone = reset_phone_edit.text.toString().trim()
        val sms = reset_sms_edit.text.toString().trim()
        val new = reset_new_edit.text.toString().trim()
        val once = reset_once_edit.text.toString().trim()
        smsCode?.let {
            when {
                sms != smsCode -> {
                    showToast("验证码输入有误")
                }
                new != once -> {
                    showToast("新密码不一致")
                }
                else -> {
                    mViewModel.setAccount(phone)
                    mViewModel.requestOfResetPassword(phone, once)
                }
            }
        }
    }

    private val phoneTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            val sms = reset_sms_edit.text.toString().trim()
            val new = reset_new_edit.text.toString().trim()
            val once = reset_once_edit.text.toString().trim()
            reset_button.isEnabled = sms.isNotBlank() && s.toString().trim()
                .isNotBlank() && new.isNotBlank() && once.isNotBlank()

            reset_clear_img.visibility = if (s.toString().trim().isNotBlank()) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }

    private val smsTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            val phone = reset_phone_edit.text.toString().trim()
            val new = reset_new_edit.text.toString().trim()
            val once = reset_once_edit.text.toString().trim()
            reset_button.isEnabled = phone.isNotBlank() && s.toString().trim()
                .isNotBlank() && new.isNotBlank() && once.isNotBlank()

            reset_sms_clear_img.visibility = if (s.toString().trim().isNotBlank()) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }

    private val newTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            val sms = reset_sms_edit.text.toString().trim()
            val phone = reset_phone_edit.text.toString().trim()
            val once = reset_once_edit.text.toString().trim()
            reset_button.isEnabled = sms.isNotBlank() && s.toString().trim()
                .isNotBlank() && phone.isNotBlank() && once.isNotBlank()

            reset_new_clear_img.visibility = if (s.toString().trim().isNotBlank()) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }

    private val onceTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            val phone = reset_phone_edit.text.toString().trim()
            val new = reset_new_edit.text.toString().trim()
            val sms = reset_sms_edit.text.toString().trim()
            reset_button.isEnabled = phone.isNotBlank() && s.toString().trim()
                .isNotBlank() && new.isNotBlank() && sms.isNotBlank()

            reset_once_clear_img.visibility = if (s.toString().trim().isNotBlank()) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@ResetPasswordActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@ResetPasswordActivity, {
                ToastManager.show(it)
            })
            smscode.observe(this@ResetPasswordActivity, {
                smsCode = it
                val countDownTimer = CountDownTimerUtils(reset_sendsms_tv, 60000, 1000)
                countDownTimer.start()
            })
            content.observe(this@ResetPasswordActivity, {
                mViewModel.setPassword(reset_once_edit.text.toString().trim())
                MainActivity.startActivity(this@ResetPasswordActivity)
                this@ResetPasswordActivity.finish()
            })
        }
    }

    private fun showLoading() {
        loadingDialog.showLoading(this@ResetPasswordActivity, "")
    }

    private fun dismissLoading() {
        loadingDialog.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }
}
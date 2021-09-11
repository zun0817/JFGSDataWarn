package com.ztzb.data.ui.activity

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.CompoundButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ztzb.data.R
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.util.DeviceUtil
import com.ztzb.data.util.ToastManager
import com.ztzb.data.util.ViewTouchUtil
import com.ztzb.data.view.LoadingDialog
import com.ztzb.data.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class LoginActivity : BaseMVVMActivity(), View.OnClickListener,
    CompoundButton.OnCheckedChangeListener {

    private var isShow = true

    private val loadingDialog: LoadingDialog by lazy { LoadingDialog() }

    private val mViewModel: LoginViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
        viewModelObserve()
        checkPermission()
    }

    private fun initView() {
        login_button.setOnClickListener(this)
        login_clear_img.setOnClickListener(this)
        login_eye_img.setOnClickListener(this)
        login_reset_tv.setOnClickListener(this)
        login_remind_cb.setOnCheckedChangeListener(this)
        login_account_edit.addTextChangedListener(accountTextWatcher)
        login_password_edit.addTextChangedListener(passwordTextWatcher)
        ViewTouchUtil.expandViewTouchDelegate(login_clear_img)
        ViewTouchUtil.expandViewTouchDelegate(login_eye_img)
        login_password_edit.transformationMethod =
            PasswordTransformationMethod.getInstance()
        login_remind_cb.isChecked = mViewModel.getRemindPsw()
        if (mViewModel.getAccount().isNotBlank()) {
            login_account_edit.setText(mViewModel.getAccount())
            login_account_edit.setSelection(mViewModel.getAccount().length)
        }
        if (mViewModel.getPassword().isNotBlank() && mViewModel.getRemindPsw()) {
            login_password_edit.setText(mViewModel.getPassword())
            login_password_edit.setSelection(mViewModel.getPassword().length)
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.login_clear_img -> login_account_edit.setText("")
            R.id.login_reset_tv -> ResetPasswordActivity.startActivity(this)
            R.id.login_eye_img -> {
                val content = login_password_edit.text.toString().trim()
                if (content.isBlank()) return
                if (isShow) {
                    login_eye_img.setImageResource(R.mipmap.icon_hide)
                    login_password_edit.transformationMethod =
                        PasswordTransformationMethod.getInstance()
                } else {
                    login_eye_img.setImageResource(R.mipmap.icon_show)
                    login_password_edit.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                }
                isShow = !isShow
                login_password_edit.setSelection(content.length)
            }
            R.id.login_button -> {
                val name = login_account_edit.text.toString().trim()
                val password = login_password_edit.text.toString().trim()
                name.takeIf { it.isBlank() }?.let {
                    showToast("请输入账户名")
                    return
                }
                password.takeIf { it.isBlank() }?.let {
                    showToast("请输入密码")
                    return
                }
                mViewModel.setAccount(name)
                mViewModel.setPassword(password)
                mViewModel.requestOfLogin(name, password, DeviceUtil.getDeviceId(this))
            }
        }
    }

    private fun viewModelObserve() {
        mViewModel.apply {
            loadingDialog.observe(this@LoginActivity, {
                when (it) {
                    true -> showLoading()
                    false -> dismissLoading()
                }
            })
            toastText.observe(this@LoginActivity, {
                ToastManager.show(it)
            })
            loginBean.observe(this@LoginActivity, {
                MainActivity.startActivity(this@LoginActivity)
                this@LoginActivity.finish()
            })
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        mViewModel.setRemindPsw(isChecked)
    }

    private val accountTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            val password = login_password_edit.text.toString().trim()
            login_button.isEnabled = password.isNotBlank() && s.toString().trim().isNotBlank()
            login_clear_img.visibility = if (s.toString().trim().isNotBlank()) {
                View.VISIBLE
            } else {
                View.INVISIBLE
            }
        }
    }

    private val passwordTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            val account = login_account_edit.text.toString().trim()
            login_button.isEnabled = account.isNotBlank() && s.toString().trim().isNotBlank()
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

    private fun checkPermission() {
        var targetSdkVersion = 0
        val permissionString = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE
        )
        try {
            val info = this.packageManager.getPackageInfo(this.packageName, 0)
            targetSdkVersion = info.applicationInfo.targetSdkVersion
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (targetSdkVersion >= Build.VERSION_CODES.M) {
                val isAllGranted = checkPermissionAllGranted(permissionString)
                if (isAllGranted) {
                    return
                }
                ActivityCompat.requestPermissions(
                    this,
                    permissionString, 1
                )
            }
        }
    }

    private fun checkPermissionAllGranted(permissions: Array<String>): Boolean {
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permission
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

}
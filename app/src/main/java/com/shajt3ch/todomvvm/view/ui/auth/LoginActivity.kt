package com.shajt3ch.todomvvm.view.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.model.remote.request.auth.LoginRequest
import com.shajt3ch.todomvvm.model.remote.response.auth.LoginResponse
import com.shajt3ch.todomvvm.util.Validator
import com.shajt3ch.todomvvm.view.ui.main.MainActivity
import com.shajt3ch.todomvvm.viewmodel.auth.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class LoginActivity : AppCompatActivity() {

    companion object {
        const val TAG = "LoginActivity"
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btn_login.onClick { prepareLogin() }
        tv_signup.onClick { launchSignUpActivity() }

        observer()
    }

    private fun launchSignUpActivity() {
        startActivity(intentFor<SignUpActivity>())

    }

    private fun prepareLogin() {
        val email = txt_userId.text.toString()
        val password = txt_password.text.toString()

        if (!Validator.validateEmail(email)) {


            alert {
                isCancelable = false
                title = getString(R.string.email_validator_title)
                message = getString(R.string.validation_email_failed)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()
        } else if (!Validator.validatePassword(password)) {
            alert {
                isCancelable = false
                title = getString(R.string.password_validator_title)
                message = getString(R.string.validation_password_failed)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()
        } else {
            val loginRequest = LoginRequest(email, password)

            login(loginRequest)
        }


    }


    private fun login(login_request: LoginRequest) {

        viewModel.login(login_request).observe(this, Observer {

            it?.let {
                saveUserDetail(it)
            }

        })
    }

    private fun saveUserDetail(loginResponse: LoginResponse) {
        viewModel.saveUserDetail(loginResponse).observe(this, Observer {

            if (it) {
                finish()
                startActivity(intentFor<MainActivity>())
            }

        })
    }

    private fun observer() {
        viewModel.isError.observe(this, Observer {
            errorDialog(it)
        })

        viewModel.isSuccess.observe(this, Observer {
            if (!it) {
                unSuccessFulDialog()
            }
        })
    }


    private fun unSuccessFulDialog() {
        alert {
            title = getString(R.string.title_un_successful_dialog)
            message = viewModel.errorMsg.value.toString()
            isCancelable = false
            positiveButton(getString(R.string.btn_ok)) { dialog ->
                dialog.dismiss()
            }
        }.show()
    }

    private fun errorDialog(errorMsg: String) {
        alert {
            title = getString(R.string.title_error_dialog)
            message = errorMsg
            isCancelable = false
            positiveButton(getString(R.string.btn_ok)) { dialog ->
                dialog.dismiss()
            }
        }.show()

    }
}

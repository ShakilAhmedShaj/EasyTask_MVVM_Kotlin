package com.shajt3ch.todomvvm.view.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.model.remote.request.auth.RegisterRequest
import com.shajt3ch.todomvvm.util.Validator
import com.shajt3ch.todomvvm.viewmodel.auth.SignUpViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick

class SignUpActivity : AppCompatActivity() {

    companion object {
        const val TAG = "SignUpActivity"
    }

    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        btn_sign_up.onClick { prepareSignUp() }
        tv_login.onClick { launchLoginActivity() }

        observer()
    }

    private fun launchLoginActivity() {
        startActivity(intentFor<LoginActivity>())
    }

    private fun prepareSignUp() {
        val name = txt_userName.text.toString()
        val email = txt_email.text.toString()
        val password = txt_password.text.toString()
        val confirmPassword = txt_confirm_password.text.toString()

        if (name.isEmpty()) {
            alert {
                isCancelable = false
                title = getString(R.string.empty_user_name_title)
                message = getString(R.string.empty_user_name_msg)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()
        } else if (!Validator.validateEmail(email)) {
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
        } else if (!Validator.validatePassword(confirmPassword)) {
            alert {
                isCancelable = false
                title = getString(R.string.validation_confirm_password_title)
                message = getString(R.string.validation_confirm_password_failed)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()
        } else if (password != confirmPassword) {
            alert {
                isCancelable = false
                title = getString(R.string.error_confirm_password_title)
                message = getString(R.string.error_confirm_password_msg)
                positiveButton("OK") {
                    it.dismiss()
                }
            }.show()
        } else {
            val signUpData = RegisterRequest(name, email, password, confirmPassword)

            signUp(signUpData)
        }

    }

    private fun signUp(signUpData: RegisterRequest) {

        viewModel.register(signUpData)
    }

    private fun observer() {
        viewModel.isError.observe(this, Observer {
            errorDialog(it)
        })

        viewModel.isSuccess.observe(this, Observer {
            it?.run {
                if (it) {
                    successDialog()
                } else {
                    unSuccessFulDialog()
                }
            }
        })

        viewModel.loading.observe(this, Observer {
            pb_sign_up.visibility = if (it) View.VISIBLE else View.GONE

        })
    }


    private fun successDialog() {
        alert {
            title = getString(R.string.title_successful_signup)
            message = getString(R.string.msg_signup_successful)
            isCancelable = false
            positiveButton(getString(R.string.btn_ok)) { dialog ->
                finish()
                dialog.dismiss()
            }
        }.show()
    }

    private fun unSuccessFulDialog() {
        alert {
            title = getString(R.string.title_un_successful_dialog)
            message = getString(R.string.msg_add_post_un_successful)
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

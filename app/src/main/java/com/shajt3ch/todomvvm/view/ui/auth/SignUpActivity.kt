package com.shajt3ch.todomvvm.view.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.shajt3ch.todomvvm.R
import com.shajt3ch.todomvvm.model.remote.request.auth.RegisterRequest
import com.shajt3ch.todomvvm.viewmodel.auth.RegisterViewModel
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.sdk27.coroutines.onClick
import kotlin.math.log

class SignUpActivity : AppCompatActivity() {

    companion object {
        const val TAG = "SignUpActivity"
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        btn_sign_up.onClick { prepareSignUp() }

        viewModel.progress.observe(this, Observer {
            pb_reg.visibility = if (it) View.VISIBLE else View.GONE
        })

        tv_login.onClick { launchLoginActivity() }
    }

    private fun launchLoginActivity() {
        startActivity(intentFor<LoginActivity>())
    }

    private fun prepareSignUp() {
        val name = txt_userName.text.toString()
        val email = txt_email.text.toString()
        val password = txt_password.text.toString()
        val confirmPassword = txt_confirm_password.text.toString()

        //check for the empty value
        when {
            name.isEmpty() -> {

                alert {
                    isCancelable = false
                    title = getString(R.string.empty_user_name_title)
                    message = getString(R.string.empty_user_name_msg)
                    positiveButton("OK") {
                        it.dismiss()
                    }
                }.show()

            }
            email.isEmpty() -> {

                alert {
                    isCancelable = false
                    title = getString(R.string.empty_email_title)
                    message = getString(R.string.empty_email_msg)
                    positiveButton("OK") {
                        it.dismiss()
                    }
                }.show()

            }
            password.isEmpty() -> {

                alert {
                    isCancelable = false
                    title = getString(R.string.empty_password_title)
                    message = getString(R.string.empty_password_msg)
                    positiveButton("OK") {
                        it.dismiss()
                    }
                }.show()
            }
            confirmPassword.isEmpty() -> {

                alert {
                    isCancelable = false
                    title = getString(R.string.empty_confirm_password_title)
                    message = getString(R.string.empty_confirm_password_msg)
                    positiveButton("OK") {
                        it.dismiss()
                    }
                }.show()
            }
            password != confirmPassword -> {

                alert {
                    isCancelable = false
                    title = getString(R.string.error_confirm_password_title)
                    message = getString(R.string.error_confirm_password_msg)
                    positiveButton("OK") {
                        it.dismiss()
                    }
                }.show()
            }
            else -> {

                val signUpData = RegisterRequest(name, email, password, confirmPassword)

                signUp(signUpData)


            }
        }

    }

    private fun signUp(signUpData: RegisterRequest) {

        viewModel.register(signUpData).observe(this, Observer { data ->

            if (data.code() == 201) {

                val data = data.body()


                alert {
                    isCancelable = false
                    title = getString(R.string.success_sign_up_title)
                    message = getString(R.string.success_sign_up_msg)
                    positiveButton("OK") {
                        it.dismiss()

                        startActivity(intentFor<LoginActivity>())
                    }
                }.show()



                Log.d(TAG, "Name : ${data?.user_name}  message : ${data?.email}")

            } else {

                alert {
                    isCancelable = false
                    title = getString(R.string.error_sign_up_title)
                    message = getString(R.string.error_sign_up_msg)
                    positiveButton("OK") {
                        it.dismiss()
                    }
                }.show()

                Log.e(TAG, "error code : ${data.code()}  message : ${data.errorBody()}")

            }

        })
    }
}

package com.t.mediacorp2359pocs.presentation.oc813

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.mediacorp.mobilesso.MCMobileSSOAuthStatus
import com.mediacorp.mobilesso.MCMobileSSOUserGender
import com.t.mediacorp2359pocs.databinding.ActivityOc813SignUpBinding
import com.t.mediacorp2359pocs.utils.isValidEmail
import com.t.mediacorp2359pocs.utils.textAsString
import com.t.mediacorp2359pocs.utils.toast
import java.util.*
import timber.log.Timber

class Oc813SignUpActivity : BaseMobileSsoActivity() {

    companion object {
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, Oc813SignUpActivity::class.java)
        }
    }

    private lateinit var mBinding: ActivityOc813SignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityOc813SignUpBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setUpViews()
    }

    override fun onAuthChange(status: MCMobileSSOAuthStatus?, message: String?) {
        Timber.d("SSO: status: `$status` with message: `$message`")
        hideLoading()
        when(status) {
            MCMobileSSOAuthStatus.Authenticated -> {
                toast("New Account is created")
                finish()
            }

            else -> {
                val errorMessage = message ?: "Something went wrong. Please try again later."
                toast(errorMessage)
            }
        }
    }

    private fun setUpViews() {
        mBinding.let { b ->
            b.btnSignUp.setOnClickListener {
                signUp()
            }
        }
    }

    private fun signUp() {
        mBinding.let { b ->
            val firstName = b.etFirstName.textAsString()
            if (firstName.isEmpty()) {
                toast("First name: empty")
                return
            }

            val lastName = b.etLastName.textAsString()
            if(lastName.isEmpty()) {
                toast("Last name: empty")
                return
            }

            val email = b.etEmail.textAsString()
            if (!email.isValidEmail()) {
                toast("Email: invalid")
                return
            }

            val password = b.etPassword.textAsString()
            if (password.isEmpty()) {
                toast("Password: empty")
                return
            }

            val confirmPassword = b.etConfirmPassword.textAsString()
            if (password != confirmPassword) {
                toast("Wrong Password")
                return
            }

            showLoading()
            mMobileSso.signUp(
                firstName,
                lastName,
                email,
                password,
                MCMobileSSOUserGender.PreferNotToSay,
                Date(System.currentTimeMillis()),
                false,
                ""
            )
        }
    }

    private fun showLoading() {
        mBinding.pbLoading.isVisible = true
    }

    private fun hideLoading() {
        mBinding.pbLoading.isGone = true
    }
}
package com.mht.favqs.activities

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log.i
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.mht.favqs.network.Constants.Companion.ACCESS_TOKEN
import com.mht.favqs.network.Constants.Companion.LOGIN
import com.mht.favqs.viewModel.LoginViewModel
import com.mht.favqs.R
import com.mht.favqs.repository.SecurePreferences
import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Set up the login form.
        password.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin()
                return@OnEditorActionListener true
            }
            false
        })

        email_sign_in_button.setOnClickListener { attemptLogin() }
        initVM()
    }

    private fun initVM() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        viewModel.userSession.observe(this, Observer { session ->
            i("session", "${session?.login} -> ${session?.token}")
            val intent = MyFavQsActivity.newIntent(this)
            SecurePreferences.getInstance(this@LoginActivity).saveString(this@LoginActivity, ACCESS_TOKEN, session?.token)
            SecurePreferences.getInstance(this@LoginActivity).saveString(this@LoginActivity, LOGIN, session?.login)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        })
        viewModel.errorUserSession.observe(this, Observer { message ->
            longToast(message!!)
            showProgress(false)
        })
    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin() {

        // Reset errors.
        emailInLayout.error = null
        passwordInLayout.error = null

        // Store values at the time of the login attempt.
        val emailStr = email.text.toString()
        val passwordStr = password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(passwordStr)) {
            passwordInLayout.error = getString(R.string.error_field_required)
            focusView = password
            cancel = true
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(emailStr)) {
            emailInLayout.error = getString(R.string.error_field_required)
            focusView = email
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            viewModel.signIn(emailStr, passwordStr)
        }
    }

    private fun showProgress(show: Boolean) {
        if (show) {
            login_progress.visibility = View.VISIBLE
            login_form.visibility = View.GONE
        } else {
            login_progress.visibility = View.GONE
            login_form.visibility = View.VISIBLE
        }
    }
}

fun Context.longToast(message: CharSequence): Toast = Toast
        .makeText(this, message, Toast.LENGTH_LONG)
        .apply {
            show()
        }
package com.ardadeniz.assignment6

import android.os.Bundle

import android.content.Intent
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class DeleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        if (SharedPrefManager.getInstance(this).isLoggedIn) {
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvRegister = findViewById<TextView>(R.id.tvRegister)

        //calling the method userLogin() for login the user
        btnLogin.setOnClickListener(View.OnClickListener {
            userDelete()
        })

        //if user presses on textview it call the activity RegisterActivity
        tvRegister.setOnClickListener(View.OnClickListener {
            finish()
            startActivity(Intent(applicationContext, LoginActivity::class.java))
        })
    }

    private fun userDelete() {

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val etName = findViewById<EditText>(R.id.etUserName)
        val etPassword = findViewById<EditText>(R.id.etUserPassword)
        //first getting the values
        val username = etName.text.toString()
        val password = etPassword.text.toString()
        //validating inputs
        if (TextUtils.isEmpty(username)) {
            etName.error = "Please enter your username"
            etName.requestFocus()
            return
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.error = "Please enter your password"
            etPassword.requestFocus()
            return
        }

        val stringRequest = object : StringRequest(Request.Method.POST, URLs.URL_DELETE,
            Response.Listener { response -> progressBar.visibility = View.GONE },
            Response.ErrorListener { error -> Toast.makeText(applicationContext, error.message, Toast.LENGTH_SHORT).show() }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>{
                val params = HashMap<String, String>()
                params["username"] = username
                params["password"] = password
                return params
            }
        }
        Toast.makeText(applicationContext,"Account deleted successfully...",Toast.LENGTH_SHORT).show()
        startActivity(Intent(applicationContext, LoginActivity::class.java))
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest)
    }
}
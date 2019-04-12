package com.rizkarhm.latarte

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private val TAG = "RegisterActivity"

    override fun onCreate(savedInstanceState: Bundle?) {

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val button_register = findViewById<Button>(R.id.button_register) as Button
        button_register.setOnClickListener {
            saveData()
            val buttonRegister = Intent(applicationContext, ProfilActivity::class.java)
            startActivity(buttonRegister)
        }
        val toLogin = findViewById<Button>(R.id.textToLogin) as TextView
        toLogin.setOnClickListener {
            val intentRegister = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intentRegister)
            finish()}
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun saveData() {
        val email = inputEditTextEmail.text.toString()
        val password = inputEditTextPassword.text.toString()

        when {
            email.isEmpty() -> inputEditTextEmail.error = "Email tidak boleh kosong"
            password.isEmpty() -> inputEditTextPassword.error = "Password tidak boleh kosong"
            else -> {
                val progressDialog = ProgressDialog(
                    this,
                    R.style.Theme_MaterialComponents_Light_Dialog
                )
                progressDialog.isIndeterminate = true
                progressDialog.setMessage("Tunggu Sebentar...")
                progressDialog.show()

                auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success")
                            val user = auth.currentUser
                            updateUI(user)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(baseContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()
                            updateUI(null)
                        }
                    }

                progressDialog.hide()
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {


    }



}

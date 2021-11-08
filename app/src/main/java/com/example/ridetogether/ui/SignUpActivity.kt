package com.example.ridetogether.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.ridetogether.R
import com.example.ridetogether.databinding.ActivitySignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    lateinit var auth : FirebaseAuth
    lateinit var binding : ActivitySignUpBinding
    companion object{
        private const val TAG = "SignUpActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up)

        auth = FirebaseAuth.getInstance()
        binding.btnSignup.setOnClickListener {
            val email = binding.txtEmail2.text.toString()
            val password = binding.txtPassword2.text.toString()
            if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                Log.d(TAG , "Email or Password is empty")
            }else{
                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener( this , OnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    }else {
                        Toast.makeText(this, "Registration Failed", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
        binding.btnClear.setOnClickListener {
            clear()
        }

    }

    fun clear() {
        binding.txtUsername.setText("")
        binding.txtEmail2.setText("")
        binding.txtPassword2.setText("")
    }

}
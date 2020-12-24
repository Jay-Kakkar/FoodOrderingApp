package com.example.foodato.SignIn

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodato.FoodatoDatabase.FoodatoDatabaseBuild
import com.example.foodato.R
import com.example.foodato.databinding.FragmentSignInBinding
import com.example.foodato.home.HomeActivity


@Suppress("DEPRECATION")
class SignIn : Fragment() {
    private lateinit var signInBinding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dataSource = FoodatoDatabaseBuild.getInstance(application).loginDataDao
//
//        activity?.onBackPressedDispatcher?.addCallback(this,object :OnBackPressedCallback(true){
//            override fun handleOnBackPressed() {
//                signInBinding.editTextTextEmailAddress.text.clear()
//                signInBinding.editTextTextPassword.text.clear()
//            }
//        })

        val viewModelFactory = signInViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(SignInViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        signInBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        signInBinding.signUp.setOnClickListener {
            findNavController().navigate(SignInDirections.actionSignIn2ToSignUp2())
        }
        signInBinding.signIn.setOnClickListener {
            checkForUser()

        }
        signInBinding.lifecycleOwner = this

        return signInBinding.root
    }

    fun checkForUser() {

Log.e(this.toString(), "LOOOOOOOOOOOOOOOOOP")
var pass: String? =null
            var email = signInBinding.editTextTextEmailAddress.text.toString()

            var password = signInBinding.editTextTextPassword.text.toString()
            if (email.isEmpty()) {
                Toast.makeText(context, "Email can't be empty", Toast.LENGTH_SHORT).show()
            }
            if (password.isEmpty()) {
                Toast.makeText(context, "Password can't be empty", Toast.LENGTH_SHORT).show()

            }
            if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.getCurrentUserData(email, password)


                viewModel._password.observe(viewLifecycleOwner, Observer {
                    pass = it
                    when {
                        it == null -> {
                            Toast.makeText(context, "Email doesn't exist", Toast.LENGTH_SHORT)
                                .show()
                            signInBinding.editTextTextEmailAddress.text.clear()
                            signInBinding.editTextTextPassword.text.clear()
                        }
                        password == it -> {
                            Toast.makeText(context, "Signed in", Toast.LENGTH_SHORT).show()
//                            viewModel.update(true, email)
                            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
                            prefs.edit().putBoolean("Islogin", true).apply()
                            var intent= Intent(activity,HomeActivity::class.java)
                            startActivity(intent)
                        }
                        else -> {
                            Toast.makeText(context, "Entered password is wrong", Toast.LENGTH_SHORT)
                                .show()
                            signInBinding.editTextTextPassword.text.clear()
                        }
                    }
                })
            }

    }
}
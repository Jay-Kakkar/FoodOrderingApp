package com.example.foodato.SignIn

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodato.FoodatoDatabase.FoodatoDatabaseBuild
import com.example.foodato.R
import com.example.foodato.SignUp.signUpViewModelFactory
import com.example.foodato.databinding.FragmentSignInBinding


class SignIn : Fragment() {
    private lateinit var signInBinding: FragmentSignInBinding
    private lateinit var viewModel: SignInViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = requireNotNull(this.activity).application
        val dataSource = FoodatoDatabaseBuild.getInstance(application).loginDataDao

        activity?.onBackPressedDispatcher?.addCallback(this,object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                signInBinding.editTextTextEmailAddress.text.clear()
                signInBinding.editTextTextPassword.text.clear()
            }
        })

        val viewModelFactory = signUpViewModelFactory(dataSource, application)
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
        return signInBinding.root
    }

    fun checkForUser() {

Log.e(this.toString(),"LOOOOOOOOOOOOOOOOOP")
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


                viewModel._password.observe(this, Observer {
                    pass=it
                    if (password == it) {
                        Toast.makeText(context, "Signed in", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_signIn2_to_menu)
                    } else {
                        Toast.makeText(context, "Entered password is wrong", Toast.LENGTH_SHORT)
                            .show()
                        signInBinding.editTextTextPassword.text.clear()
                    }
                })
            }

    }
}
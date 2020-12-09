package com.example.foodato.SignUp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.foodato.FoodatoDatabase.FoodatoDatabaseBuild
import com.example.foodato.FoodatoDatabase.LoginData
import com.example.foodato.R
import com.example.foodato.databinding.FragmentSignUpBinding


class SignUp : Fragment() {
    // TODO: Rename and change types of parameters
    private lateinit var signUpBinding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val application = requireNotNull(this.activity).application

        val dataSource = FoodatoDatabaseBuild.getInstance(application).loginDataDao
        val viewModelFactory = signUpViewModelFactory(dataSource, application)

        viewModel = ViewModelProvider(this,viewModelFactory).get(SignUpViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signUpBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        signUpBinding.lifecycleOwner = this

        signUpBinding.createAccount.setOnClickListener {
            saveUserData()

        }
        // Inflate the layout for this fragment
        return signUpBinding.root
    }

    fun saveUserData() {
        var name = signUpBinding.name.text.toString()
        var username = signUpBinding.username.text.toString()
        var email = signUpBinding.EmailAddress.text.toString()
        var password = signUpBinding.editTextTextPassword.text.toString()
        var newUserData = LoginData()
        if (name.isEmpty()) {
            Toast.makeText(context, "Name can't be empty", Toast.LENGTH_SHORT).show()
        }
        if (username.isEmpty()) {
            Toast.makeText(context, "Username can't be empty", Toast.LENGTH_SHORT).show()
        }
        if (email.isEmpty()) {
            Toast.makeText(context, "Email can't be empty", Toast.LENGTH_SHORT).show()
        }
        if (password.isEmpty()) {
            Toast.makeText(context, "Password can't be empty", Toast.LENGTH_SHORT).show()
        }
        if (name.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty()) {
            viewModel.insertData(name, username, email, password, newUserData)
            Toast.makeText(context, "User data is saved", Toast.LENGTH_SHORT).show()

            findNavController().navigate(SignUpDirections.actionSignUp2ToSignIn2())
        }

    }


}
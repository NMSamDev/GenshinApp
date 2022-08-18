package com.example.carlosmendez_finalproject.view

import android.content.Context
import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators.BIOMETRIC_STRONG
import android.hardware.biometrics.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.R
import com.example.carlosmendez_finalproject.databinding.LoginBiometricFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.concurrent.Executor

class LoginBiometricFragment: ViewModelFragment() {
    private lateinit var binding: LoginBiometricFragmentBinding
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo
    private val mAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginBiometricFragmentBinding.inflate(layoutInflater)
        val sharedPreferences = requireActivity().getSharedPreferences("UserLogin",
            Context.MODE_PRIVATE
        )
        Log.d("TAG", "onCreateView: ${sharedPreferences.getString(KEY_MAIL, "")}")
        val biometricManager = BiometricManager.from(requireContext())
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS ->
                Toast.makeText(requireContext(), "App can authenticate using biometrics.", Toast.LENGTH_SHORT).show()
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE ->
                Toast.makeText(requireContext(), "No biometric features available on this device.", Toast.LENGTH_SHORT).show()
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->
                Toast.makeText(requireContext(), "Biometric features are currently unavailable.", Toast.LENGTH_SHORT).show()
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                // Prompts the user to create credentials that your app accepts.
                val enrollIntent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                        BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                }
                val REQUEST_CODE = 101010
                startActivityForResult(enrollIntent, REQUEST_CODE)
            }
        }

        executor = ContextCompat.getMainExecutor(requireContext())
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(requireContext(),
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(requireContext(),
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                    logIn()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(requireContext(), "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .build()

        binding.apply {

            tvEmail.text = sharedPreferences.getString(KEY_MAIL, "")
            btnGotoLogin.setOnClickListener(){
                findNavController().navigate(LoginBiometricFragmentDirections.actionLoginBiometricFragmentToLoginFragment())
            }
            ivBiometric.setOnClickListener {
                biometricPrompt.authenticate(promptInfo)
            }
        }


        return binding.root
    }

    fun logIn(){
        val sharedPreferences = requireActivity().getSharedPreferences("UserLogin",
            Context.MODE_PRIVATE
        )
        mAuth.signInWithEmailAndPassword(
            sharedPreferences.getString(KEY_MAIL,"").toString(),
            sharedPreferences.getString(KEY_PASSWORD,"").toString())
            .addOnSuccessListener {
                val intent = Intent(requireActivity(), IndexActivity()::class.java)
                startActivity(intent)
                requireActivity().finish()
            }

    }
}
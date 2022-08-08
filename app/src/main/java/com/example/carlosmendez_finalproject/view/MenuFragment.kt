package com.example.carlosmendez_finalproject.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.carlosmendez_finalproject.databinding.MenuFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class MenuFragment: ViewModelFragment() {
    lateinit var binding: MenuFragmentBinding
    private val mAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MenuFragmentBinding.inflate(layoutInflater)
        verifyCurrentUser()

        val context = this.context
        binding.apply {

            tvUser.text = mAuth.currentUser!!.email
//            btnCharacters.layoutParams.width = dpToPx(250f, this@MenuFragment.context!!)
//
//            btnCharacters
//                .setOnTouchListener { view, motionEvent ->
//                    if(motionEvent.action == MotionEvent.ACTION_DOWN) {
//                        view.layoutParams.width = view.layoutParams.width*2
//                        Toast.makeText(context, "Pressed", Toast.LENGTH_SHORT).show()
//                    }
//
//                        return@setOnTouchListener true
//                }



            btnLogout.setOnClickListener(){
                signOut()
            }
            btnCharacters.setOnClickListener(){
                viewModel.setLoading()
                findNavController().navigate(
                    MenuFragmentDirections.actionMenuFragmentToCharacterFragment()
                )
            }
        }

        return binding.root
    }

    private fun dpToPx(dp: Float, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }

    private fun verifyCurrentUser() {
        if(mAuth.currentUser == null) {
            signOut()
        }
    }

    private fun signOut() {
        mAuth.signOut()
        val intent = Intent(requireActivity(), MainActivity()::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}
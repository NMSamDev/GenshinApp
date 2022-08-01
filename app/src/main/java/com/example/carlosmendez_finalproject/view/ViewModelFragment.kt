package com.example.carlosmendez_finalproject.view

import androidx.fragment.app.Fragment
import com.example.carlosmendez_finalproject.di.DI

open class ViewModelFragment: Fragment() {
    protected val viewModel by lazy {
        DI.provideViewModel(requireActivity())
    }
}
package com.example.carlosmendez_finalproject.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.carlosmendez_finalproject.di.AppModule
import com.example.carlosmendez_finalproject.viewmodel.GenshinViewModel

open class ViewModelFragment: Fragment() {
    protected val viewModel by lazy {
        AppModule.provideViewModel(requireActivity())
    }
}
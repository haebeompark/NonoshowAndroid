package com.example.nonoshow.ui.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.nonoshow.R

class SignUpFragment : Fragment() {

    private lateinit var toolsViewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        toolsViewModel =
            ViewModelProviders.of(this).get(SignUpViewModel::class.java)

        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }
}
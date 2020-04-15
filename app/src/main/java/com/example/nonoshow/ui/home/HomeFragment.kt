package com.example.nonoshow.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.nonoshow.R
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.random.Random

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var edittext: EditText
    private lateinit var circleImageButton : ImageButton

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val edittext : EditText = getView()!!.findViewById(R.id.Text_search)
        val circleImageButton : Button = getView()!!.findViewById(R.id.circleImageButton)

        circleImageButton.textSize = 25f
        circleImageButton.text = "X%"
        edittext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var phoneNum = s.toString()
                /*textView.text = phoneNum*/

                if(phoneNum.length > 10){
                    circleImageButton.text = search(phoneNum)
                }
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    fun search(num : String) : String{
        // 여기에 검색함수
        val randomValue = Random.nextInt(0,100)
        return setTextInCircle(randomValue)
    }

    private fun setTextInCircle(x : Int): String{
        return x.toString() + "%"
    }
}
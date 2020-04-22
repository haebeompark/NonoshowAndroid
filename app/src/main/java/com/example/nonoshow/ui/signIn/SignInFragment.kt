package com.example.nonoshow.ui.signIn

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.nonoshow.MainActivity
import com.example.nonoshow.MyApplication
import com.example.nonoshow.R

class SignInFragment : Fragment() {

    private lateinit var signInViewModel: SignInViewModel
    private val LOGIN : Int = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        MyApplication.isLogined = false
        val logoButton : ImageView = getView()!!.findViewById(R.id.logoImage)
        val signIn : Button = getView()!!.findViewById(R.id.button_signIn)
        val signUp : Button = getView()!!.findViewById(R.id.button_signUp)
        val checkImage : ImageView = getView()!!.findViewById(R.id.checkImage)
        val textID : EditText = getView()!!.findViewById(R.id.editText_ID)
        val textPW : EditText = getView()!!.findViewById(R.id.editText_PW)
        logoButton.setOnClickListener{
            val uri = Uri.parse("http://github.com/haebeompark/NonoshowAndroid")
            val intent : Intent = Intent(Intent.ACTION_VIEW, uri)
            intent.addCategory(Intent.CATEGORY_BROWSABLE)
            startActivity(intent)
        }
        var isChecked = false
        checkImage.setOnClickListener{ /*로그인 상태 유지가 클릭됐을 때*/
            when(isChecked){
                true-> {
                    checkImage.setImageResource(R.drawable.check_gray)
                    isChecked = false
                }
                false->{
                    checkImage.setImageResource(R.drawable.check_colored)
                    isChecked = true
                }
            }
        }
        signIn.setOnClickListener{
            MyApplication.isLogined = true
            MyApplication.ID = textID.text.toString()
Log.i("ID",MyApplication.ID)
            MyApplication.PW = textPW.text.toString()
Log.i("PW",MyApplication.PW)

            MainActivity.changeState(MyApplication.ID, LOGIN)/*로그인하며 닉네임설정*/

            it.findNavController().navigate(R.id.nav_search_by_phoneNum)    /*fragment 전환*/
        }
        signUp.setOnClickListener{
            MyApplication.isLogined = false
            it.findNavController().navigate(R.id.nav_signUp)
        }


    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        signInViewModel =
            ViewModelProviders.of(this).get(SignInViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_sign_in, container, false)

        return root
    }
}
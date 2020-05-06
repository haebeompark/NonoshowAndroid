/*예약하기를 눌렀을 때 나오는 레이아웃을 컨트롤하는 클래스*/
package com.example.nonoshow

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.nonoshow.MyApplication.Companion.contextForList
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.nonoshow.MyApplication.Companion.createView
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.booking_manager.*



class bookingManager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.booking_manager)
        contextForList = this

        initialSetting(bookingDetailLinearLayout)
        /**뒤로가기 버튼을 위한 툴바**/
        val mToolbar = findViewById<View>(R.id.toolbarBooking) as Toolbar
        setSupportActionBar(mToolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        /**                         **/
    }
    override fun onOptionsItemSelected(item : MenuItem) : Boolean  {
        /*툴바에 뒤로가기버튼기능을 핸드폰의 뒤로가기버튼의 동작과 같도록 함*/
        when (item.itemId){
            android.R.id.home->{
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun initialSetting (LL : LinearLayout){
        LL.addView(createView<View>(
            type = MyApplication.LINE,
            directionHorizontal = true,
            backGroundColor = R.color.colorLightGray)) /*가로선*/
        LL.addView(createView<TextView>(
            type = MyApplication.TEXT_VIEW,
            text = "예약날짜 선택",
            backGroundColor = R.color.colorPrimary,
            textSize = 16f,
            width =  ViewGroup.LayoutParams.WRAP_CONTENT,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
            marginLeft = 64,
            marginTop = 32,
            textColor = R.color.colorWhite
        ))
        LL.addView(createView<MaterialCalendarView>(
            type = MyApplication.CALENDAR
        ))
        LL.addView(createView<View>(
            type = MyApplication.LINE,
            directionHorizontal = true,
            backGroundColor = R.color.colorLightGray)) /*가로선*/
        LL.addView(createView<TextView>(
            type = MyApplication.TEXT_VIEW,
            text = "예약시각 선택",
            backGroundColor = R.color.colorPrimary,
            textSize = 16f,
            width =  ViewGroup.LayoutParams.WRAP_CONTENT,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
            marginLeft = 64,
            marginTop = 32,
            textColor = R.color.colorWhite
        ))

        /*******************예약시각 선택********************/
        val textGroup0 :LinearLayout? = createView(
            type = MyApplication.LINEAR_LAYOUT,
            directionHorizontal = true  /*가로*/,
            marginHorizontal = 80
        )
        textGroup0!!.addView(createView<Spinner>(
            type = MyApplication.SPINNER,
            width = 0,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
            weight = .4f,
            startNum = 1,
            endNum = 24
        ))
        textGroup0.addView(createView<TextView>(
            type = MyApplication.TEXT_VIEW,
            text = "시",
            textSize = 24f,
            textColor = R.color.colorPrimary,
            width = 0,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
            weight = .1f,
            marginLeft = 40,
            marginTop = 8
        ))
        textGroup0.addView(createView<Spinner>(
            type = MyApplication.SPINNER,
            width = 0,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
            weight = .4f,
            startNum = 0,
            endNum = 59
        ))
        textGroup0.addView(createView<TextView>(
            type = MyApplication.TEXT_VIEW,
            text = "분",
            textSize = 24f,
            textColor = R.color.colorPrimary,
            width = 0,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
            weight = .1f,
            marginLeft = 40,
            marginTop = 8
        ))
        LL.addView(textGroup0)
        /***************************************************/

        LL.addView(createView<View>(
            type = MyApplication.LINE,
            directionHorizontal = true,
            backGroundColor = R.color.colorLightGray)) /*가로선*/
        LL.addView(createView<TextView>(
            type = MyApplication.TEXT_VIEW,
            text = "예약인원 선택",
            backGroundColor = R.color.colorPrimary,
            textSize = 16f,
            width =  ViewGroup.LayoutParams.WRAP_CONTENT,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
            marginLeft = 64,
            marginTop = 32,
            textColor = R.color.colorWhite
        ))

        /********************예약인원 선택********************/
        val textGroup1 :LinearLayout? = createView(
            type = MyApplication.LINEAR_LAYOUT,
            directionHorizontal = true  /*가로*/,
            marginHorizontal = 80
        )
        textGroup1!!.addView(createView<TextView>(
            type = MyApplication.TEXT_VIEW,
            text = "예약인원 : ",
            textSize = 24f,
            textColor = R.color.colorPrimary,
            width = 0,
            weight = .5f,
            marginLeft = 40,
            marginTop = 8
        ))
        textGroup1.addView(createView<Spinner>(
            type = MyApplication.SPINNER,
            startNum = 1,
            endNum = 30,
            width =  0,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
            weight = .5f
        ))
        /*******************************************************/
        LL.addView(textGroup1)
        LL.addView(createView<View>(
            type = MyApplication.LINE,
            directionHorizontal = true,
            backGroundColor = R.color.colorLightGray)) /*가로선*/
        LL.addView(createView<TextView>(
            type = MyApplication.TEXT_VIEW,
            text = when(MyApplication.isLogined){
                true->"예약하기"
                false->"비회원 예약하기"
            },
            textSize = 20f,
            marginHorizontal = 256,
            marginVertical = 64,
            background = R.drawable.edit_text_customize_primary,
            textAlignCenter = true,
            textColor = R.color.colorPrimary,
            height = 300
        ))
    }
}
package com.example.nonoshow

import android.app.Application
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*

class MyApplication : Application() { /*하나의 인스턴스를 가지는 클래스*/
    companion object {
        val LINEAR_LAYOUT = 1004
        val TEXT_VIEW = 1015
        val IMAGE_BUTTON = 1026
        val LINE = 1037
        val CALENDAR = 1048
        val SPINNER = 1059
        fun logout() {
            ID = "default"
            PW = "default"
            isLogined = false
            var loginToken = ""
        }

        /*static at Kotlin*/
        var ID = "default" /*일시적 사용 : 보안 취약 * 제거될코드?*/
        var PW = "default"
        var isLogined = false
        var loginToken = "" /*서버에서 암호화해서 보내준 녀석을 저장<나중에 업데이트>*/

        fun <T> createView(
            type: Int,     /*0 = LL 1 = textView 2 = ImageButton */
            directionHorizontal: Boolean = false,    /*레이아웃 방향 true = 가로*/
            text: String = "",
            textSize: Float = 24f,
            width: Int = ViewGroup.LayoutParams.MATCH_PARENT,
            height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
            marginHorizontal: Int = 0, /*가로 마진*/
            marginVertical: Int = 0,
            marginLeft: Int = marginHorizontal,
            marginTop: Int = marginVertical,
            marginRight: Int = marginHorizontal,
            marginBottom: Int = marginVertical,
            backGroundColor: Int = R.color.colorWhite,
            weight: Float = 0f,
            imageId: Int = R.color.colorWhite,
            background: Int = R.color.colorWhite,
            textColor: Int = android.R.color.black
        ): T? {
            val context = MainActivity.contextForList!! /*context 문제*/
            var result: T = View(context) as T
            when (type) {
                LINEAR_LAYOUT -> {   /*LL*/
                    result = LinearLayout(context).apply {
                        when (directionHorizontal) {
                            true -> orientation = LinearLayout.HORIZONTAL
                            false -> orientation = LinearLayout.VERTICAL
                        }
                        layoutParams = LinearLayout.LayoutParams(
                            width,
                            height
                        ).apply {
                            if (weight != 0f)
                                this.weight = weight
                        }
                        val param = layoutParams as ViewGroup.MarginLayoutParams    /*마진설정*/
                        param.setMargins(marginLeft, marginTop, marginRight, marginBottom)
                        setBackgroundColor(     /*배경 색 설정*/
                            ContextCompat.getColor(
                                context,
                                backGroundColor
                            )
                        )  /*backgroundColor 설정*/
                        weightSum = 1f
                    } as T
                }
                TEXT_VIEW -> {  /*textView*/
                    result = TextView(context).apply {
                        this.text = text /*  이곳에 매장의 이름이 들어와야 함  */
                        this.textSize = textSize
                        layoutParams = LinearLayout.LayoutParams(
                            width,
                            height
                        ).apply {
                            if (weight != 0f)
                                this.weight = weight
                        }
                        setTextColor(
                            ContextCompat.getColor(
                                context,
                                textColor
                            )
                        )
                        val param = layoutParams as ViewGroup.MarginLayoutParams    /*마진설정*/
                        param.setMargins(marginLeft, marginTop, marginRight, marginBottom)
                    } as T
                }
                IMAGE_BUTTON -> { /*ImageButton*/
                    result = ImageButton(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            width,
                            height
                        ).apply {
                            if (weight != 0f)
                                this.weight = weight
                        }
                        /*gravity = layout_centerVertical 위치 정렬 - 이상하게 안됨 일단 미구현*/
                        this.setImageResource(imageId)    /*사진도 나중에 구현*/
                        this.background = ContextCompat.getDrawable(context,background)
                        adjustViewBounds = true
                    } as T
                }
                LINE -> {   /*line*/
                    result = View(context).apply {
                        when (directionHorizontal) {    /*true 면 가로선 (height 가 6)*/
                            true -> layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,6)
                            false -> layoutParams = LinearLayout.LayoutParams(6,ViewGroup.LayoutParams.MATCH_PARENT)
                        }
                        setBackgroundColor(     /*배경 색 설정*/
                            ContextCompat.getColor(
                                context,
                                backGroundColor
                            )
                        )
                    } as T
                }
                CALENDAR -> {
                    result = MaterialCalendarView(context).apply{
                        state().edit()
                            .setFirstDayOfWeek(Calendar.SUNDAY)
                            .setMinimumDate(CalendarDay.from(2017, 0, 1))
                            .setMaximumDate(CalendarDay.from(2030, 11, 31))
                            .setCalendarDisplayMode(CalendarMode.MONTHS)
                            .commit()
                    } as T
                }
                SPINNER -> {
                    val data : Array<String> = context.getResources().getStringArray(R.array.time_array)
                    val adapter : ArrayAdapter<String>  = ArrayAdapter(context, android.R.layout.simple_dropdown_item_1line,data)

                    result = Spinner(context).apply{
                        layoutParams = LinearLayout.LayoutParams(
                            width,
                            height
                        ).apply {
                            if (weight != 0f)
                                this.weight = weight
                        }
                        ListView(context).apply{
                            layoutParams = LinearLayout.LayoutParams(
                                width,
                                height
                            )
                        }
                        setAdapter(adapter)
                    } as T
                }
            }
            return result
        }
    }
}
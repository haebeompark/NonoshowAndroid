package com.example.nonoshow.ui.bookingMain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.nonoshow.MainActivity
import com.example.nonoshow.MyApplication.Companion.CALENDAR
import com.example.nonoshow.MyApplication.Companion.IMAGE_BUTTON
import com.example.nonoshow.MyApplication.Companion.LINE
import com.example.nonoshow.MyApplication.Companion.LINEAR_LAYOUT
import com.example.nonoshow.MyApplication.Companion.SPINNER
import com.example.nonoshow.MyApplication.Companion.TEXT_VIEW
import com.example.nonoshow.MyApplication.Companion.createView
import com.example.nonoshow.R
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.fragment_booking_main.*

class BookingMainFragment : Fragment() {
   // val materialCalendarView : MaterialCalendarView? = createView()
    private var selected : LinearLayout? = null
    private var selectedInfo : LinearLayout? = null

    private lateinit var bookingMainViewModel: BookingMainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createBlocks(LinearLayoutBookingMain)
        val loadMore = TextView(MainActivity.contextForList)
        loadMore.text = resources.getString(R.string.text_load_more)  /*load more*/
        loadMore.setOnClickListener {
            /*뷰 블럭을 더 생성*/
            createBlocks(LinearLayoutBookingMain)
        }
        LinearLayoutBookingManager.addView(loadMore)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bookingMainViewModel =
            ViewModelProviders.of(this).get(BookingMainViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_booking_main, container, false)

        return root
    }

    private fun createBlocks(LL: LinearLayout = LinearLayoutBookingMain, numberOfBlock: Int = 10) {
        /*create Fragment or loadMore 할 경우 실행됨*/
        for (i in 1..numberOfBlock) {
            createABlock(LL)
        }
    }

    private fun createABlock(LL: LinearLayout = LinearLayoutBookingMain): LinearLayout {    /*블록을 하나 생성하고 그 뷰를 리턴*/
        val tableRow : LinearLayout? = createView(
            type = LINEAR_LAYOUT,
            directionHorizontal = true, /*가로*/
            width = ViewGroup.LayoutParams.MATCH_PARENT,
            height = 500,
            backGroundColor = R.color.colorWhite
        )
        val textView : TextView? = createView(
            type = TEXT_VIEW,
            text = "흥부네오리",
            textSize = 24f,
            width = ViewGroup.LayoutParams.MATCH_PARENT,
            height = ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val textViewSub : TextView? = createView(
            type = TEXT_VIEW,
            text = "오리요리전문점\n경기도 시흥시\n캐주얼, 어린이 환영, 단체석", /* 간단한 설명  */
            textColor = R.color.colorGray140,
            textSize = 16f,
            width =  ViewGroup.LayoutParams.MATCH_PARENT,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
            marginLeft = 64,
            marginTop = 32
        )
        val imageButton : ImageButton? = createView(
            type = IMAGE_BUTTON,
            width = 0,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
            weight = .33f,
            imageId = R.drawable.logo_transparent
        )
        val textGroup : LinearLayout? = createView(
            type = LINEAR_LAYOUT,
            width = 0,
            height = ViewGroup.LayoutParams.MATCH_PARENT,
            weight = .67f,
            directionHorizontal = false, /*세로*/
            marginHorizontal = 64,
            marginTop = 32,
            marginBottom = 64
        )
        val block : LinearLayout? = createView(
            type = LINEAR_LAYOUT,
            width = ViewGroup.LayoutParams.MATCH_PARENT,
            height = ViewGroup.LayoutParams.WRAP_CONTENT,
            marginHorizontal = 24,
            marginVertical = 10,
            directionHorizontal = false /*세로*/
        )

        /*뷰와 레이아웃의 연결*/
        textGroup!!.addView(textView)
        textGroup.addView(textViewSub)
        tableRow!!.addView(textGroup)
        tableRow.addView(imageButton)
        block!!.addView(tableRow)
        LL.addView(block)

        settingTableRowClickListener(block, tableRow) /*클릭시 정보생성*/

        return block
    }

    private fun settingTableRowClickListener(block : LinearLayout, tableRow : LinearLayout){
        tableRow.setOnClickListener{/*선택됨 - 선택된 녀석의 정보 보여주기! + 선택상태를 저장*/
            if(selectedInfo != null) {
                selected!!.removeView(selectedInfo) /* 다른녀석이 선택되면 이전 선택된 info 제거*/
            }
            val info : LinearLayout? = createView(
                type = LINEAR_LAYOUT
            )
            info!!.addView(createView<View>(
                type = LINE,
                directionHorizontal = true,
                backGroundColor = R.color.colorGray207)) /*가로선*/
            info.addView(createView<ImageButton>(  /*사진 추가*/
                type = IMAGE_BUTTON,
                imageId = R.drawable.logo_transparent
            ))
            info.addView(createView<View>(
                type = LINE,
                directionHorizontal = true,
                backGroundColor = R.color.colorGray207)) /*가로선*/
            info.addView(createView<MaterialCalendarView>(
                type = CALENDAR
            ))
            info.addView(createView<View>(
                type = LINE,
                directionHorizontal = true,
                backGroundColor = R.color.colorPrimaryDark)) /*가로선*/
            info.addView(createView<Spinner>(
                type = SPINNER
            ))
            block.addView(info) /*블록에 정보를 붙임*/
            tableRow.setOnClickListener{/*위쪽 테이블을 눌렀을경우 INFO 창 제거를 위해*/
                settingTableRowClickListener(block, tableRow)/*INFO 가 제거된 테이블의 setOnClickListener 초기화를 위해*/
                block.removeView(info)
                selected = null
                selectedInfo = null
            }
            /*
            tableRow.addView() 예약날짜 선택하는 놈
            tableRow.addView() 인원 선택
            */
            selected = block /* 선택 */
            selectedInfo = info
        }
    }
}
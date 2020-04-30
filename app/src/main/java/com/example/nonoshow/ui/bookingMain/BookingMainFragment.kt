package com.example.nonoshow.ui.bookingMain

import android.content.Context
import android.os.Bundle
import android.sax.EndElementListener
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.LinearLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.nonoshow.MainActivity
import com.example.nonoshow.R
import kotlinx.android.synthetic.main.fragment_booking_main.*

class BookingMainFragment : Fragment() {

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

    /*fragment Manager로 fragment안의 view들을 동적으로 생성해 보자*/
    /******************************************************************************************
    scrollView > linearLayout > tableRow > [하나의 정보(block)]
    linearLayout을 이름으로 가져와서 tableRow를 임의의 수 생성하도록 해야함 -> 생성하는 함수가 필요
     *******************************************************************************************/
    private fun createBlocks(LL: LinearLayout = LinearLayoutBookingMain, numberOfBlock: Int = 10) {
        /*create Fragment or loadMore 할 경우 실행됨*/
        for (i in 1..numberOfBlock) {
            createABlock(LL)
        }
    }

    private fun createABlock(LL: LinearLayout = LinearLayoutBookingMain): LinearLayout {    /*블록을 하나 생성하고 그 뷰를 리턴*/
        val context = MainActivity.contextForList!!
        val tableRow = createView<LinearLayout>(
            type = 0,
            context = context,
            directionHorizontal = true,
            width = ViewGroup.LayoutParams.MATCH_PARENT,
            height = 500,
            marginHorizontal = 24,
            marginVertical = 10,
            backGroundColor = R.color.colorWhite
        )

        val textView = createView<TextView>(
            type = 1,
            context = context,
            text = "흥부네오리",
            textSize = 24f,
            width = ViewGroup.LayoutParams.MATCH_PARENT,
            height = ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val textViewSub = createView<TextView>(
            type = 1,
            context = context,
            text = "오리요리전문점\n경기도 시흥시\n캐주얼, 어린이 환영, 단체석" /* 간단한 설명  */,
            textColor = R.color.colorGray140,
            textSize = 16f,
            width =  ViewGroup.LayoutParams.MATCH_PARENT,
            height = ViewGroup.LayoutParams.WRAP_CONTENT ,
            marginLeft = 64,
            marginTop = 32
        )
        val imageButton = createView<ImageButton>(
            type = 2,
            context = context,
            width = 0,
            height = 450,
            weight = .33f,
            background = R.drawable.logo_transparent
        )
        val textGroup = createView<LinearLayout>(
            type = 0,
            context = context,
            width = 0,
            height = ViewGroup.LayoutParams.MATCH_PARENT,
            weight = .67f,
            marginHorizontal = 64,
            marginTop = 32,
            marginBottom = 64
        )

        /*뷰와 레이아웃의 연결*/
        textGroup!!.addView(textView)
        textGroup!!.addView(textViewSub)
        tableRow!!.addView(textGroup)
        tableRow!!.addView(imageButton)
        LL.addView(tableRow)
        return tableRow
    }

    /*
     * 아래는 생성될 하나의 블록 위는 그 코드
    *******************************************************************************************
    <TableRow
    android:layout_width="match_parent"
    android:layout_height="120dp"
    android:layout_marginHorizontal="14dp"
    android:layout_marginVertical="10dp"
    android:background="@color/colorWhite">

    <TextView
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:layout_gravity="center_vertical"
    android:text=""
    android:textSize="30sp" />
    <!-- 아래 뷰는 이미지 버튼을 오른쪽으로 보내기위한 장치 -->
    <View
    android:layout_width="0dp"
    android:layout_height="0dp"
    android:layout_weight="1" />

    <ImageButton
    android:layout_width="100dp"
    android:layout_height="100dp"
    android:layout_gravity="center_vertical"
    app:srcCompat="@color/colorPrimaryDark" />

    </TableRow>
     ********************************************************************************************/
    fun <T> createView(type : Int,     /*0 = LL 1 = textView 2 = ImageButton */
                       context : Context,
                       directionHorizontal : Boolean = true,    /*레이아웃 방향 true = 세로*/
                       text : String = "",
                       textSize : Float = 24f,
                       width : Int = ViewGroup.LayoutParams.MATCH_PARENT,
                       height : Int = ViewGroup.LayoutParams.WRAP_CONTENT,
                       marginHorizontal : Int = 0, /*가로 마진*/
                       marginVertical : Int = 0,
                       marginLeft : Int = marginHorizontal,
                       marginTop : Int = marginVertical,
                       marginRight : Int = marginHorizontal,
                       marginBottom : Int = marginVertical,
                       backGroundColor : Int = R.color.colorWhite,
                       weight : Float = 0f,
                       background : Int = R.color.colorWhite,
                       textColor : Int = android.R.color.black): T? {
        var result : T = View(context) as T
        when (type) {
            0 ->{   /*LL*/
                result = LinearLayout(context).apply {
                    when( directionHorizontal ) {
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
                    param.setMargins(marginLeft,marginTop ,marginRight,marginBottom)
                    setBackgroundColor(     /*배경 색 설정*/
                        ContextCompat.getColor(
                            context,
                            backGroundColor
                        )
                    )  /*backgroundColor 설정*/
                } as T
            }
            1 -> {  /*textView*/
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
                    param.setMargins(marginLeft,marginTop ,marginRight,marginBottom)
                } as T
            }
            2 -> { /*ImageButton*/
                result = ImageButton(context).apply {
                    layoutParams = LinearLayout.LayoutParams(
                        width,
                        height
                    ).apply {
                        if (weight != 0f)
                            this.weight = weight
                    }
                    /*gravity = layout_centerVertical 위치 정렬 - 이상하게 안됨 일단 미구현*/
                    this.background = ContextCompat.getDrawable(
                        context,
                        background
                    )    /*사진도 나중에 구현*/
                } as T
            }
        }
        return result
    }
}
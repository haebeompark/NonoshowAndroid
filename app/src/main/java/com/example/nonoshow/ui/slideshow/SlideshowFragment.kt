package com.example.nonoshow.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TableRow
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.FrameLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.nonoshow.MainActivity
import com.example.nonoshow.R
import kotlinx.android.synthetic.main.fragment_slideshow.*

class SlideshowFragment : Fragment() {

    private lateinit var slideshowViewModel: SlideshowViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createABlock(LinearLayoutBookingMain)
        val textView11 = TextView(MainActivity.contextForList)
        textView11.text = "rmfwk"
        LinearLayoutBookingMain.addView(textView11)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        slideshowViewModel =
            ViewModelProviders.of(this).get(SlideshowViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)

        return root
    }

    /*fragment Manager로 fragment안의 view들을 동적으로 생성해 보자*/
    /******************************************************************************************
    scrollView > linearLayout > tableRow > [하나의 정보(block)]
    linearLayout을 이름으로 가져와서 tableRow를 임의의 수 생성하도록 해야함 -> 생성하는 함수가 필요
     *******************************************************************************************/
    public fun createABlock(LL : LinearLayout = LinearLayoutBookingMain): FrameLayout {
        val context = MainActivity.contextForList!!
        val tableRow : FrameLayout
        tableRow = FrameLayout(context).apply {
            layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                500
            )
            var param = layoutParams as ViewGroup.MarginLayoutParams    /*패딩설정*/
            param.setMargins(24, 10, 24, 10)
            layoutParams = param                                        /*패딩설정 끝*/
            setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    R.color.colorWhite
                )
            )  /*backgroundColor 설정*/
        }
        val textView = TextView(context).apply {
            gravity = android.R.attr.layout_centerVertical /*위치 정렬*/
            text = "매장 이름" /*  이곳에 매장의 이름이 들어와야 함  */
            textSize = 30f
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }
        val view = View(context).apply {
            layoutParams = LinearLayoutCompat.LayoutParams(
                0,
                0,
                1f
            )
        }
        val imageButton = ImageButton(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                450,
                450
            )
            /*gravity = layout_centerVertical 위치 정렬 - 이상하게 안됨 일단 미구현*/
            background = ContextCompat.getDrawable(
                context,
                R.drawable.logo_transparent
            )    /*사진도 나중에 구현*/
        }
        tableRow.addView(textView)
        tableRow.addView(view)
        tableRow.addView(imageButton)
        LL.addView(tableRow)
        return tableRow
    }
        /*
     * 아래는 생성될 하나의 블록
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
}
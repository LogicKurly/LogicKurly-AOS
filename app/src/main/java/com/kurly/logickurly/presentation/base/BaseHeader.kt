package com.kurly.logickurly.presentation.base

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.kurly.logickurly.R


class BaseHeader : ConstraintLayout {

    // 뒤로가기 이미지뷰
    var ivBack: ImageView ?=null
    // 타이틀 텍스트뷰
    var tvTitle : TextView ?=null
    // 추가하기 이미지뷰
    var ivAdd: ImageView ?=null

    var mContext : Context ?= null


    // 기본생성자
    constructor(context: Context) : super(context) {
        mContext = context
        initControl()
    }

    // 속성을 읽는 생성자
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mContext = context
        initControl()
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs) {
        mContext = context
        initControl()
        getAttrs(attrs, defStyle)
    }

    private fun getAttrs(attrs: AttributeSet?) {
        // 속성 설정
        val typedArray = mContext!!.obtainStyledAttributes(attrs, R.styleable.BaseHeader, 0, 0)
        initAttr(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyle: Int) {
        // 속성 설정
        val typedArray = mContext!!.obtainStyledAttributes(attrs, R.styleable.BaseHeader, defStyle, 0)
        initAttr(typedArray)
    }

    private fun initAttr(typedArray: TypedArray) {


    }

    /**
     * 레이아웃 초기화
     */
    private fun initControl() {
        val li = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v: View = li.inflate(R.layout.layout_base_header, this, false)

        ivBack = v.findViewById(R.id.ivBack)
        tvTitle = v.findViewById(R.id.headerTitle)
        ivAdd = v.findViewById(R.id.ivAdd)

        addView(v)
    }
}
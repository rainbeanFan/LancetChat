package cn.framework.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.GradientDrawable.LINE
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_MOVE
import android.view.MotionEvent.ACTION_UP
import android.view.View
import cn.framework.R

class TouchPictureView: View {

    private var mBGBitmap: Bitmap? = null
    private var mPaintBG: Paint? = null
    private var mBlankBitmap: Bitmap? = null
    private var mPaintBlank: Paint? = null

    private var mMoveBitmap: Bitmap? = null
    private var mPaintMove: Paint? = null

    private var mWidth: Int = 0
    private var mHeight: Int = 0

    private var CARD_SIZE: Int = 200
    private var LINE_W = 0
    private var LINE_H = 0

    private var mMoveX = 200
    private var mErrorValue = 10

    private var mListener:OnViewResultListener? = null

    fun setListener(listener: OnViewResultListener){
        mListener = listener
    }

    constructor(context: Context):this(context, null)

    constructor(context: Context, attrs: AttributeSet?):this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?,defStyleAttrs:Int):super(context, attrs,defStyleAttrs){
        init()
    }

    private fun init() {
        mPaintBG = Paint()
        mPaintBlank = Paint()
        mPaintMove = Paint()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = w
        mHeight = h
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawMoveCard(canvas)
        drawBlankCard(canvas)
        drawBG(canvas)
    }

    private fun drawMoveCard(canvas: Canvas?) {
        mMoveBitmap = Bitmap.createBitmap(mBGBitmap!!,LINE_W,LINE_H,CARD_SIZE,CARD_SIZE)
        canvas?.drawBitmap(mMoveBitmap!!,mMoveX.toFloat(),LINE_H.toFloat(),mPaintMove)
    }

    private fun drawBlankCard(canvas: Canvas?){
        mBlankBitmap = BitmapFactory.decodeResource(resources, R.drawable.img_null_card)
        CARD_SIZE = mBlankBitmap!!.width

        LINE_W = mWidth / 3 * 2
        LINE_H = mHeight / 2 - (CARD_SIZE/2)

        canvas?.drawBitmap(mBlankBitmap!!,LINE_W.toFloat(),LINE_H.toFloat(),mPaintBlank)
    }

    private fun drawBG(canvas: Canvas?) {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_bg)

        mBGBitmap = Bitmap.createBitmap(mWidth,mHeight,Bitmap.Config.ARGB_8888)

        val bgCanvas = Canvas(mBGBitmap!!)

        bgCanvas.drawBitmap(bitmap,null, Rect(0,0,mWidth,mHeight),mPaintBG)
        canvas?.drawBitmap(mBGBitmap!!,null, Rect(0,0,mWidth,mHeight),mPaintBG)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action){
            ACTION_MOVE -> {
                if (event.x>0 && event.x<(mWidth - CARD_SIZE)){
                    mMoveX = event.x.toInt()
                    invalidate()
                }
            }
            ACTION_UP -> {
                if(mMoveX>(LINE_W - mErrorValue) && mMoveX < (LINE_W + mErrorValue)){
                    if(mListener!=null){
                       mListener!!.onResult()
                       mMoveX = 200
                       invalidate()
                    }
                }
            }
        }
        return true
    }

    interface OnViewResultListener {
        fun onResult()
    }

}
package com.ztzb.data.ui.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.ztzb.data.R
import com.ztzb.data.base.BaseActivity
import com.ztzb.data.util.StringAxisValueFormatter
import kotlinx.android.synthetic.main.activity_safe_char.*
import java.util.*

class SafeCharActivity : BaseActivity(),
    View.OnClickListener, OnChartValueSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safe_char)
        initView()
    }

    private fun initView() {
        safe_char_back_fl.setOnClickListener(this)
        // 设置数值选择监听
        mDoubleLineChar.setOnChartValueSelectedListener(this)
        // 没有描述的文本
        mDoubleLineChar.description.isEnabled = false
        // 支持触控手势
        mDoubleLineChar.setTouchEnabled(true)
        mDoubleLineChar.dragDecelerationFrictionCoef = 0.9f
        // 支持缩放和拖动
        mDoubleLineChar.isDragEnabled = true
        mDoubleLineChar.setScaleEnabled(true)
        mDoubleLineChar.setDrawGridBackground(false)
        mDoubleLineChar.isHighlightPerDragEnabled = true
        // 如果禁用,扩展可以在x轴和y轴分别完成
        mDoubleLineChar.setPinchZoom(true)
        // 设置背景颜色(灰色)
        mDoubleLineChar.setBackgroundColor(Color.BLACK)
        // 设置数据
        setData(20, 30f)
        // 默认x动画
        mDoubleLineChar.animateX(2500)
        // 获得数据
        val l = mDoubleLineChar.legend
        // 修改
        l.form = Legend.LegendForm.LINE
        l.textSize = 11f
        l.textColor = Color.WHITE
        l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
        l.orientation = Legend.LegendOrientation.HORIZONTAL
        l.setDrawInside(false)

        val xAxisFormatter = StringAxisValueFormatter(mDoubleLineChar)
        // x轴
        val xAxis = mDoubleLineChar.xAxis
        xAxis.textSize = 11f
        xAxis.textColor = Color.WHITE
        xAxis.setDrawGridLines(false)
        xAxis.setDrawAxisLine(false)
        xAxis.setLabelCount(20, true)
        xAxis.valueFormatter = xAxisFormatter

        // 左边y轴
        val leftAxis = mDoubleLineChar.axisLeft
        leftAxis.textColor = ColorTemplate.getHoloBlue()
        leftAxis.axisMaximum = 200f
        leftAxis.axisMinimum = 0f
        leftAxis.setDrawGridLines(true)
        leftAxis.isGranularityEnabled = true

        // 右边y轴
        val rightAxis = mDoubleLineChar.axisRight
        rightAxis.textColor = Color.RED
        rightAxis.axisMaximum = 900f
        rightAxis.axisMinimum = -200f
        rightAxis.setDrawGridLines(false)
        rightAxis.setDrawZeroLine(false)
        rightAxis.isGranularityEnabled = false
    }

    // 设置数据
    private fun setData(count: Int, range: Float) {
        val yVals1 = ArrayList<Entry>()
        for (i in 0 until count) {
            val mult = range / 2f
            val `val` = (Math.random() * mult).toFloat() + 50
            yVals1.add(Entry(i.toFloat(), `val`))
        }
        val yVals2 = ArrayList<Entry>()
        for (i in 0 until count - 1) {
            val `val` = (Math.random() * range).toFloat() + 450
            yVals2.add(Entry(i.toFloat(), `val`))
        }

        val set1: LineDataSet
        val set2: LineDataSet
        if (mDoubleLineChar.data != null &&
            mDoubleLineChar.data.dataSetCount > 0
        ) {
            set1 = mDoubleLineChar.data.getDataSetByIndex(0) as LineDataSet
            set2 = mDoubleLineChar.data.getDataSetByIndex(1) as LineDataSet
            set1.values = yVals1
            set2.values = yVals2
            mDoubleLineChar.data.notifyDataChanged()
            mDoubleLineChar.notifyDataSetChanged()
        } else {
            // 创建一个数据集,并给它一个类型
            set1 = LineDataSet(yVals1, "测量数据")
            set1.axisDependency = YAxis.AxisDependency.LEFT
            set1.isDrawValuesEnabled
            set1.color = ColorTemplate.getHoloBlue()
            set1.setCircleColor(Color.WHITE)
            set1.lineWidth = 2f
            set1.circleRadius = 3f
            set1.fillAlpha = 65
            set1.fillColor = ColorTemplate.getHoloBlue()
            set1.highLightColor = Color.rgb(244, 117, 117)
            set1.setDrawCircleHole(false)

            // 创建一个数据集,并给它一个类型
            set2 = LineDataSet(yVals2, "监测数据量")
            set1.isDrawValuesEnabled
            set2.axisDependency = YAxis.AxisDependency.RIGHT
            set2.color = Color.RED
            set2.setCircleColor(Color.WHITE)
            set2.lineWidth = 2f
            set2.circleRadius = 3f
            set2.fillAlpha = 65
            set2.fillColor = Color.RED
            set2.setDrawCircleHole(false)
            set2.highLightColor = Color.rgb(244, 117, 117)

            // 创建一个数据集的数据对象
            val data = LineData(set1, set2)
            data.setValueTextColor(Color.WHITE)
            data.setValueTextSize(9f)

            // 设置数据
            mDoubleLineChar.data = data
        }
    }

    override fun onValueSelected(e: Entry?, h: Highlight?) {}

    override fun onNothingSelected() {}

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.safe_char_back_fl -> this.finish()
        }
    }
}
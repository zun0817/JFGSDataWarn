package com.ztzb.data.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.*
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.ztzb.data.R
import com.ztzb.data.base.BaseMVVMActivity
import com.ztzb.data.base.BaseViewModel
import com.ztzb.data.model.data.VtDateValue
import com.ztzb.data.model.data.VtDateValueAvg
import com.ztzb.data.viewmodel.SafeMonitorViewModel
import kotlinx.android.synthetic.main.activity_safe_monitor.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SafeMonitorActivity : BaseMVVMActivity(), View.OnClickListener {

    companion object {
        private val TAG = SafeMonitorActivity::class.java.simpleName
        fun startActivity(activity: Activity) {
            val intent = Intent()
            intent.setClass(activity, SafeMonitorActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private lateinit var leftAxis: YAxis //左侧Y轴
    private lateinit var rightAxis: YAxis //右侧Y轴
    private lateinit var xAxis: XAxis //X轴
    private lateinit var legend: Legend //图例
    private lateinit var limitLine: LimitLine //限制线

    private val mViewModel: SafeMonitorViewModel by viewModel()

    override fun getChildViewModel(): BaseViewModel = mViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safe_monitor)
        initBarChart()
        initData()
    }

    private fun initData() {
        safe_back_fl.setOnClickListener(this)
        val vtDateValue1 = VtDateValue(2810, "安全监测01标")
        val vtDateValue2 = VtDateValue(2663, "安全监测02标")
        val vtDateValue3 = VtDateValue(1755, "安全监测03标")
        val vtDateValue4 = VtDateValue(940, "安全监测04标")
        val vtDateValue5 = VtDateValue(1, "土建A1标")
        val vtDateValue6 = VtDateValue(919, "土建A2标")
        val vtDateValue7 = VtDateValue(546, "土建A3标")
        val vtDateValue8 = VtDateValue(1182, "土建A4标")
        val vtDateValue9 = VtDateValue(894, "土建A5标")
        val vtDateValue10 = VtDateValue(1122, "土建A6标")
        val vtDateValue11 = VtDateValue(744, "土建A7标")
        val vtDateValue12 = VtDateValue(353, "土建B1标")
        val vtDateValue13 = VtDateValue(1189, "土建B2标")
        val vtDateValue14 = VtDateValue(887, "土建B3标")
        val vtDateValue15 = VtDateValue(872, "土建B4标")

        val vtDateValueAvg1 = VtDateValueAvg(27233f, "安全监测01标")
        val vtDateValueAvg2 = VtDateValueAvg(14617f, "安全监测02标")
        val vtDateValueAvg3 = VtDateValueAvg(13147f, "安全监测03标")
        val vtDateValueAvg4 = VtDateValueAvg(8867f, "安全监测04标")
        val vtDateValueAvg5 = VtDateValueAvg(0f, "土建A1标")
        val vtDateValueAvg6 = VtDateValueAvg(54215f, "土建A2标")
        val vtDateValueAvg7 = VtDateValueAvg(39542f, "土建A3标")
        val vtDateValueAvg8 = VtDateValueAvg(92549f, "土建A4标")
        val vtDateValueAvg9 = VtDateValueAvg(11028f, "土建A5标")
        val vtDateValueAvg10 = VtDateValueAvg(65850f, "土建A6标")
        val vtDateValueAvg11 = VtDateValueAvg(57116f, "土建A7标")
        val vtDateValueAvg12 = VtDateValueAvg(11012f, "土建B1标")
        val vtDateValueAvg13 = VtDateValueAvg(46953f, "土建B2标")
        val vtDateValueAvg14 = VtDateValueAvg(13859f, "土建B3标")
        val vtDateValueAvg15 = VtDateValueAvg(10248f, "土建B4标")

        val chartDataMap = LinkedHashMap<String, MutableList<Float>>()
        val xValues = mutableListOf<String>()
        val yValue1 = mutableListOf<Float>()
        val yValue2 = mutableListOf<Float>()
        val colors = listOf(
            resources.getColor(R.color.color_5cacee),
            resources.getColor(R.color.color_0B1746)
        )
        val valueList = mutableListOf<VtDateValue>()
        val avgValueList = mutableListOf<VtDateValueAvg>()

        valueList.add(vtDateValue1)
        valueList.add(vtDateValue2)
        valueList.add(vtDateValue3)
        valueList.add(vtDateValue4)
        valueList.add(vtDateValue5)
        valueList.add(vtDateValue6)
        valueList.add(vtDateValue7)
        valueList.add(vtDateValue8)
        valueList.add(vtDateValue9)
        valueList.add(vtDateValue10)
        valueList.add(vtDateValue11)
        valueList.add(vtDateValue12)
        valueList.add(vtDateValue13)
        valueList.add(vtDateValue14)
        valueList.add(vtDateValue15)

        avgValueList.add(vtDateValueAvg1)
        avgValueList.add(vtDateValueAvg2)
        avgValueList.add(vtDateValueAvg3)
        avgValueList.add(vtDateValueAvg4)
        avgValueList.add(vtDateValueAvg5)
        avgValueList.add(vtDateValueAvg6)
        avgValueList.add(vtDateValueAvg7)
        avgValueList.add(vtDateValueAvg8)
        avgValueList.add(vtDateValueAvg9)
        avgValueList.add(vtDateValueAvg10)
        avgValueList.add(vtDateValueAvg11)
        avgValueList.add(vtDateValueAvg12)
        avgValueList.add(vtDateValueAvg13)
        avgValueList.add(vtDateValueAvg14)
        avgValueList.add(vtDateValueAvg15)
        valueList.reverse()
        valueList.forEach {
            xValues.add(it.sYearMonth)
            yValue1.add(it.fValue.toFloat())
        }
        avgValueList.forEach {
            yValue2.add(it.fValue)
        }
        chartDataMap["测量数据"] = yValue1
        chartDataMap["监测数据量"] = yValue2
        showBarChart(xValues, chartDataMap, colors as MutableList<Int>)
    }

    private fun initBarChart() {
        barChart.setBackgroundColor(Color.WHITE)
        barChart.setDrawGridBackground(false)
        barChart.setDrawBarShadow(false)
        barChart.isHighlightFullBarEnabled = false
        barChart.isDoubleTapToZoomEnabled = false
        barChart.isDragEnabled = false
        barChart.isScaleXEnabled = false
        barChart.isScaleYEnabled = false
        barChart.setScaleEnabled(false)
        barChart.setDrawBorders(false)
        val description = Description()
        description.isEnabled = false
        barChart.description = description
        //设置动画效果
        barChart.animateY(1000, Easing.Linear)
        barChart.animateX(1000, Easing.Linear)
        xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        leftAxis = barChart.axisLeft
        rightAxis = barChart.axisRight
        //不绘制 Y轴线条
        rightAxis.setDrawAxisLine(false)
        //不显示X轴网格线
        xAxis.setDrawGridLines(false)
        leftAxis.setDrawGridLines(false)
        rightAxis.setDrawGridLines(false)
        legend = barChart.legend
        legend.form = Legend.LegendForm.SQUARE
        legend.textSize = 11f
        //显示位置
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        //是否绘制在图表里面
        legend.setDrawInside(false)
    }

    private fun initBarDataSet(barDataSet: BarDataSet, color: Int) {
        barDataSet.color = color
        barDataSet.formLineWidth = 1f
        barDataSet.formSize = 15f
    }

    private fun showBarChart(
        xValues: MutableList<String>,
        dataLists: LinkedHashMap<String, MutableList<Float>>,
        colors: MutableList<Int>
    ) {
        val dataSets = mutableListOf<IBarDataSet>()
        var currentPosition = 0
        dataLists.entries.forEach {
            val name = it.key
            val yValueList = it.value
            val entries = mutableListOf<BarEntry>()
            yValueList.forEachIndexed { index, float ->
                entries.add(BarEntry(index.toFloat(), float))
            }
            val batDataSet = BarDataSet(entries, name)
            initBarDataSet(batDataSet, colors[currentPosition])
            dataSets.add(batDataSet)
            currentPosition++
        }
//        xAxis.valueFormatter =
//            IAxisValueFormatter { value, _ ->
//                xValues[(abs(
//                    value
//                ) % xValues.size).toInt()]
//            } as ValueFormatter?
//
//        rightAxis.valueFormatter =
//            IAxisValueFormatter { _, _ ->
//                ""
//            } as ValueFormatter?
        val data = BarData(dataSets)
        val barAmount = dataLists.size //需要显示柱状图的类别 数量
        //设置组间距占比30% 每条柱状图宽度占比 70% /barAmount  柱状图间距占比 0%
        val groupSpace = 0.3f //柱状图组之间的间距
        val barWidth = (1f - groupSpace) / barAmount
        val barSpace = 0f
        //设置柱状图宽度
        data.barWidth = barWidth;
        //(起始点、柱状图组间距、柱状图之间间距)
        data.groupBars(0f, groupSpace, barSpace)
        barChart.data = data

        xAxis.axisMinimum = 0f
        xAxis.axisMaximum = xValues.size.toFloat()
        //将X轴的值显示在中央
        xAxis.setCenterAxisLabels(true)
    }



    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.safe_back_fl -> {
                this.finish()
            }
        }
    }

}

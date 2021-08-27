package com.ztzb.data.util;

import android.util.Log;

import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class StringAxisValueFormatter extends ValueFormatter {

    private BarLineChartBase<?> chart;

    public StringAxisValueFormatter(BarLineChartBase<?> chart) {
        this.chart = chart;
    }

    @Override
    public String getFormattedValue(float value) {

        String label = "01标";
        Log.e("******", value + "");
        switch ((int) value) {
            case 0:
                label = "01标";
                break;
            case 1:
                label = "02标";
                break;
            case 2:
                label = "03标";
                break;
            case 3:
                label = "04标";
                break;
            case 4:
                label = "A1标";
                break;
            case 5:
                label = "A2标";
                break;
            case 6:
                label = "A3标";
                break;
            case 7:
                label = "A4标";
                break;
            case 8:
                label = "A5标";
                break;
            case 9:
                label = "A6标";
                break;
            case 10:
                label = "A7标";
                break;
            case 11:
                label = "B1标";
                break;
            case 12:
                label = "B2标";
                break;
            case 13:
                label = "B3标";
                break;
            case 14:
                label = "B4标";
                break;
            case 15:
                label = "C1标";
                break;
            case 16:
                label = "C2标";
                break;
            case 17:
                label = "D1标";
                break;
            case 18:
                label = "D2标";
                break;
            case 19:
                label = "第三方";
                break;
        }
        return label;
    }

}

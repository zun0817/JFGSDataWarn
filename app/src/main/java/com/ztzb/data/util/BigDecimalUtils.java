package com.ztzb.data.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


/**
 * BigDecimal 辅助类
 *
 * 算法工具类
 */
public class BigDecimalUtils {
	public static DecimalFormat format1 = new DecimalFormat("0.0");

	public static DecimalFormat doubleFormat = new DecimalFormat("0.00");
	public static DecimalFormat threeFormat = new DecimalFormat("0.000");
	public static DecimalFormat intFormat = new DecimalFormat("0");
	public static DecimalFormat fiveFormat = new DecimalFormat("0.00000");

	/**
	 * 加法
	 */
	public static BigDecimal add(BigDecimal value1, BigDecimal value2,
			boolean needFormat) {
		if (needFormat) {
			return new BigDecimal(doubleFormat.format(value1.add(value2)));
		} else {
			return value1.add(value2);
		}
	}

	/**
	 * 减法
	 */
	public static BigDecimal sub(BigDecimal value1, BigDecimal value2,
			boolean needFormat) {
		if (needFormat) {
			return new BigDecimal(doubleFormat.format(value1.subtract(value2)));
		} else {
			return value1.subtract(value2);
		}
	}

	/**
	 * 乘法
	 */
	public static BigDecimal mul(BigDecimal value1, BigDecimal value2,
			boolean needFormat) {
		if (needFormat) {
			return new BigDecimal(doubleFormat.format(value1.multiply(value2)));
		} else {
			return value1.multiply(value2);
		}
	}

	/**
	 * 保留小数点后两位
	 */
	public static BigDecimal formatDouble(BigDecimal value1, boolean needFormat) {
		if (needFormat) {
			return new BigDecimal(doubleFormat.format(value1));
		} else {
			return value1;
		}
	}
	public static BigDecimal formatDouble(BigDecimal value1) {
		return formatDouble(value1,true);
	}
	public static BigDecimal format1(BigDecimal value1) {
		return new BigDecimal(format1.format(value1));
	}
    public static BigDecimal formatInt(BigDecimal value1) {
        return new BigDecimal(intFormat.format(value1));
    }

	public static BigDecimal formatFive(BigDecimal value1, boolean needFormat) {
		if (needFormat) {
			return new BigDecimal(fiveFormat.format(value1));
		} else {
			return value1;
		}
	}
	public static BigDecimal formatFive(BigDecimal value1) {
		return formatDouble(value1,true);
	}

	/**
	 * 除法
	 * @param needFormat 为true时保留小数点后两位，
	 */
	public static BigDecimal div(BigDecimal value1, BigDecimal value2,
			boolean needFormat) {
		if (needFormat) {
			return value1.divide(value2, 2, BigDecimal.ROUND_HALF_UP);
		} else {
			return value1.divide(value2, 5, BigDecimal.ROUND_HALF_UP);
		}
	}
	/**
	 * 除法
	 * @param needFormat 为true时保留小数点后三位，
	 */
	public static BigDecimal divThirdFormat(BigDecimal value1, BigDecimal value2,
			boolean needFormat) {
		if (needFormat) {
			return value1.divide(value2, 3, BigDecimal.ROUND_HALF_UP);
		} else {
			return value1.divide(value2, 5, BigDecimal.ROUND_HALF_UP);
		}
	}


	/**
	 * 求得绝对值
	 */
	public static BigDecimal abs(BigDecimal value1,boolean needFormat) {
		if (needFormat) {
			return new BigDecimal(doubleFormat.format(value1.abs()));
		} else {
			return value1.abs();
		}
	}
	/**
	 * 比较大小
	 * 为true时 前比后大
	 * 为false是 前小于等于后
	 */
	public static boolean compare(BigDecimal value1, BigDecimal value2){
		if(value1.compareTo(value2) == 1){
			return true;
		} else {
			return false;
		}
	}

	public static String getPercent(String str) {
		NumberFormat numberFormat = NumberFormat.getPercentInstance(Locale.getDefault());
		numberFormat.setMinimumFractionDigits(1);
		numberFormat.setMaximumFractionDigits(2);
		numberFormat.setGroupingUsed(false);
		return numberFormat.format(Double.parseDouble(str));
	}
}

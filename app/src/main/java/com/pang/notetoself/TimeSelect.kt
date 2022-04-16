package com.pang.notetoself

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.TextView
import java.util.*

class TimeSelect {

    private var calendar = Calendar.getInstance(Locale.CHINA)
    private  val themeResId = 4//0是日历图1是滚轴，还有更多的小伙伴们可以自己试一下
    /**
     * 日期选择
     * @param activity
     * @param tv
     */
    public fun showDatePickerDialog(
        activity: Activity,
        tv: TextView
    ) {
        calendar = Calendar.getInstance(Locale.CHINA)
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        DatePickerDialog(
            activity, themeResId,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                // 绑定监听器(How the parent is notified that the date is set.)
                // 此处得到选择的时间，可以进行你想要的操作
                showTimePickerDialog(
                    activity,
                    tv,
                    "$year-${if (monthOfYear > 8) monthOfYear + 1 else "0${monthOfYear + 1}"}-${if (dayOfMonth > 9) dayOfMonth else "0$dayOfMonth"}"
                )

            } // 设置初始日期
            , calendar.get(Calendar.YEAR)
            , calendar.get(Calendar.MONTH)
            , calendar.get(Calendar.DAY_OF_MONTH)).show()
    }

    /**
     * 时间选择
     * @param activity
     * @param tv
     */
    fun showTimePickerDialog(
        activity: Activity,
        tv: TextView,
        time: String
    ) {
        // Calendar c = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来
        // 解释一哈，Activity是context的子类
        TimePickerDialog(activity, themeResId,  // 绑定监听器
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                tv.text =
                    "$time ${if (hourOfDay > 9) hourOfDay else "0$hourOfDay"}:${if (minute > 9) minute else "0$minute"}:00"
            } // 设置初始时间
            , calendar[Calendar.HOUR_OF_DAY]
            , calendar[Calendar.MINUTE] // true表示采用24小时制
            , true).show()
    }


}
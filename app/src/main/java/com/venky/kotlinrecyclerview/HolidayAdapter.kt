package com.venky.kotlinrecyclerview

import Holidays
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.single_holiday.view.*
import java.time.DayOfWeek
import java.util.*

class HolidayAdapter(val allHolidays: List<Holidays>, val mainActivity: MainActivity) :
    RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.single_holiday,parent,false))
    }

    override fun getItemCount(): Int {
        return allHolidays.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val da: Date
        da = Date(allHolidays.get(position).date.datetime.year,
            allHolidays.get(position).date.datetime.month,
            allHolidays.get(position).date.datetime.day)
        /*Toast.makeText(mainActivity,"${da.day}",Toast.LENGTH_LONG).show()
        Log.i("DATE-->","${da.toGMTString()}")
        da.day*/
        val calendar = Calendar.getInstance()
        calendar.set(allHolidays.get(position).date.datetime.year,allHolidays.get(position).date.datetime.month,allHolidays.get(position).date.datetime.day)
        var day:String = ""
        when(calendar.get(Calendar.DAY_OF_WEEK)){
            7->
                day = "Sun"
            1->
                day = "Mon"
            2->
                day = "Tues"
            3->
                day = "Wed"
            4->
                day = "Thur"
            5->
                day = "Fri"
            6->
                day = "Sat"
        }
        Log.i("--","${calendar.get(Calendar.DAY_OF_WEEK)}")

        holder?.day?.text = day
        holder?.holiday?.text = allHolidays.get(position).name
        holder?.description?.text = allHolidays.get(position).description
        holder?.date?.text = ""+allHolidays.get(position).date.datetime.day

    }

}

class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
    val day = view.dayid
    val date = view.dateid
    val month = view.monthid
    val holiday = view.holidayid
    val description = view.descriptionid
}

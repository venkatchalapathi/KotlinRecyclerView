package com.venky.kotlinrecyclerview

import Datetime
import Holidays
import Json4Kotlin_Base
import Response
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.ls.LSException
import retrofit2.Call
import retrofit2.Callback
import java.util.*
import javax.security.auth.callback.CallbackHandler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Toast.makeText(applicationContext, "Started", Toast.LENGTH_LONG).show()

        holidaysList.layoutManager = LinearLayoutManager(this)
        load()
        Toast.makeText(applicationContext, "Completed...", Toast.LENGTH_LONG).show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.cal_view -> {
                val i = Intent(this,CalViewActivity::class.java)
                startActivity(i)
                Toast.makeText(applicationContext, "Calender Activity", Toast.LENGTH_LONG).show()
                true
            }
            R.id.settings->{
                val i = Intent(this,SettingsActivity::class.java)
                startActivity(i)
                Toast.makeText(applicationContext, "Settings Activity", Toast.LENGTH_LONG).show()
                true
            }
            else ->
                super.onOptionsItemSelected(item)
        }


    }

    fun load() {
        val calInterface = CalInterface.create()
        val holidays: Call<Json4Kotlin_Base>
        holidays = calInterface.getHolidays()

        holidays.enqueue(object : Callback<Json4Kotlin_Base> {
            override fun onResponse(
                call: Call<Json4Kotlin_Base>,
                response: retrofit2.Response<Json4Kotlin_Base>
            ) {

                val data: Json4Kotlin_Base
                data = response.body()!!
                val allHolidays: List<Holidays>


                allHolidays = data.response.holidays

                holidaysList.adapter = HolidayAdapter(allHolidays, this@MainActivity)
                Toast.makeText(applicationContext, "${allHolidays.size}", Toast.LENGTH_LONG).show()


                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFailure(call: Call<Json4Kotlin_Base>, t: Throwable) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })


    }
}

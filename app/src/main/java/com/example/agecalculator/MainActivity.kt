package com.example.agecalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var selectedDate : TextView? =null
    private var ageInMins : TextView? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val birthDayButton: Button= findViewById(R.id.selectBirthDayButton)
        birthDayButton.setOnClickListener {
        clickDatePicker()}

        selectedDate=findViewById(R.id.selectedDate)
        ageInMins=findViewById(R.id.ageInMinutes)

    }
    fun clickDatePicker(){

        val c=Calendar.getInstance()
        val year =c.get(Calendar.YEAR)
        val month =c.get(Calendar.MONTH)
        val day =c.get(Calendar.DAY_OF_MONTH)

        val dpd= DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view,selectedyear,selectedmonthOfYear,selecteddayOfMonth ->

                Toast.makeText(this,"Selected year $selectedyear, Selected monty ${selectedmonthOfYear+1}, Selected day ${selecteddayOfMonth+1}", Toast.LENGTH_LONG).show()
            val selected="$selecteddayOfMonth/${selectedmonthOfYear+1}/$selectedyear"
            selectedDate?.setText(selected)
            val sdf= SimpleDateFormat("DD/MM/YYYY", Locale.ENGLISH)
            val theDate = sdf.parse(selected)
            val dateInMins = theDate.time/60000
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes=currentDate.time/60000
            val difference = currentDateInMinutes-dateInMins
            ageInMins?.text=difference.toString()

        },year,month,day)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()


    }
}
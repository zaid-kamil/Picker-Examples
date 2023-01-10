package com.example.pickerexamples

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import com.example.pickerexamples.databinding.ActivityMainBinding
import java.text.DateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity(), OnDateSetListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            viewDateSelect.setOnClickListener {
                DatePickerFragment().show(supportFragmentManager, "SelectedDate")
            }
        }
    }

    class DatePickerFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            return DatePickerDialog(
                requireContext(),
                activity as OnDateSetListener, year, month, day
            )
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
        val c = Calendar.getInstance()
        c.set(Calendar.YEAR, year)
        c.set(Calendar.MONTH, month)
        c.set(Calendar.DAY_OF_MONTH, day)
        val selectedDate = DateFormat.getDateInstance().format(c.time)
        binding.tvSelectedDate.text = selectedDate
    }
}
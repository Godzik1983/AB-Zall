package com.example.skillbox_hw_quiz


import android.app.DatePickerDialog
import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.skillbox_hw_quiz.databinding.FragmentDataEnterBinding
import com.example.skillbox_hw_quiz.ui.main.MainViewModel
import java.util.Calendar

class DataFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private var anyDay: String = "null"
    private val viewModel by activityViewModels<MainViewModel>()
    private var day = 0
    private var month = 0
    private var year = 0
    private var currentDay = 0
    private var currentMonth = 0
    private var currentYear = 0
    private var vb: FragmentDataEnterBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FrameLayout? {
        vb = FragmentDataEnterBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb!!.rezultButton.isVisible = false

        val calendar = Calendar.getInstance()
        day = calendar.get(Calendar.DAY_OF_MONTH)
        month = calendar.get(Calendar.MONTH)
        year = calendar.get(Calendar.YEAR)
        val datePickerDialog = DatePickerDialog(requireContext(), this, year, month, day)
        datePickerDialog.setTitle(R.string.type_date)
        datePickerDialog.show()

        vb!!.dataButton.setOnClickListener {
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            //val datePickerDialog = DatePickerDialog(requireContext(), this, year, month, day)
            datePickerDialog.setTitle(R.string.type_date)
            datePickerDialog.show()
        }

        vb!!.rezultButton.setOnClickListener {
            view.findNavController().navigate(R.id.action_ekranOprosa_to_otvetFragment)
            enterTransition = Explode()
            exitTransition = Slide(Gravity.END)
            viewModel.dateEntered(anyDay)
            viewModel.countFinalNumber()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        this.currentDay = dayOfMonth
        this.currentYear = year
        this.currentMonth = month + 1
        this.anyDay = "$currentDay$currentMonth$currentYear"
        if (anyDay == "null") {
            vb?.dataButton!!.isVisible = true
            vb!!.rezultButton.isVisible = false
        } else {
            vb!!.rezultButton.isVisible = true
            vb?.dataButton!!.isVisible = false
        }
    }
}





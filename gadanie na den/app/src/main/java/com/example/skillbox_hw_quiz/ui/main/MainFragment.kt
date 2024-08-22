package com.example.skillbox_hw_quiz.ui.main


import android.app.DatePickerDialog
import android.os.Bundle
import android.transition.Explode
import android.transition.Slide
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.FrameLayout
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.skillbox_hw_quiz.R
import com.example.skillbox_hw_quiz.databinding.MainFragmentBinding
import com.google.android.material.snackbar.Snackbar
import java.util.Calendar

class MainFragment : Fragment(), DatePickerDialog.OnDateSetListener {
    private var birthday: String = "null"
    private val viewModel by activityViewModels<MainViewModel>()
    private var day = 0
    private var month = 0
    private var year = 0
    private var currentDay = 0
    private var currentMonth = 0
    private var currentYear = 0
    private val calendar = Calendar.getInstance()
    private var vb: MainFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FrameLayout? {
        vb = MainFragmentBinding.inflate(inflater, container, false)
        return vb?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vb!!.dataRozdeniyaButton.setOnClickListener {
            day = calendar.get(Calendar.DAY_OF_MONTH)
            month = calendar.get(Calendar.MONTH)
            year = calendar.get(Calendar.YEAR)
            val datePickerDialog = DatePickerDialog(requireContext(), this, year, month, day)
            datePickerDialog.setTitle(R.string.type_birthday)
            datePickerDialog.updateDate(1990, 6, 15)
            datePickerDialog.show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        this.currentDay = dayOfMonth
        this.currentYear = year
        this.currentMonth = month + 1
        this.birthday = "$currentDay$currentMonth$currentYear"
        Snackbar.make(requireView().rootView, R.string.ok_date, Snackbar.LENGTH_SHORT).show()
        vb?.dataRozdeniyaButton!!.isVisible = birthday == "null"
        findNavController().navigate(R.id.action_mainFragment_to_ekranOprosa).apply {
            enterTransition = Explode()
            exitTransition = Slide(Gravity.RIGHT)
            viewModel.numberEntered(birthday)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }
}


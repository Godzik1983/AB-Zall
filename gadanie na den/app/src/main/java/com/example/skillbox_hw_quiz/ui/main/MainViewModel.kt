package com.example.skillbox_hw_quiz.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private lateinit var birthDate: String
    private lateinit var date: String
    private val _result = MutableStateFlow(100)
    val result = _result.asStateFlow()

    fun dateEntered(anyDay: String) {
        date = anyDay
        Log.d("TAG", date)
    }

    fun numberEntered(value: String) {
        birthDate = value
        Log.d("TAG", birthDate)
    }

    private fun multiplyToSimpleNumber(date: String): Int {
        val list = date.map {
            it.toString().toInt()
        }
        val sumString = list.sum().toString()
        val list2 = sumString.map {
            it.toString().toInt()
        }
        val chislo = list2.sum()
        if (chislo <= 9) {
            return chislo
        } else {
            val sumString2 = chislo.toString()
            val list3 = sumString2.map {
                it.toString().toInt()
            }
            val chislo2 = list3.sum()
            return chislo2
        }
    }

    fun countFinalNumber() {
        val numberBirth = multiplyToSimpleNumber(birthDate)
        val numberDate = multiplyToSimpleNumber(date)
        val finalNumber = numberBirth + numberDate
        if (finalNumber <= 9) {
            _result.value = finalNumber
        } else {
            val string = finalNumber.toString()
            val list = string.map {
                it.toString().toInt()
            }
            val newFinalNum = list.sum()
            _result.value = newFinalNum
        }
    }






}
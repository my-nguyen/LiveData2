package com.nguyen.livedata2

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private const val TAG = "MainViewModel"
class MainViewModel: ViewModel() {
    private lateinit var timer: CountDownTimer
    private val _seconds = MutableLiveData<Int>()
    val seconds = _seconds
    private val _finished = MutableLiveData<Boolean>()
    val finished = _finished
    var timerValue = MutableLiveData<Long>()

    fun startTimer() {
        timer = object : CountDownTimer(timerValue.value!!.toLong(), 1000) {
            override fun onTick(p0: Long) {
                _seconds.value = p0.toInt() / 1000
            }

            override fun onFinish() {
                Log.d(TAG, "onFinish")
            }
        }.start()
    }

    fun stopTimer() {
        timer.cancel()
    }
}
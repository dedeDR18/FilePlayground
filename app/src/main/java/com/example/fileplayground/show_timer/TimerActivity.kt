package com.example.fileplayground.show_timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.example.fileplayground.R
import com.example.fileplayground.databinding.ActivityTimerBinding
import java.util.*
import java.util.concurrent.TimeUnit

class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding

    private val duration: Long = 12000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTimerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        countDown()
        upAndDownButton()
    }

    private fun upAndDownButton(){
        binding.btnDown.setOnClickListener{
            Toast.makeText(this, "btn down", Toast.LENGTH_SHORT).show()
        }
        binding.btnUp.setOnClickListener {
            Toast.makeText(this, "btn up", Toast.LENGTH_SHORT).show()
        }
    }

    private fun countDown() {
        binding.btnStart.setOnClickListener {
            val cd = (object : CountDownTimer(duration * 1000, 1000) {
                override fun onTick(milisUntilFinished: Long) {

                    val time = String.format(
                        Locale.getDefault(),
                        "%02d:%02d:%02d",
                        TimeUnit.MILLISECONDS.toHours(milisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(milisUntilFinished) -
                                TimeUnit.HOURS.toMinutes(
                                    TimeUnit.MILLISECONDS.toHours(
                                        milisUntilFinished
                                    )
                                ),
                        TimeUnit.MILLISECONDS.toSeconds(milisUntilFinished) -
                                TimeUnit.MINUTES.toSeconds(
                                    TimeUnit.MILLISECONDS.toMinutes(
                                        milisUntilFinished
                                    )
                                )
                    )
                    binding.countdown.text = time
                }

                override fun onFinish() {
                    binding.countdown.text = resources.getString(R.string.done)
                }

            })
            cd.start()
        }
    }
}
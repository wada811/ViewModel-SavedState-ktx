package com.wada811.viewmodelsavedstate.sample

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wada811.databinding.dataBinding
import com.wada811.viewmodelsavedstate.sample.databinding.SampleActivityBinding

class SampleActivity : AppCompatActivity() {

    private val binding: SampleActivityBinding by dataBinding(R.layout.sample_activity)
    private val viewModel: SampleViewModel by viewModels()
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.appendLog("Activity::onCreate")
        binding.viewModel = viewModel
        binding.activityCountText.text = "$count"
        binding.countUpButton.setOnClickListener {
            binding.activityCountText.text = "${++count}"
            viewModel.countUp()
        }
        binding.rotateButton.setOnClickListener {
            viewModel.appendLog("Activity::rotate")
            requestedOrientation = when (requestedOrientation) {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT -> ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                else -> ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        }
        binding.restartButton.setOnClickListener {
            if (isEnabledDoNotKeepActivities()) {
                viewModel.appendLog("Activity::restart")
                startActivity(MainActivity.createIntent(this))
            } else {
                Toast.makeText(this, "adb shell settings put global always_finish_activities 1", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun isEnabledDoNotKeepActivities(): Boolean {
        val doNotKeepActivities = Settings.Global.getInt(contentResolver, Settings.Global.ALWAYS_FINISH_ACTIVITIES, 0)
        return doNotKeepActivities != 0
    }

    override fun onPause() {
        super.onPause()
        viewModel.appendLog("Activity::onPause")
    }

    override fun onResume() {
        super.onResume()
        viewModel.appendLog("Activity::onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.appendLog("Activity::onDestroy")
    }
}


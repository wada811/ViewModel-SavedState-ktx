package com.wada811.viewmodelsavedstatektx.sample

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.wada811.databinding.dataBinding
import com.wada811.viewmodelsavedstatektx.extension.putExtra
import com.wada811.viewmodelsavedstatektx.sample.SampleViewModel.CountUpValue
import com.wada811.viewmodelsavedstatektx.sample.SampleViewModel.CountUpValue.ONE
import com.wada811.viewmodelsavedstatektx.sample.SampleViewModel.CountUpValue.TEN
import com.wada811.viewmodelsavedstatektx.sample.databinding.SampleActivityBinding

class SampleActivity : AppCompatActivity(R.layout.sample_activity) {
    private val binding: SampleActivityBinding by dataBinding()
    private val viewModel: SampleViewModel by viewModels()
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.appendLog("Activity::onCreate")
        binding.viewModel = viewModel
        binding.activityCountText.text = "$count"
        binding.countUpButton.setOnClickListener {
            count += viewModel.countUpValueEnumLiveData.value?.count ?: 0
            binding.activityCountText.text = "$count"
            viewModel.countUp()
        }
        when (viewModel.countUpValueEnumLiveData.value) {
            ONE -> binding.plusOneButton.isChecked = true
            TEN -> binding.plusTenButton.isChecked = true
            null -> Unit
        }
        binding.plusOneButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.plusTenButton.isChecked = false
            }
            viewModel.countUpValueEnumLiveData.value = if (isChecked) CountUpValue.ONE else null
        }
        binding.plusTenButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.plusOneButton.isChecked = false
            }
            viewModel.countUpValueEnumLiveData.value = if (isChecked) CountUpValue.TEN else null
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

    companion object {
        @JvmStatic
        fun createIntent(context: Context): Intent = Intent(context, SampleActivity::class.java).also {
            it.putExtra(SampleViewModel::savedStateCount, 0)
        }
    }
}


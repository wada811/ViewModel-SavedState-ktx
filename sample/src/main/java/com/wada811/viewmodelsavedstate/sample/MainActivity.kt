package com.wada811.viewmodelsavedstate.sample

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object {
        private const val ACTION_RESTART = "ACTION_RESTART"
        @JvmStatic
        fun createIntent(context: Context): Intent = Intent(context, MainActivity::class.java).also {
            it.action = ACTION_RESTART
            it.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (intent.action == ACTION_RESTART) {
            setContentView(R.layout.main_activity)
            Handler().postDelayed({
                finish()
            }, TimeUnit.SECONDS.toMillis(1))
            return
        }
        startActivity(Intent(this, SampleActivity::class.java).also {
            it.putExtra(SampleViewModel::savedStateCount.name, 0)
            it.putExtra(SampleViewModel::log.name, "Log:")
        })
        finish()
        return
    }
}

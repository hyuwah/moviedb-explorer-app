package dev.hyuwah.moviedbexplorer.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dev.hyuwah.moviedbexplorer.databinding.ActivitySplashBinding
import dev.hyuwah.moviedbexplorer.presentation.utils.viewBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivitySplashBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        lifecycleScope.launch {
            delay(1500)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }
}
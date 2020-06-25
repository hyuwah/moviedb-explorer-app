package dev.hyuwah.moviedbexplorer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.hyuwah.moviedbexplorer.databinding.ActivityMainBinding
import dev.hyuwah.moviedbexplorer.presentation.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}
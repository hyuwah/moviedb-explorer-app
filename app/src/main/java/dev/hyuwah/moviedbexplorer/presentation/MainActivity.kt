package dev.hyuwah.moviedbexplorer.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dev.hyuwah.moviedbexplorer.R
import dev.hyuwah.moviedbexplorer.databinding.ActivityMainBinding
import dev.hyuwah.moviedbexplorer.presentation.utils.viewBinding

class MainActivity : AppCompatActivity() {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val navController: NavController
        get() = findNavController(R.id.nav_host_fragment)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}
package dev.hyuwah.moviedbexplorer.helper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.MockKAnnotations
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class BaseTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatchers = TestCoroutineDispatcher()


    @Before
    open fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatchers)
    }

    @After
    open fun tearDown() {
        Dispatchers.resetMain()
    }
}
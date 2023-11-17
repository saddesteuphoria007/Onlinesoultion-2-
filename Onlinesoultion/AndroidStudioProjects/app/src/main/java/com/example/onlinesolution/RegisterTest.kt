package com.example.onlinesolution

import android.media.audiofx.DynamicsProcessing
import android.widget.EditText
import com.example.onlinesolution.databinding.ActivityRegisterBinding
import com.google.android.gms.tasks.Task
import com.google.ar.core.Config
import com.google.firebase.auth.FirebaseAuth
import org.hamcrest.CoreMatchers.any
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = DynamicsProcessing.Config.NONE)
class RegisterTest {

    private lateinit var activity: Register

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth

    @Mock
    private lateinit var binding: ActivityRegisterBinding

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        activity = Robolectric.buildActivity(Register::class.java)
            .create()
            .resume()
            .get()
        activity.firebaseAuth = firebaseAuth
        activity.binding = binding
    }

    @Test
    fun testRegistrationSuccess() {
        // Mocking user input
        `when`(binding.emailEditText.text).thenReturn(mock(EditText::class.java))
        `when`(binding.emailEditText.text.toString()).thenReturn("test@example.com")

        `when`(binding.password.text).thenReturn(mock(EditText::class.java))
        `when`(binding.password.text.toString()).thenReturn("password123")

        `when`(binding.passwordEditText.text).thenReturn(mock(EditText::class.java))
        `when`(binding.passwordEditText.text.toString()).thenReturn("password123")

        // Mocking FirebaseAuth.createUserWithEmailAndPassword
        `when`(firebaseAuth.createUserWithEmailAndPassword(any(), any()))
            .thenReturn(mockTask(true))

        // Trigger the button click
        activity.findViewById<Button>(R.id.registerButton).performClick()

        // Verify that startActivity was called (registration successful)
        assertTrue(activity.isFinishing)
    }

    @Test
    fun testRegistrationFailure() {
        // Mocking user input
        `when`(binding.emailEditText.text).thenReturn(mock(EditText::class.java))
        `when`(binding.emailEditText.text.toString()).thenReturn("test@example.com")

        `when`(binding.password.text).thenReturn(mock(EditText::class.java))
        `when`(binding.password.text.toString()).thenReturn("password123")

        `when`(binding.passwordEditText.text).thenReturn(mock(EditText::class.java))
        `when`(binding.passwordEditText.text.toString()).thenReturn("password123")

        // Mocking FirebaseAuth.createUserWithEmailAndPassword
        `when`(firebaseAuth.createUserWithEmailAndPassword(any(), any()))
            .thenReturn(mockTask(false))

        // Trigger the button click
        activity.findViewById<Button>(R.id.registerButton).performClick()

        // Verify that Toast.makeText was called (registration failed)
        // Note: This is a simplistic verification, you might need to refine it based on your actual implementation
        verify(activity).makeText(any(), any(), anyInt())
    }

    private fun <T> mockTask(result: T): Task<T> {
        val task = mock(Task::class.java) as Task<T>
        `when`(task.isSuccessful).thenReturn(true)
        `when`(task.result).thenReturn(result)
        return task
    }
}
package com.example.animedata

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.example.animedata.ui.theme.AnimeDataTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException

class UserRegisterActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeDataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UserRegisterForm()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@RequiresApi(Build.VERSION_CODES.P)
fun UserRegisterForm() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordRepeat by remember { mutableStateOf("") }
    val context = LocalContext.current
    val auth = FirebaseAuth.getInstance()
    val navigate = Intent(context, MainActivity::class.java)
    val navigateToLoginActivity = Intent(context, UserAuthActivity::class.java)

    Column(
        modifier = Modifier
            .padding(56.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopAppBar(
            title = {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Sign Up",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 38.sp,
                            fontWeight = FontWeight.Black
                        )
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = passwordRepeat,
            onValueChange = { passwordRepeat = it },
            label = { Text("Repeat Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedButton(onClick = {
                if (validateInput(email, password, passwordRepeat, context)) {
                    checkEmailAndRegister(email, password, auth, context, navigate)
                }
            }) {
                Text("Sign Up")
            }

            Spacer(modifier = Modifier.width(16.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Already have an account? Log In",
                color = Color.White,
                modifier = Modifier.clickable {
                    ContextCompat.startActivity(context, navigateToLoginActivity, null)
                }
            )
        }
    }
}

fun validateInput(
    email: String,
    password: String,
    passwordRepeat: String,
    context: android.content.Context
): Boolean {
    return when {
        email.isBlank() || password.isBlank() || passwordRepeat.isBlank() -> {
            Toast.makeText(context, "Please fill all the inputs", Toast.LENGTH_SHORT).show()
            false
        }

        !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
            Toast.makeText(
                context,
                "Please enter a valid email",
                Toast.LENGTH_SHORT
            ).show()
            false
        }

        password.length < 6 -> {
            Toast.makeText(
                context,
                "The password must have at least 6 characters",
                Toast.LENGTH_SHORT
            ).show()
            false
        }

        password != passwordRepeat -> {
            Toast.makeText(context, "The passwords do not match", Toast.LENGTH_SHORT).show()
            false
        }

        else -> true
    }
}

fun checkEmailAndRegister(
    email: String,
    password: String,
    auth: FirebaseAuth,
    context: android.content.Context,
    navigate: Intent
) {
    auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val result = task.result?.signInMethods
            if (result.isNullOrEmpty()) {
                registerUser(email, password, auth, context, navigate)
            } else {
                Toast.makeText(context, "This email is already been used", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Error verifying the email", Toast.LENGTH_SHORT).show()
        }
    }
}

fun registerUser(
    email: String,
    password: String,
    auth: FirebaseAuth,
    context: android.content.Context,
    navigate: Intent
) {
    auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            Toast.makeText(context, "Successful Registration", Toast.LENGTH_SHORT).show()
            ContextCompat.startActivity(context, navigate, null)
        } else {
            if (task.exception is FirebaseAuthUserCollisionException) {
                Toast.makeText(context, "This email is already been used", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(context, "Unsuccessful Registration", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

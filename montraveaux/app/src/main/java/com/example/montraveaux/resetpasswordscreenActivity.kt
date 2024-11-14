package com.example.tpjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ResetPasswordActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResetPasswordScreen(
                onResetPassword = { email ->
                    // Logique de réinitialisation ici
                },
                onNavigateBack = {
                    finish() // Revenir à l'écran de connexion
                }
            )
        }
    }
}

@Composable
fun ResetPasswordScreen(onResetPassword: (String) -> Unit, onNavigateBack: () -> Unit) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Button(onClick = { onResetPassword(email) }) {
            Text("Reset Password")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = onNavigateBack) {
            Text("Back to Login")
        }
    }
}

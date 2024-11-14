package com.example.tpjetpackcompose

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

class SignupActivity : ComponentActivity, Parcelable {
    constructor() : super()
    constructor(parcel: Parcel) : this() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupScreen(
                onSignup = { name, email, password ->
                    // Logique d'inscription ici
                },
                onNavigateBack = {
                    finish() // Revenir à l'écran de connexion
                }
            )
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SignupActivity> {
        override fun createFromParcel(parcel: Parcel): SignupActivity {
            return SignupActivity(parcel)
        }

        override fun newArray(size: Int): Array<SignupActivity?> {
            return arrayOfNulls(size)
        }
    }
}

@Composable
fun SignupScreen(onSignup: (String, String, String) -> Unit, onNavigateBack: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )
        Button(onClick = { onSignup(name, email, password) }) {
            Text("Sign Up")
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = onNavigateBack) {
            Text("Back to Login")
        }
    }
}

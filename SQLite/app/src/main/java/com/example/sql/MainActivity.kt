package com.example.sql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.sql.ui.theme.SqlTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import com.example.sql.data.Database


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SqlTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    UserRegistrationScreen(this)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegistrationScreen(context: Context) {
    var donnee by remember { mutableStateOf("") }
    var donneeList by remember { mutableStateOf(emptyList<String>()) }
    var selectedUser by remember { mutableStateOf<String?>(null) }
    val dbase = Database(context)

    LaunchedEffect(Unit) {
        donneeList = dbase.getAllUsers()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = donnee,
            onValueChange = { donnee = it },
            label = { Text("Donnée") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            if (selectedUser != null) {
                dbase.updateUser(selectedUser!!, donnee)
                selectedUser = null
            } else {
                dbase.insertUser(donnee)
            }
            donnee = ""
            donneeList = dbase.getAllUsers()
        }) {
            Text(if (selectedUser == null) "Enregistrer" else "Modifier")
        }

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(donneeList) { user ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(user, modifier = Modifier.align(Alignment.CenterVertically))

                    Button(onClick = {
                        selectedUser = user
                        donnee = user
                    }) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Modifier")
                    }

                    Button(onClick = {
                        dbase.deleteUser(user)
                        donneeList = dbase.getAllUsers()
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Supprimer")
                    }

                }
            }
        }
    }
}

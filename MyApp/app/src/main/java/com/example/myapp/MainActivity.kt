package com.example.myapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapp.ui.theme.MyAppTheme
import com.example.myapp.ui.theme.SecondActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GreetingWithCenteredButton(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun GreetingWithCenteredButton(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        val  context = LocalContext.current

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hello $name!")

            Spacer(modifier = Modifier.height(16.dp)) // Space between text and button

            Button(onClick = {val intent =Intent(context,SecondActivity:: class.java)
                intent.putExtra("soulayma","23")

            context.startActivity(intent)
            }) {

                Text(text = "Click Me")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingWithCenteredButtonPreview() {
    MyAppTheme {
        GreetingWithCenteredButton("Android")
    }
}

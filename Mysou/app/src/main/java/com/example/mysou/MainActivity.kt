package com.example.mysou

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysou.ui.theme.MysouTheme

// Création de la classe Product
data class Product(
    val id: Int,
    val name: String,
    val imageResId: Int,
    val description: String
)

val LightPink = Color(0xFFE4D7FF)
val DarkPurple = Color(0x74876A8F)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("Login") }
    val productList = listOf(
        Product(1, "Product 1", R.drawable.b6, "Description of Product 1"),
        Product(2, "Product 2", R.drawable.boucle3, "Description of Product 2"),
        Product(3, "Product 3", R.drawable.boucles1, "Description of Product 3"),
        Product(4, "Product 4", R.drawable.boucles2, "Description of Product 4"),
        Product(5, "Product 5", R.drawable.boucles4, "Description of Product 5")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(DarkPurple, LightPink)
                )
            )
    ) {
        when (currentScreen) {
            "Login" -> LoginScreen(
                onLoginSuccess = { currentScreen = "Home" },
                onForgotPasswordClick = { currentScreen = "ResetPassword" },
                onSignUpClick = { currentScreen = "SignUp" }
            )
            "Home" -> HomeScreen(
                onLogoutClick = { currentScreen = "Login" },
                productList = productList
            )
            "ResetPassword" -> ResetPasswordScreen(
                onBackClick = { currentScreen = "Login" }
            )
            "SignUp" -> SignUpScreen(
                onBackClick = { currentScreen = "Login" }
            )
        }
    }
}

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onForgotPasswordClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", fontSize = 24.sp, color = Color.White)

        BasicTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White)
        )

        BasicTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
        )

        Checkbox(
            checked = showPassword,
            onCheckedChange = { showPassword = it }
        )
        Text("Show Password")

        Button(
            onClick = { onLoginSuccess() },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Login")
        }

        TextButton(onClick = onForgotPasswordClick) {
            Text("Forgot Password?", color = Color.White)
        }

        TextButton(onClick = onSignUpClick) {
            Text("Sign Up", color = Color.White)
        }
    }
}

@Composable
fun HomeScreen(onLogoutClick: () -> Unit, productList: List<Product>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Text("Welcome to Home", fontSize = 24.sp, color = Color.White)

        Button(
            onClick = onLogoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = LightPink)
        ) {
            Text("Logout", fontSize = 20.sp, color = Color.Black)
        }

        productList.forEach { product ->
            ProductItem(product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    var showDescription by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { showDescription = !showDescription },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                modifier = Modifier.size(128.dp)
            )
            Text(text = product.name, fontSize = 20.sp, color = Color.Black)

            Button(
                onClick = { showDescription = !showDescription },
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(if (showDescription) "Hide Description" else "Show Description")
            }

            if (showDescription) {
                Text(text = product.description, color = Color.Black, modifier = Modifier.padding(top = 8.dp))
            }
        }
    }
}

@Composable
fun ResetPasswordScreen(onBackClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Reset Password", fontSize = 24.sp, color = Color.White)

        BasicTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White)
        )

        BasicTextField(
            value = newPassword,
            onValueChange = { newPassword = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.White),
            visualTransformation = PasswordVisualTransformation()
        )

        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Reset Password")
        }

        Button(
            onClick = onBackClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Back to Login")
        }
    }
}

@Composable
fun SignUpScreen(onBackClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, content = {
            Text("Sign Up", fontSize = 24.sp, color = Color.White)

            // Champ email
            BasicTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White),
                decorationBox = { innerTextField ->
                    if (email.isEmpty()) {
                        Text("Enter your email", color = Color.Gray)
                    }
                    innerTextField()
                }
            )

            // Champ mot de passe
            BasicTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White),
                visualTransformation = PasswordVisualTransformation(),
                decorationBox = { innerTextField ->
                    if (password.isEmpty()) {
                        Text("Enter your password", color = Color.Gray)
                    }
                    innerTextField()
                }
            )

            // Champ confirmation du mot de passe
            BasicTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.White),
                visualTransformation = PasswordVisualTransformation(),
                decorationBox = { innerTextField ->
                    if (confirmPassword.isEmpty()) {
                        Text("Confirm your password", color = Color.Gray)
                    }
                    innerTextField()
                }
            )

            // Bouton Inscription
            Button(
                onClick = { /* Logique pour s'inscrire */ },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Sign Up")
            }

            // Bouton Retour
            Button(
                onClick = onBackClick,
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Back to Login")
            }}}
        }
        @Preview( showBackground = true )
@Composable
funSignUpScreen () {
    MysouTheme {
        SignUpScreen()
    }
}
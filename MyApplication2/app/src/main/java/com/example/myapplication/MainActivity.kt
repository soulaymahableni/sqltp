package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // Create and set up the navigation controller
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    // Navigation setup with routes
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("forgotPassword") { ForgotPasswordScreen(navController) }
        composable("home") { HomeScreen(navController) }
        composable("productDetails/{productName}/{productDescription}/{productPrice}/{productImageId}") { backStackEntry ->
            val productName = backStackEntry.arguments?.getString("productName")
            val productDescription = backStackEntry.arguments?.getString("productDescription")
            val productPrice = backStackEntry.arguments?.getString("productPrice")
            val productImageId = backStackEntry.arguments?.getString("productImageId")?.toInt() ?: 0
            ProductDetailsScreen(productName, productDescription, productPrice, productImageId)
        }
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Login", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = email,
            onValueChange = { email = it; emailError = false },
            label = { Text("Email") },
            isError = emailError
        )
        if (emailError) {
            Text("Invalid email address", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = password,
            onValueChange = { password = it; passwordError = false },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            isError = passwordError
        )
        if (passwordError) {
            Text("Password cannot be empty", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            emailError = email.isBlank()
            passwordError = password.isBlank()

            if (!emailError && !passwordError) {
                if (email == "achref@gmail.com" && password == "1230") {
                    navController.navigate("home")
                } else {
                    emailError = true
                    passwordError = true
                }
            }
        }) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("forgotPassword") }) {
            Text("Forgot Password?")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("signup") }) {
            Text("Don't have an account? Sign Up")
        }
    }
}

@Composable
fun SignupScreen(navController: NavController) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Sign Up", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        TextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name") })
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") })
        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = email, onValueChange = {
            email = it
            emailError = false
        }, label = { Text("Email") }, isError = emailError)
        if (emailError) {
            Text("Invalid email address", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(value = password, onValueChange = {
            password = it
            passwordError = false
        }, label = { Text("Password") }, visualTransformation = PasswordVisualTransformation(), isError = passwordError)
        if (passwordError) {
            Text("Password cannot be empty", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            emailError = email.isBlank()
            passwordError = password.isBlank()

            if (!emailError && !passwordError) {
                // Handle sign-up logic here
            }
        }) {
            Text(text = "Sign Up")
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("login") }) {
            Text("Already have an account? Login")
        }
    }
}

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Forgot Password", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        TextField(value = email, onValueChange = {
            email = it
            emailError = false
        }, label = { Text("Email") }, isError = emailError)
        if (emailError) {
            Text("Invalid email address", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            if (email.isBlank()) {
                emailError = true
            } else {
                // Handle reset password logic here
            }
        }) {
            Text(text = "Reset Password")
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate("login") }) {
            Text("Back to Login")
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    val products = listOf(
        Product("Product 1", "Description of product 1", "$10", R.drawable.product1),
        Product("Product 2", "Description of product 2", "$20", R.drawable.product2),
        Product("Product 3", "Description of product 3", "$30", R.drawable.product3),
        Product("Product 4", "Description of product 4", "$40", R.drawable.product4),
        Product("Product 5", "Description of product 5", "$50", R.drawable.product5)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Products", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        // Display the list of products
        products.forEach { product ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable {
                        navController.navigate("productDetails/${product.name}/${product.description}/${product.price}/${product.imageId}")
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = product.imageId),
                    contentDescription = "Product Image",
                    modifier = Modifier.size(120.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(text = product.name, style = MaterialTheme.typography.bodyLarge)
                    Text(text = product.price, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}

@Composable
fun ProductDetailsScreen(productName: String?, productDescription: String?, productPrice: String?, productImageId: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Product Details", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(20.dp))

        Image(
            painter = painterResource(id = productImageId),
            contentDescription = "Product Image",
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Name: $productName", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Description: $productDescription", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Price: $productPrice", style = MaterialTheme.typography.bodyLarge)
    }
}

data class Product(val name: String, val description: String, val price: String, val imageId: Int)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        HomeScreen(navController = rememberNavController())
    }
}

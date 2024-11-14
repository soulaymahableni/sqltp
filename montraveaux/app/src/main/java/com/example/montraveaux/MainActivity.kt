package com.example.montraveaux
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tpjetpackcompose.LoginScreen
import com.example.tpjetpackcompose.ResetPasswordScreen
import com.example.tpjetpackcompose.SignupScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                LoginScreen(
                    onLogin = { email, password ->
                        // Logique de connexion
                    },
                    onNavigateToSignup = {
                        // Logique pour aller vers l'écran d'inscription
                    },
                    onNavigateToResetPassword = {
                        // Logique pour aller vers l'écran de réinitialisation
                    }
                )
            }
        }
        @Composable
        fun LoginScreen(onLogin: (String, String) -> Unit, onNavigateToSignup: () -> Unit, onNavigateToResetPassword: () -> Unit) {
            // Code de l'interface de connexion ici
        }

    }

        @Composable
        fun AppNavigation(navController: NavHostController) {
            // Définir les routes (chemins) pour chaque écran
            NavHost(navController = navController, startDestination = "login") {
                composable("login") {
                    // Appel de l'écran de connexion
                    LoginScreen(
                        onLogin = { email, password ->
                            // Logique de connexion ici

                        },
                        onNavigateToSignup = {
                            // Navigation vers l'écran d'inscription
                            navController.navigate("signup")
                        },
                        onNavigateToResetPassword = {
                            // Navigation vers l'écran de réinitialisation
                            navController.navigate("resetPassword")
                        }
                    )
                }
                composable("signup") {
                    // Appel de l'écran d'inscription
                    SignupScreen(
                        onSignup = { name, email, password ->
                            // Logique d'inscription ici
                        },
                        onNavigateBack = {
                            // Retour à l'écran de connexion
                            navController.popBackStack()
                        }
                    )
                }
                composable("resetPassword") {
                    // Appel de l'écran de réinitialisation
                    ResetPasswordScreen(
                        onResetPassword = { email ->
                            // Logique de réinitialisation ici
                        },
                        onNavigateBack = {
                            // Retour à l'écran de connexion
                            navController.popBackStack()
                        }
                    )
                }
            }
        }

    }


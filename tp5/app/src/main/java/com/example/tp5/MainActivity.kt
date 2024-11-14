
p
importer android.os.Bundle
importer androidx.activity.ComponentActivity
importer androidx.activity.compose.setContent
importer androidx.compose.foundation.Image
importer androidx.compose.foundation.layout.*
importer androidx.compose.foundation.clickable
importer androidx.compose.foundation.shape.RoundedCornerShape
importer androidx.compose.material3.*
importer androidx.compose.runtime.*
importer androidx.compose.ui.Alignment
importer androidx.compose.ui.Modifier
importer androidx.compose.ui.graphics.Color
importer androidx.compose.ui.layout.ContentScale
importer androidx.compose.ui.res.painterResource
importer androidx.compose.ui.text.input.PasswordVisualTransformation
importer androidx.compose.ui.tooling.preview. Aperçu
importer androidx.compose.ui.unit.dp
importer androidx.navigation.NavController
importer androidx.navigation.NavHostController
importer androidx.navigation.compose.NavHost
importer androidx.navigation.compose.composable
importer androidx.navigation.compose.rememberNavController
importer com.example.myapplication.ui.theme.MyApplicationTheme

classe MainActivity : ComponentActivity() {
    remplacer le plaisir onCreate (savedInstanceState : Bundle ?) {
        super .onCreate(état d'instance enregistré)
                définirContenu {
            MonThème d'Application {
            // Créer et configurer le contrôleur de navigation
            val navController = rememberNavController ()
            AppNavigation (contrôleur de navigation)
        }
    }
}
}

@Composable
AppNavigation amusante (navController : NavHostController) {
    // Configuration de la navigation avec des itinéraires
    NavHost (navController, startDestination = "connexion" ) {
        composable ( "connexion" ) { Écran de connexion (navController) }
        composable ( "inscription" ) { SignupScreen (navController) }
        composable ( "mot de passe oublié" ) { Mot de passe oubliéScreen (navController) }
        composable ( "accueil" ) { Écran d'accueil (navController) }
            composable ( "détails du produit/{nom du produit}/{description du produit}/{prix du produit}/{image du produitId}" ) { backStackEntry ->
                val productName = backStackEntry. arguments ?.getString( "productName" )
                val productDescription = backStackEntry. arguments ?.getString( "productDescription" )
                val productPrice = backStackEntry. arguments ?.getString( "productPrice" )
                val productImageId = backStackEntry. arguments ?. getString( "productImageId" )?. toInt () ?: 0
                ProductDetailsScreen (productName, productDescription, productPrice, productImageId)
            }
        }
    }

    @Composable
    écran de connexion amusant (navController : NavController) {
        var email par remember { mutableStateOf ( "" ) }
        var mot de passe par remember { mutableStateOf ( "" ) }
        var emailError par remember { mutableStateOf ( false ) }
        var passwordError par remember { mutableStateOf ( false ) }

        Colonne (
            modifier = Modificateur
                . fillMaxSize ()
                . rembourrage ( 16 . dp ),
            horizontalAlignment = Alignment.CenterHorizontally ,
            verticalArrangement = Arrangement. Centre
        ) {
            Texte ( texte = "Connexion" , style = MaterialTheme. typography . headlineLarge )
            Espaceur ( modifier = Modifier. hauteur ( 20 . dp ))

            Champ de texte (
                valeur = email,
                onValueChange = { email = it ; emailError = false } ,
                label = { Texte ( "E-mail" ) } ,
                isError = emailError
            )
            si (emailError) {
                Texte ( "Adresse e-mail invalide" , couleur = Couleur. Rouge )
            }

            Espaceur ( modifier = Modifier. hauteur ( 8 . dp ))

            Champ de texte (
                valeur = mot de passe,
                onValueChange = { mot de passe = it ; passwordError = false } ,
                label = { Texte ( "Mot de passe" ) } ,
                visualTransformation = Mot de passeVisualTransformation(),
                isError = mot de passeErreur
            )
            si (mot de passeErreur) {
                Texte ( "Le mot de passe ne peut pas être vide" , couleur = Couleur. Rouge )
            }

            Espaceur ( modifier = Modifier. hauteur ( 8 . dp ))

            Bouton ( onClick = {
                emailError = email.isBlank ()
                passwordError = mot de passe.isBlank ()

                si (!emailError && !passwordError) {
                    si (email == " achref@gmail.com " && mot de passe == "1230" ) {
                        navController.navigate( "accueil" )
                    } autre {
                        emailError = vrai
                        mot de passeErreur = vrai
                    }
                }
            } ) {
                Texte ( texte = "Connexion" )
            }

            Espaceur ( modifier = Modifier. hauteur ( 8 . dp ))
            Bouton de texte ( onClick = { navController.navigate( "mot de passe oublié" ) } ) {
                Texte ( « Mot de passe oublié ? » )
            }
            Espaceur ( modifier = Modifier. hauteur ( 8 . dp ))
            Bouton de texte ( onClick = { navController.navigate( "inscription" ) } ) {
                Texte ( « Vous n'avez pas de compte ? Inscrivez-vous » )
            }
        }
    }

    @Composable
    écran d'inscription amusant (navController : NavController) {
    var prénom par remember { mutableStateOf ( "" ) }
    var lastName par remember { mutableStateOf ( "" ) }
    var email par remember { mutableStateOf ( "" ) }
    var mot de passe par remember { mutableStateOf ( "" ) }
    var emailError par remember { mutableStateOf ( false ) }
    var passwordError par remember { mutableStateOf ( false ) }

    Colonne (
        modifier = Modificateur
            . fillMaxSize ()
            . rembourrage ( 16 . dp ),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement. Centre
    ) {
        Texte ( texte = "Inscrivez-vous" , style = MaterialTheme. typography . headlineLarge )
        Espaceur ( modifier = Modifier. hauteur ( 20 . dp ))

        TextField ( valeur = prénom, onValueChange = { prénom = it } , label = { Texte ( "Prénom" ) } )
        Espaceur ( modifier = Modifier. hauteur ( 8 . dp ))

        TextField ( valeur = lastName, onValueChange = { lastName = it } , label = { Texte ( "Nom de famille" ) } )
        Espaceur ( modifier = Modifier. hauteur ( 8 . dp ))

        Champ de texte ( valeur = e-mail, onValueChange = {
            email = ça
            emailError = faux
        } , label = { Texte ( "E-mail" ) } , isError = emailError)
        si (emailError) {
            Texte ( "Adresse e-mail invalide" , couleur = Couleur. Rouge )
        }

        Espaceur ( modifier = Modifier. hauteur ( 8 . dp ))

        TextField ( valeur = mot de passe, onValueChange = {
            mot de passe = il
            passwordError = faux
        } , label = { Texte ( "Mot de passe" ) } , visualTransformation = PasswordVisualTransformation(), isError = passwordError)
        si (mot de passeErreur) {
            Texte ( "Le mot de passe ne peut pas être vide" , couleur = Couleur. Rouge )
        }

        Espaceur ( modifier = Modifier. hauteur ( 8 . dp ))

        Bouton ( onClick = {
            emailError = email.isBlank ()
            passwordError = mot de passe.isBlank ()

            si (!emailError && !passwordError) {
                // Gérez la logique d'inscription ici
            }
        } ) {
            Texte ( texte = "Inscrivez-vous" )
        }

        Espaceur ( modifier = Modifier. hauteur ( 8 . dp ))
        Bouton de texte ( onClick = { navController.navigate( "login" ) } ) {
            Texte ( « Vous avez déjà un compte ? Se connecter » )
        }
    }
}

@Composable
amusant ForgotPasswordScreen (navController: NavController) {
    var email par remember { mutableStateOf ( "" ) }
    var emailError par remember { mutableStateOf ( false ) }

    Colonne (
        modifier = Modificateur
            . fillMaxSize ()
            . rembourrage ( 16 . dp ),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement. Centre
    ) {
        Texte ( texte = "Mot de passe oublié" , style = MaterialTheme. typography . headlineLarge )
        Espaceur ( modifier = Modifier. hauteur ( 20 . dp ))

        Champ de texte ( valeur = e-mail, onValueChange = {
            email = ça
            emailError = faux
        } , label = { Texte ( "E-mail" ) } , isError = emailError)
        si (emailError) {
            Texte ( "Adresse e-mail invalide" , couleur = Couleur. Rouge )
        }

        Espaceur ( modifier = Modifier. hauteur ( 8 . dp ))

        Bouton ( onClick = {
            si ( email.isBlank ()) {
                emailError = vrai
            } autre {
                // Gérez ici la logique de réinitialisation du mot de passe
            }
        } ) {
            Texte ( texte = "Réinitialiser le mot de passe" )
        }

        Espaceur ( modifier = Modifier. hauteur ( 8 . dp ))
        Bouton de texte ( onClick = { navController.navigate( "login" ) } ) {
            Texte ( « Retour à la connexion » )
        }
    }
}

@Composable
écran d'accueil amusant (navController : NavController) {
val produits = listeDe (
    Produit( "Produit 1" , "Description du produit 1" , "$10" , R.drawable.product1 ),
    Produit( "Produit 2" , "Description du produit 2" , "$20" , R.drawable.product2 ),
    Produit( "Produit 3" , "Description du produit 3" , "$30" , R.drawable.product3 ),
    Produit( "Produit 4" , "Description du produit 4" , "$40" , R.drawable.product4 ),
    Produit( "Produit 5" , "Description du produit 5" , "$50" , R.drawable.product5 )
)

Colonne (
modifier = Modificateur
. fillMaxSize ()
. rembourrage ( 16 . dp ),
horizontalAlignment = Alignment.CenterHorizontally ,
verticalArrangement = Arrangement. Haut
) {
    Texte ( texte = "Produits" , style = MaterialTheme. typography . headlineLarge )
    Espaceur ( modifier = Modifier. hauteur ( 20 . dp ))

    // Afficher la liste des produits
    produits. forEach { produit ->
        Rangée (
            modifier = Modificateur
                .fillMaxWidth( )
                . rembourrage ( 8 . dp )
                . cliquable {
                    navController.navigate( "productDetails/ ${ produit. nom } / ${ produit. description } / ${ produit. prix } / ${ produit. imageId } " )
                } ,
            verticalAlignment = Alignement.CentreVerticalement
        ) {
            Image (
                peintre = peintreResource ( id = produit. imageId ),
                contentDescription = "Image du produit" ,
                modifier = Modificateur. taille ( 120 . dp ),
                contentScale = ContentScale. Crop
            )
            Espaceur ( modifier = Modificateur. largeur ( 16 . dp ))
            Colonne {
                Texte ( texte = produit. nom , style = MaterialTheme. typography . bodyLarge )
                Texte ( texte = produit. prix , style = MaterialTheme. typography . bodyMedium )
            }
        }
    }
}
}

@Composable
amusant ProductDetailsScreen (nom du produit : chaîne ?, description du produit : chaîne ?, prix du produit : chaîne ?, image du produit : Int) {
    Colonne (
        modifier = Modificateur
            . fillMaxSize ()
            . rembourrage ( 16 . dp ),
        horizontalAlignment = Alignment.CenterHorizontally ,
        verticalArrangement = Arrangement. Centre
    ) {
        Texte ( texte = "Détails du produit" , style = MaterialTheme. typography . headlineLarge )
        Espaceur ( modifier = Modifier. hauteur ( 20 . dp ))

        Image (
            peintre = peintreResource ( id = productImageId),
            contentDescription = "Image du produit" ,
            modifier = Modificateur. taille ( 200 . dp ),
            contentScale = ContentScale. Crop
        )
        Espaceur ( modifier = Modifier. hauteur ( 16 . dp ))
        Texte ( texte = "Nom : $ productName " , style = MaterialTheme.typography.bodyLarge )
        Texte ( texte = "Description : $ productDescription " , style = MaterialTheme.typography.bodyLarge )
        Texte ( texte = "Prix : $ productPrice " , style = MaterialTheme. typography . bodyLarge )
    }
}

classe de données Produit( val nom : String, val description : String, val prix : String, val imageId : Int)

@Aperçu (showBackground = true )
@Composable
amusant DefaultPreview () {
    MonThème d'Application {
    Écran d'accueil ( navController = rememberNavController ())
}
}
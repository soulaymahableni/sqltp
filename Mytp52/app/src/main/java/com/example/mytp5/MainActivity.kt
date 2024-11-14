import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sqllite.ui.theme.SqlliteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SqlliteTheme {
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
    var userName by remember { mutableStateOf("") }
    var userList by remember { mutableStateOf(emptyList<String>()) }

    val dbHelper = DatabaseHelper(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text("User Name") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            dbHelper.insertUser(userName)
            userName = "" // Clear the text field
            userList = dbHelper.getAllUsers()
        }) {
            Text("Register User")
        }

        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn {
            items(userList) { user ->
                Text(user)
            }
        }
    }
}

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "users.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        // Ajout d'un identifiant unique à chaque utilisateur
        db.execSQL("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }

    fun insertUser(name: String) {
        val db = writableDatabase
        db.beginTransaction()
        try {
            val values = ContentValues().apply {
                put("name", name)
            }
            db.insert("users", null, values)
            db.setTransactionSuccessful() // Marque la transaction comme réussie
        } finally {
            db.endTransaction() // Termine la transaction (qu'elle ait réussi ou échoué)
            db.close()
        }
    }

    fun getAllUsers(): List<String> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT name FROM users", null)
        val userList = mutableListOf<String>()
        cursor.use { // Fermeture automatique du cursor après utilisation
            while (it.moveToNext()) {
                userList.add(it.getString(0))
            }
        }
        db.close()
        return userList
    }
}

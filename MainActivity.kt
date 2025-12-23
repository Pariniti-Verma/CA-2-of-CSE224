package com.cse22android.intentapp
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstScreen(this)
        }
    }
}
@Composable
fun FirstScreen(context: Context) {
    var username by remember { mutableStateOf("") }
    var userType by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = userType,
            onValueChange = { userType = it },
            label = { Text("User Type") }
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(onClick = {
            val intent = Intent(context, SecondActivity::class.java)
            intent.putExtra("username", username)
            intent.putExtra("usertype", userType)
            context.startActivity(intent)
        }) {
            Text("-> Go to Next Screen")
        }
    }
}
class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val username = intent.getStringExtra("username") ?: ""
        val userType = intent.getStringExtra("usertype") ?: ""

        setContent {
            SecondScreen(this, username, userType)
        }
    }
}
@Composable
fun SecondScreen(context: Context, username: String, userType: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Text("Transferred Data")

        Spacer(modifier = Modifier.height(5.dp))

        Text("Username: $username")
        Text("User Type: $userType")

        Spacer(modifier = Modifier.height(15.dp))

        Text("Actions")

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://play.google.com/store")
                )
                context.startActivity(intent)
            }
        ) {
            Text("Open Play Store")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://wa.me/")
                )
                context.startActivity(intent)
            }
        ) {
            Text("Open WhatsApp")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "message/rfc822"
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("test@gmail.com"))
                intent.putExtra(Intent.EXTRA_SUBJECT, "Hello")
                context.startActivity(
                    Intent.createChooser(intent, "Send Email")
                )
            }
        ) {
            Text("Send Email")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("sms:1234567890")
                intent.putExtra("sms_body", "Hello from Android")
                context.startActivity(intent)
            }
        ) {
            Text("Send SMS")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val intent = Intent(Settings.ACTION_SETTINGS)
                context.startActivity(intent)
            }
        ) {
            Text("Open Settings")
        }
    }
}

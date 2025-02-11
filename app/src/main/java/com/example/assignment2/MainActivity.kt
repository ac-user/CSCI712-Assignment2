package com.example.assignment2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Greeting()
        }
    }
}


@Composable
fun Greeting() {
    Column(Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally,
           verticalArrangement = Arrangement.Center) {
        Text( "Alycia Christiansen", fontSize =  21.sp, fontWeight = FontWeight.Bold)
        Text( "#123456" )

        val context = LocalContext.current
        Button(onClick = { context.startActivity(Intent(context, SoftwareChallengesActivity::class.java)) }, shape = CutCornerShape(15)) {
            Text( text = "Start Activity Explicitly")
        }

        val intent = Intent()
        intent.action = "android.intent.action.SOFTWARECHALLENGES"
        Button(onClick = { context.startActivity(intent)}, shape = CutCornerShape(15)){
            Text( text = "Start Activity Implicitly")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment2Theme {
        Greeting()
    }
}
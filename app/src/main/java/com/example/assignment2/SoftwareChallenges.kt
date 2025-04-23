package com.example.assignment2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class SoftwareChallengesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            SoftwareChallenges()
        }
    }
}

@Composable
fun SoftwareChallenges(){
    Column(Modifier.padding(48.dp).fillMaxSize(),
           verticalArrangement = Arrangement.Center) {

        Text( "Software Challenges:", fontSize =  21.sp, fontWeight = FontWeight.Bold )
        Text( "1. Fragmentation (Device, OS, Device & OS)")
        Text( "2. Environment unstable & dynamic")
        Text( "3. Rapid changes")
        Text( "4. Tool support")
        Text( "5. Low tolerance for usability issues")
        Text( "6. Low security & privacy awareness")

        val context = LocalContext.current
        Button(onClick = {  context.startActivity(Intent(context, MainActivity::class.java)) }, shape = CutCornerShape(15)) {
            Text( text = "Main Activity ")
        }

    }
}

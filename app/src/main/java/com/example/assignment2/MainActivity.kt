package com.example.assignment2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.assignment2.ui.theme.Assignment2Theme

class MainActivity : ComponentActivity() {

    private var PERMISSION_MSE412 = "com.example.assignment2.MSE412"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
             Greeting()
        }
    }


    private val requestPerissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            isGranted ->
        if(isGranted){
            navigateToSecondActivity()
        }
        else{
            showToast()
        }
    }

    private fun requestPermission(){
        when {
            ContextCompat.checkSelfPermission(this, PERMISSION_MSE412) == PackageManager.PERMISSION_GRANTED -> {
                navigateToSecondActivity()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(this, PERMISSION_MSE412) -> {

            }

            else -> requestPerissionLauncher.launch(PERMISSION_MSE412)
        }
    }

    private fun navigateToSecondActivity(){
        val intent = Intent(this, SoftwareChallengesActivity::class.java)
        startActivity(intent)
    }

    private fun showToast(){
        val toast = Toast.makeText(this, "Need to accept permission to move to other activity", Toast.LENGTH_SHORT)
        toast.show();
    }

    @Composable
    fun Greeting() {
        Column(Modifier.fillMaxSize(),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center) {
            Text( "Alycia Christiansen", fontSize =  21.sp, fontWeight = FontWeight.Bold)
            Text( "#123456" )

            Button(onClick = { requestPermission() }, shape = CutCornerShape(15)) {
                Text( text = "Start Activity Explicitly")
            }

            Button(onClick = { requestPermission() }, shape = CutCornerShape(15)){
                Text( text = "Start Activity Implicitly")
            }

            val context = LocalContext.current
            Button(onClick = {context.startActivity(Intent(context, ImageCaptureActivity::class.java))}, shape = CutCornerShape(15)){
                Text("View Image Activity")
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
}
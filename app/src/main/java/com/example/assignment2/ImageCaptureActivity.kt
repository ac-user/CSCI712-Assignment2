package com.example.assignment2

import android.content.pm.PackageManager
import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class ImageCaptureActivity : ComponentActivity() {

    private lateinit var outputDir : File
    private lateinit var cameraExecutor : ExecutorService
    private lateinit var imageUri : Uri
    private var showImage : MutableState<Boolean> = mutableStateOf(false)
    private var showCamera : MutableState<Boolean> = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            if(showCamera.value) {
                Camera(
                    outputDir,
                    cameraExecutor,
                    ::capturedImage,
                    { Log.e("kilo", "Failed in camera") })
            } else {
                CaptureImage()
            }
        }


        outputDir = getOutputDir()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

    @Composable
    fun CaptureImage(){
        Column (
            Modifier.fillMaxSize().padding(0.dp, 50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {
            Button(onClick = {
                requestCameraPermission()
            }){
                Text("Capture Image")
            }

            if(showImage.value){
                Image(
                    painter = rememberImagePainter(imageUri),
                    contentDescription = "image taken",
                    modifier = Modifier.fillMaxSize())
            }


        }
    }

    private val requestPerissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            isGranted -> showCamera.value = isGranted
    }

    private fun requestCameraPermission(){
        when {
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
                showCamera.value = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) -> {

            }

            else -> requestPerissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun capturedImage(uri : Uri){
        showCamera.value = false
        showImage.value = true
        imageUri = uri
    }

    private fun getOutputDir(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let{
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

}

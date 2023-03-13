package com.xxmrk888ytxx.customruntimepermission

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.xxmrk888ytxx.customruntimepermission.ui.theme.CustomRuntimePermissionTheme
import com.xxmrk888ytxx.sendexobroadcastusecase.SendExoBroadcastUseCase

class MainActivity : ComponentActivity() {

    private val sendExoBroadcastUseCase by lazy {
        SendExoBroadcastUseCase(this.applicationContext)
    }

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val text = rememberSaveable { mutableStateOf("") }
            val permissionState = rememberPermissionState("com.xxmrk888ytxx.SEND_EXO_MESSAGE")

            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = {
                    if(permissionState.status != PermissionStatus.Granted) {
                        permissionState.launchPermissionRequest()
                    } else {
                        Toast.makeText(this@MainActivity, "Permission already granted", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text(text = "Request permission")
                }

                OutlinedTextField(value = text.value, onValueChange = {
                    text.value = it
                })

                Button(onClick = { sendExoBroadcastUseCase.execute(text.value) }) {
                    Text(text = "Send")
                }
            }
        }
    }
}
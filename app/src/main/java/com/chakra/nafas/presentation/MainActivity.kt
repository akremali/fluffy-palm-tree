package com.chakra.nafas.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chakra.nafas.data.model.Chakra
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                ChakraApp()
            }
        }
    }
}

@Composable
fun ChakraApp() {
    var selectedChakra by remember { mutableStateOf<Chakra?>(null) }
    var isSessionActive by remember { mutableStateOf(false) }
    var timeLeft by remember { mutableStateOf(120) } // 2 دقائق
    
    LaunchedEffect(isSessionActive) {
        if (isSessionActive) {
            while (timeLeft > 0) {
                delay(1000)
                timeLeft--
            }
            isSessionActive = false
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!isSessionActive) {
            Text("اختر التشاكرا", fontSize = 28.sp)
            
            Spacer(modifier = Modifier.height(20.dp))
            
            Chakra.values().forEach { chakra ->
                Button(
                    onClick = { selectedChakra = chakra },
                    colors = ButtonDefaults.buttonColors(containerColor = chakra.color),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text("${chakra.arabicName} (${chakra.frequency}Hz)", color = Color.Black)
                }
            }
            
            Spacer(modifier = Modifier.height(20.dp))
            
            if (selectedChakra != null) {
                Button(
                    onClick = { 
                        isSessionActive = true
                        timeLeft = 120
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("بدء الجلسة (دقيقتان)")
                }
            }
        } else {
            selectedChakra?.let { chakra ->
                Text(
                    text = chakra.arabicName,
                    fontSize = 32.sp,
                    color = chakra.color
                )
                Text("${chakra.frequency} هرتز", fontSize = 20.sp)
                
                Spacer(modifier = Modifier.height(30.dp))
                
                Text(
                    text = "الوقت المتبقي: ${timeLeft / 60}:${String.format("%02d", timeLeft % 60)}",
                    fontSize = 24.sp
                )
                
                Spacer(modifier = Modifier.height(20.dp))
                
                Button(
                    onClick = { 
                        isSessionActive = false
                        timeLeft = 120
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                ) {
                    Text("إنهاء الجلسة")
                }
            }
        }
    }
}

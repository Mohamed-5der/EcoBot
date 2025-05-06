package com.khedr.ecobot.ui.presentaion.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khedr.ecobot.R

class SettingsScreen :Screen {
    @Composable
    override fun Content() {
        SettingsContent()
    }
     @Composable
     fun SettingsContent(){
         RobotStatusScreen(
             batteryLevel = 60,
             binFillLevel = 80,
             robotStatus = "Idle",
             isConnected = true,
             lastUpdated = "2 minutes ago",{

             }
         )

     }


    @Composable
    fun RobotStatusScreen(
        batteryLevel: Int,
        binFillLevel: Int,
        robotStatus: String,
        isConnected: Boolean,
        lastUpdated: String,
        onRefresh: () -> Unit
    ) {
        val batteryColor = when {
            batteryLevel > 50 -> Color(0xFF4CAF50) // Green
            batteryLevel > 20 -> Color(0xFFFFC107) // Amber
            else -> Color(0xFFF44336)             // Red
        }

        val binColor = if (binFillLevel < 70) Color(0xFF4CAF50) else Color(0xFFF44336)


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🧹 Trash Collection Robot",
                fontSize = 24.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(36.dp))

            // Battery Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("🔋 Battery Level", fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(4.dp))

                    LinearProgressIndicator(
                        progress = batteryLevel / 100f,
                        color = batteryColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                    Text("$batteryLevel%", modifier = Modifier.align(Alignment.End))
                }
            }

            // Bin Fill Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("🗑️ Bin Fill Level", fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(4.dp))
                    LinearProgressIndicator(
                        progress = binFillLevel / 100f,
                        color = binColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                    )
                    Text("$binFillLevel%", modifier = Modifier.align(Alignment.End))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            // Status & Connection
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("⚙️ Current Status", fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(robotStatus)
                }
                Column {
                    Text("📶 Connection", fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (isConnected) "Connected ✅" else "Disconnected ❌",
                        color = if (isConnected) Color(0xFF4CAF50) else Color(0xFFF44336)
                    )
                }
            }
            Spacer(modifier = Modifier.height(36.dp))

            Button(
                onClick = {


                },
                modifier = Modifier
                    .height(44.dp).align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(8.dp),
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.app_color)
                )
            ) {
                Icon(Icons.Default.Refresh, contentDescription = "Refresh")

                Text(text = "Refresh Status",
                    modifier = Modifier,textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.white),)
            }
            Text("🕓 Last updated: $lastUpdated", fontSize = 12.sp, color = Color.Gray)


        }
    }

}
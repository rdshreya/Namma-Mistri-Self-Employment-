package com.example.nammamistri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialCalculatorScreen()
        }
    }
}

fun calculateMaterials(
    length: Double,
    height: Double,
    thickness: Double
): Triple<Int, Int, Double> {

    val volume = length * height * thickness

    val bricks = (volume * 500).toInt()

    val cementBags = (volume / 0.035).toInt()

    val sand = volume * 0.3

    return Triple(bricks, cementBags, sand)
}

@Composable
fun MaterialCalculatorScreen() {

    var length by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var thickness by remember { mutableStateOf("") }

    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        OutlinedTextField(
            value = length,
            onValueChange = { length = it },
            label = { Text("Length") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = height,
            onValueChange = { height = it },
            label = { Text("Height") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = thickness,
            onValueChange = { thickness = it },
            label = { Text("Thickness") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {

            val data = calculateMaterials(
                length.toDouble(),
                height.toDouble(),
                thickness.toDouble()
            )

            result =
                "Bricks: ${data.first}\nCement Bags: ${data.second}\nSand: ${data.third}"

        }) {

            Text("Calculate")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(result)
    }
}
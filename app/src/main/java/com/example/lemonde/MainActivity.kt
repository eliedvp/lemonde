package com.example.lemonde

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonde.ui.theme.LemondeTheme
import kotlin.random.Random
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemondeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {

    val currentStep = remember { mutableStateOf(0) }

    val requiredPresses = remember { mutableStateOf(Random.nextInt(2, 5)) }

    val pressesCount = remember { mutableStateOf(0) }



    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.Yellow)



    ) {
        Text(
            text = "Lemonde",
            modifier = Modifier.align(Alignment.Center)
                .padding(16.dp),
            fontSize = 22.sp,
            color = Color.Black  ,
            fontWeight = FontWeight.Bold

        )
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {


            when (currentStep.value) {
                0 -> {
                    // Étape 1 : Afficher le citronnier
                    Image(
                        painter = painterResource(id = R.drawable.lemon_tree),
                        contentDescription = "Lemon Tree",
                        modifier = Modifier
                            .clickable {
                                currentStep.value = 1
                            }
                    )
                    Text(
                        text = "Tap the lemon tree to select a lemon",
                        modifier = Modifier.padding(16.dp)
                    )
                }
                1 -> {
                    // Étape 2 : Afficher le citron
                    Image(
                        painter = painterResource(id = R.drawable.lemon_squeeze),
                        contentDescription = "Keep tapping the lemon to squeeze it",
                        modifier = Modifier
                            .clickable {
                                pressesCount.value += 1
                                if (pressesCount.value >= requiredPresses.value) {
                                    currentStep.value = 2
                                }
                            }
                    )
                    Text(
                        text = "Keep tapping the lemon to squeeze it",
                        modifier = Modifier.padding(16.dp)
                    )
                }
                2 -> {
                    // Étape 3 : Afficher le verre de citronnade
                    Image(
                        painter = painterResource(id = R.drawable.lemon_drink),
                        contentDescription = "Lemonade",
                        modifier = Modifier
                            .clickable {
                                currentStep.value = 3
                            }
                    )
                    Text(
                        text = "Tap the lemonade to drink it",
                        modifier = Modifier.padding(16.dp)
                    )
                }
                3 -> {
                    // Étape 4 : Afficher le verre vide
                    Image(
                        painter = painterResource(id = R.drawable.lemon_restart),
                        contentDescription = "Empty Glass",
                        modifier = Modifier
                            .clickable {

                                currentStep.value = 0
                                requiredPresses.value = Random.nextInt(2, 5)
                                pressesCount.value = 0
                            }
                    )
                    Text(
                        text = "Tap the empty glass to start again",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemondeTheme {
        LemonadeApp()
    }
}

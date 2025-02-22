package com.jetpackcompose.dayone

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpackcompose.dayone.ui.theme.DayOneTheme
import kotlin.math.exp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DayOneTheme {
                Myapp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Myapp(modifier: Modifier=Modifier){
    var showScreen by rememberSaveable{ mutableStateOf(true) }
    Surface() {
        if(showScreen){
            ShowSplashScreen(onClicked = {showScreen=false})
        }else{
            Greetings()
        }
    }

}
@Composable
fun Greetings(
    modifier: Modifier=Modifier,
    names:List<String> = List(20){"$it"}
){
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }

}

@Composable
fun Greeting(name: String,modifier: Modifier = Modifier) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 5.dp, horizontal = 8.dp)
    ){
        CardContent(name)
    }
}



@Composable
fun CardContent(name: String) {
    var expand by rememberSaveable {
        mutableStateOf(false)
    }
    Surface(color = MaterialTheme.colorScheme.primary) {
        Row (modifier = Modifier
            .padding(26.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )){
            Column (modifier = Modifier.weight(1f)
                .padding(12.dp)){
                Text("Hello")
                Text(name, style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
                if(expand){
                    Text("hello guys today i learned so many new things , and this is day 1 of my official jetpack compose journy.", modifier = Modifier.padding(vertical = 5.dp))
                }
            }
            IconButton(onClick = {expand= !expand}) {
                Icon( imageVector = if(expand) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore , contentDescription =if(expand) "Show less" else "Show more" )
            }
        }


    }

}

@Preview(showBackground = true,
    name="greet")
@Composable
fun GreetingPreview() {
    DayOneTheme {
        Myapp(Modifier.fillMaxSize())
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Preview(showBackground = true,
    name="greet1")
@Composable
fun GreetingsPreview() {
    DayOneTheme {
        Greetings()
    }
}



@Composable
fun ShowSplashScreen(modifier: Modifier=Modifier,
                     onClicked:()->Unit){

    Column (
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text("Welcome to my App")
        Button(onClick = onClicked,
            modifier=Modifier.padding(vertical = 24.dp)) {
            Text("Continue")
        }

    }
}

@Preview(showBackground = true,widthDp = 320, heightDp = 320)
@Composable
fun ShowSplashScreenPreview(){
    DayOneTheme {
        ShowSplashScreen(onClicked = {})
    }
}
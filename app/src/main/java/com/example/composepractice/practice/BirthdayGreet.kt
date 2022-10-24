package com.example.composepractice.practice

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composepractice.R
import com.example.composepractice.ui.theme.ComposeGreen

@Composable
fun BirthdayGreet() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = BirthdayScreen.GetNameScreen.route) {
        composable(route = BirthdayScreen.GetNameScreen.route) {
            GetName(navController)
        }
        composable(route = BirthdayScreen.WishBirthdayScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            )) { entry ->
            WishBirthday(entry.arguments?.getString("name") ?: "")
        }
    }
}

sealed class BirthdayScreen(val route: String) {
    object GetNameScreen : BirthdayScreen("get_name")
    object WishBirthdayScreen : BirthdayScreen("wish_birthday")

    fun withArgs(vararg args: String): String = buildString {
        append(route)
        args.forEach {
            append("/$it")
        }
    }

}


@Composable
fun GetName(navController: NavController) {
    var name by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        TextField(value = name, onValueChange = { name = it }, label = {
            Text("Name")
        }, maxLines = 1, singleLine = true, modifier = Modifier.fillMaxWidth(0.8f))

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            if (name.isBlank()) {
                Toast.makeText(context, "Enter a name !", Toast.LENGTH_SHORT).show()
            } else {
                navController.navigate(BirthdayScreen.WishBirthdayScreen.withArgs(name))
            }
        }, modifier = Modifier.fillMaxWidth(0.8f)) {
            Text("Submit")
        }

    }
}

@Composable
fun WishBirthday(name: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.cake),
            contentDescription = "cake",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Text(
            text = "Happy Birthday $name !",
            fontSize = 44.sp,
            fontFamily = FontFamily.Cursive,
            fontWeight = FontWeight.Bold, color = ComposeGreen,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp),
        )
    }
}
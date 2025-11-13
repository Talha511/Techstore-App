package com.example.guidesign.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.guidesign.R
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.navigation.compose.rememberNavController

@SuppressLint("ConfigurationScreenWidthHeight", "UnusedBoxWithConstraintsScope")
@Composable
fun LoginScreen(navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    configuration.screenHeightDp.dp
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White,
                        colorResource(id = R.color.alto)
                    )
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        val imageSize = screenWidth * 0.5f
        val buttonWidth = screenWidth * 0.8f
        val titleFontSize = (screenWidth.value / 12).sp

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(screenWidth * 0.13f)
                        .border(
                            1.dp,
                            color = colorResource(id = R.color.alto),
                            RoundedCornerShape(12.dp)
                        )
                        .clickable {
                            navController.popBackStack(
                                route = "SplashScreen",
                                inclusive = false
                            )
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrowback),
                        contentDescription = "Back Icon",
                        modifier = Modifier
                            .size(screenWidth * 0.06f),
                        tint = Color.Black
                    )
                }

                Box(
                    modifier = Modifier
                        .size(screenWidth * 0.13f)
                        .border(
                            1.dp,
                            color = colorResource(id = R.color.alto),
                            RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = "Profile",
                        modifier = Modifier.size(screenWidth * 0.07f),
                        tint = colorResource(id = R.color.azure)
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = "Login Image",
                modifier = Modifier
                    .size(imageSize)
                    .padding(bottom = 8.dp)
            )

            Text(
                text = "Welcome To Login",
                fontSize = titleFontSize,
                fontFamily = FontFamily(Font(R.font.abri_regular, FontWeight.Bold)),
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Login To Your Account",
                fontSize = (screenWidth.value / 25).sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    Log.i("Credential", "Email: $email, Password: $password")
                },
                modifier = Modifier
                    .width(buttonWidth)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.azure),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Login",
                    fontSize = (screenWidth.value / 25).sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Forgot Password
            Text(
                text = "Forgot Password?",
                color = colorResource(id = R.color.azure),
                modifier = Modifier.clickable { },
                fontWeight = FontWeight.Medium,
                fontSize = (screenWidth.value / 28).sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Or Sign In With",
                fontSize = (screenWidth.value / 28).sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SocialLoginIcon(R.drawable.fb, screenWidth)
                SocialLoginIcon(R.drawable.go, screenWidth)
                SocialLoginIcon(R.drawable.tw, screenWidth)
            }

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun SocialLoginIcon(iconRes: Int, screenWidth: Dp) {
    Image(
        painter = painterResource(id = iconRes),
        contentDescription = null,
        modifier = Modifier
            .size(screenWidth * 0.1f)
            .clickable { }
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}














































//@Composable
//fun LoginScreen(navController: NavHostController) {
//    var email by remember{ mutableStateOf("")
//    }
//    var password by remember { mutableStateOf("")
//    }
//    Column(
//        modifier = Modifier.fillMaxSize()
//            .background(
//                brush = Brush.verticalGradient(
//                    colors = listOf(
//                        Color.White, // top (light)
//                        colorResource(id = R.color.alto) // bottom (dark #ebebeb)
//                    )
//                )
//            ),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    )
//    {
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(top = 30.dp, bottom = 24.dp, start = 4.dp, end = 4.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Box(
//                modifier = Modifier
//                    .size(55.dp)
//                    .border(
//                        1.dp,
//                        color = colorResource(id = R.color.alto),
//                        RoundedCornerShape(12.dp)
//                    ),
//                contentAlignment = Alignment.Center
//            )
//            {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_arrowback),
//                    contentDescription = "Back Icon",
//                    modifier = Modifier.size(24.dp)
//                        .clickable {
//                            navController.popBackStack(
//                                route = "ProductScreen",
//                                inclusive = false
//                            )
//                        },
//
//                    tint = Color.Black
//                )
//            }
//            Box(
//                modifier = Modifier
//                    .size(55.dp)
//                    .border(
//                        1.dp,
//                        color = colorResource(id = R.color.alto),
//                        RoundedCornerShape(12.dp)
//                    ),
//                contentAlignment = Alignment.Center
//
//            )
//            {
//                Icon(
//                    painter = painterResource(id = R.drawable.ic_bookmark),
//                    contentDescription = "Profile",
//                    modifier = Modifier.size(35.dp),
//                    tint = colorResource(R.color.azure)
//                )
//            }
//        }
//        // Add a Image
//        Image(painter = painterResource(id = R.drawable.login), contentDescription = "Login Image",
//            modifier = Modifier.size(250.dp))
//
//        //Add Text with Format Changing & Import Font Family
//        Text(
//            text ="Welcome To Login" ,//context.add(2,3).toString(),
//            fontSize = 35.sp,
//            fontFamily = FontFamily(Font(R.font.abri_regular,FontWeight.Bold))
//        )
//
//        // Add Space Between
//        Spacer(modifier = Modifier.height(10.dp))
//
//        // Add a Text
//        Text(text = "Login To Your Account")
//
//        // Add Space Between
//        Spacer(modifier = Modifier.height(4.dp))
//
//        //Add Text Field of Email Address with Styling and Label
//        OutlinedTextField(value = email, onValueChange = { email = it } , label = {
//            Text(text = "Email Address")
//        })
//
//        // Add Space Between
//        Spacer(modifier = Modifier.height(2.dp))
//
//        //Add Text Field of Password with Styling and Label with value passing
//        OutlinedTextField(value = password, onValueChange = {password = it} , label = {
//            Text(text = "Password")
//        }, visualTransformation = PasswordVisualTransformation())
//
//        // Add Space Between
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Add Login Button with OnClick Function with Styling
//        Button(
//            onClick = {
//                Log.i("Credential", "Email: $email Password: $password")
//            },
//            colors = ButtonDefaults.buttonColors(
//                containerColor = colorResource(id = R.color.azure),   // Background color
//                contentColor = Color.White                            // Text/Icon color
//            )
//        ) {
//            Text(text = "Login")
//        }
//
//        // Add Space Between
//        Spacer(modifier = Modifier.height(10.dp))
//
//        //Text Clickable Forgot Password
//        Text("Forgot Password ? ", modifier = Modifier.clickable{
//
//        })
//
//        // Add Space Between
//        Spacer(modifier = Modifier.height(40.dp))
//
//        // Add Signing In
//        Text("Or Signing With", modifier = Modifier.clickable{
//        })
//
//        //Create a Row with horizontal Pattern and Attach logo in Horizontal Sequence
//        Row (
//            modifier = Modifier.fillMaxWidth().padding(20.dp),
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ){
//            // Add a Clickable Link Facebook
//            Image(painter = painterResource(id = R.drawable.fb), contentDescription = "Facebook Logo",
//                modifier = Modifier.size(40.dp).clickable{
//                }
//            )
//            // Add a Clickable Link Google
//            Image(painter = painterResource(id = R.drawable.go), contentDescription = "Google Logo",
//                modifier = Modifier.size(40.dp).clickable{
//                }
//            )
//            // Add a Clickable Link Twitter
//            Image(painter = painterResource(id = R.drawable.tw), contentDescription = "Twitter Logo",
//                modifier = Modifier.size(40.dp).clickable{
//                }
//            )
//        }
//
//    }
//
//}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen(navController =rememberNavController())
//}
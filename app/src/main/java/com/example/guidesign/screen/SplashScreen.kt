package com.example.guidesign.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guidesign.R

@Composable
fun SplashScreen(
    onclickPro: () -> Unit,
    onclickAuth: () -> Unit
) {
    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState) // Scrollable column
                .padding(40.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // UPPER CONTENT
            Column {
                Text(
                    "W F H SHOP",
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    "Everything you need for your home office",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.sf_pro_display, FontWeight.Bold))
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    "Improve your work from home experience with our vast selection of amazing tools to help you work like a professional.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    fontSize = 18.sp,
                    maxLines = 3,
                    lineHeight = 35.sp,
                    fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght))
                )
                Spacer(modifier = Modifier.height(25.dp))

                // Buttons Row
                Row {
                    Button(
                        onClick = { onclickPro() },
                        modifier = Modifier.width(160.dp),
                        shape = RoundedCornerShape(15.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(id = R.color.azure),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            "Explore ➔",
                            modifier = Modifier.padding(12.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght))
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    OutlinedButton(
                        onClick = { onclickAuth() },
                        modifier = Modifier.width(145.dp),
                        border = BorderStroke(1.dp, color = colorResource(id = R.color.alto)),
                        shape = RoundedCornerShape(15.dp)
                    ) {
                        Text(
                            "or Log in",
                            modifier = Modifier.padding(12.dp),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.inter_variablefont_opsz_wght)),
                            color = Color.Gray
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.imm),
                contentDescription = "Character",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(500.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(
        onclickPro = {},
        onclickAuth = {})
}

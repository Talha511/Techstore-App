package com.example.guidesign.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.guidesign.ModelClasses.Discount
import com.example.guidesign.R

@Composable
fun ProductDetailScreenLayout(
   discount: Discount,
   onCLickNav:()->Unit
) {
    var selectedColor by remember { mutableStateOf("Graphite") }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color.White, colorResource(id = R.color.alto))
                )
            )
            .verticalScroll(scrollState)
            .padding(horizontal = 5.dp, vertical = 10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 12.dp, end = 12.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                       onCLickNav()
                    }
                    .border(1.dp, colorResource(id = R.color.alto), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrowback),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(26.dp),
                    tint = Color.Black
                )
            }
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .border(1.dp, colorResource(id = R.color.alto), RoundedCornerShape(12.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = "Bookmark",
                    modifier = Modifier.size(30.dp),
                    tint = colorResource(R.color.azure)
                )
            }
        }

        Image(
            painter = painterResource(id = discount.img),
            contentDescription = "Product Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .padding(horizontal = 20.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 26.dp, vertical = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Text(
                            text = discount.name,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = 28.sp
                        )
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(color = Color.Gray, fontWeight = FontWeight.Normal)
                                ) { append("by ") }
                                withStyle(
                                    style = SpanStyle(
                                        fontWeight = FontWeight.ExtraBold,
                                        fontFamily = FontFamily(Font(R.font.inter_semibold)),
                                        color = Color.Black
                                    )
                                ) { append("Logitech") }
                            },
                            fontSize = 14.sp
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = discount.price,
                            fontSize = 26.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
                            color = colorResource(id = R.color.azure)
                        )
                        Text(
                            text = discount.dis.let { dis-> if (dis.contains("%")) dis else "$dis% Off!" },
                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
                            fontSize = 16.sp,
                            color = Color(0xFFFFB300)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "About",
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(10.dp))

                var expanded by remember { mutableStateOf(false) }
                val shortTextLimit = 150
                val fullText = discount.des
                val shortText = fullText.take(shortTextLimit)

                Column {
                    Text(
                        text = if (expanded) fullText else shortText,
                        maxLines = if (expanded) Int.MAX_VALUE else 3,
                        overflow = TextOverflow.Ellipsis,
                        lineHeight = 26.sp,
                        textAlign = TextAlign.Justify,
                        color = Color.Gray,
                        fontSize = 16.sp
                    )

                    if (fullText.length > shortTextLimit) {
                        Text(
                            text = if (expanded) "Show less" else "Read more",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Justify,
                            modifier = Modifier
                                .padding(top = 8.dp)
                                .clickable { expanded = !expanded }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                                    color = Color.Black
                                )
                            ) { append("Graphite -") }
                            withStyle(
                                style = SpanStyle(color = Color.Gray, fontWeight = FontWeight.Normal)
                            ) { append(" Color") }
                        },
                        fontSize = 16.sp
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .then(
                                    if (selectedColor == "Graphite")
                                        Modifier.border(2.dp, Color.Black, CircleShape)
                                    else Modifier
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(Color.DarkGray)
                                    .clickable { selectedColor = "Graphite" }
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .then(
                                    if (selectedColor == "Light Gray")
                                        Modifier.border(2.dp, Color.Gray, CircleShape)
                                    else Modifier
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(Color.LightGray)
                                    .clickable { selectedColor = "Light Gray" }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.azure))
                ) {
                    Text(
                        text = "Add to cart",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.ic_shoppingbag),
                        contentDescription = "Shopping icon",
                        tint = Color.White,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProductDetailScreen() {
    ProductDetailScreenLayout(
        discount = Discount(
            img = R.drawable.ap,
            name = "TALHA",
            price = "12",
            dis = "12",
            des = "Hello"
        ),
        onCLickNav = {}
    )
}

package com.example.guidesign.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.guidesign.R
import com.example.guidesign.ModelClasses.Category
import com.example.guidesign.ModelClasses.Discount
import com.example.guidesign.ui.theme.alto

val categoriesList = listOf(
    Category("Mice", R.drawable.ic_mouse, 12),
    Category("Keyboards", R.drawable.ic_keyboard, 14),
    Category("Mice", R.drawable.ic_mouse, 9),
    Category("Keyboards", R.drawable.ic_keyboard, 18)
)

val dislist = listOf(
    Discount(
        R.drawable.ap,
        "H390 Headset",
        "$31.00",
        "20",
        "Logitech H390 headset delivers unparalleled online communication with its USB headset featuring a noise-canceling mic, laser-tuned drivers, and in-line controls. Ideal for calls, gaming, and more, it prioritizes comfort with an adjustable headband and leatherette ear cushions. The 7.64 ft cable ensures flexibility during voice calls, Skype, webinars, and beyond. With a plug-and-play USB-A connection, compatibility with any PC or Mac is seamless. Experience the perfect fusion of functionality and comfort with Logitech H390 – your ultimate choice for crystal-clear online communication."
    ),
    Discount(
        R.drawable.mo,
        "MX Master 3S",
        "$97.00",
        "15",
        "Unveiling the Logitech MX Master 3S Wireless Performance Mouse featuring Quiet Clicks – a revolutionary innovation that maintains the familiar click feel while minimizing noise. Enjoy a quieter experience with 90 Percent less click noise compared to its predecessor, the MX Master 3. The MX Master 3S sets a new standard with a remarkable 90 percent reduction in Sound Power Level for both left and right clicks, measured at 1m."),
    Discount(
        R.drawable.spe,
        "Z313 2.1 Speaker",
        "$52.00",
        "30",
        "This 2.1 speaker system delivers balanced acoustics and provides enhanced bass from a compact subwoofer. Connect any device via the 3.5mm input and easily access power and volume using the wired control pod."),
    Discount(
        R.drawable.wc,
        "C922 Pro Stream",
        "$71.00",
        "10",
        "Connect with superior clarity every time you go live on channels like Twitch and YouTube. Stream anything you want in your choice of Full 1080p at 30fps or hyper fast HD 720p at 60fps. Broadcast masterfully with reliable no-drop audio, autofocus, and a 78-degree field of view. Includes free 3-month premium XSplit license.Stream and record vibrant, true-to-life video. The glass lens and full HD 1080p captures the most exciting details, bright and natural colors in fluid video at 30fps, while the 78-degree field of view accommodates up to two people. You can use the app to zoom and pan the camera.")
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(onClickItem : (Discount) -> Unit ,navController : NavHostController) {

    var searchQuery by remember { mutableStateOf("") }
    val filteredProducts = dislist.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.des.contains(searchQuery, ignoreCase = true)
    }
    val filteredCategories = categoriesList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(Color.White, colorResource(id = R.color.alto))
                    )
                )
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 26.dp, vertical = 20.dp)
        ) {
            TopAppBarSection()
            Spacer(Modifier.height(35.dp))
            Text(
                text = "Explore products",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
            Spacer(Modifier.height(20.dp))

            SearchBarSection(searchQuery = searchQuery, onQueryChange = { searchQuery = it })

            Spacer(Modifier.height(40.dp))

            ProductCardSection(onClickItem = {ait->onClickItem(ait)}, filteredProducts, searchQuery)

            Spacer(Modifier.height(50.dp))

            CategoriesSection(filteredCategories, searchQuery)

            Spacer(Modifier.height(70.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarSection(searchQuery: String, onQueryChange: (String) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = searchQuery,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(1f)
                .height(52.dp)
                .clip(RoundedCornerShape(14.dp)),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = "Search",
                    tint = Color.Gray
                )
            },
            placeholder = { Text("Search products or categories...", color = Color.Gray) },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            singleLine = true
        )

        Spacer(Modifier.width(12.dp))

        Box(
            modifier = Modifier
                .size(52.dp)
                .background(colorResource(id = R.color.azure), RoundedCornerShape(14.dp))
                .clip(RoundedCornerShape(14.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_sliderstoggle),
                contentDescription = "Filter",
                modifier = Modifier.size(22.dp),
                tint = Color.White
            )
        }
    }
}

@Composable
fun ProductCardSection(onClickItem: (Discount)->Unit, list: List<Discount>, query: String) {
    Column {
        Text(
            text = "On discount 🔥",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 15.dp)
        )

        if (list.isEmpty() && query.isNotEmpty()) {
            Text(
                text = "No matching products found.",
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
            )
        } else {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(22.dp),
                contentPadding = PaddingValues(horizontal = 9.dp)
            ) {
                items(list) {
                    DiscountCard(onClickItem = {its-> onClickItem(its) }, it)
                }
            }
        }
    }
}
//                navController.navigate(
//                    "Details?name=${discount.name}&des=${discount.des}&dis=${discount.dis}&price=${discount.price}&img=${discount.img}"
//                )
@Composable
fun DiscountCard(onClickItem: (Discount) -> Unit, discount: Discount) {
    Card(
        modifier = Modifier
            .width(155.dp)
            .clickable { onClickItem(discount) },
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = discount.img),
                contentDescription = discount.name,
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = discount.name,
                style = MaterialTheme.typography.headlineSmall,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = discount.price,
                    fontSize = 14.sp,
                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                    color = colorResource(id = R.color.azure),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "${discount.dis}% Off!",
                    fontSize = 13.sp,
                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                    color = Color(0xFFFFA500),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun CategoriesSection(filteredCategories: List<Category>, query: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Categories",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        if (filteredCategories.isEmpty() && query.isNotEmpty()) {
            Text(
                text = "No matching categories found.",
                color = Color.Gray,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 10.dp, top = 10.dp)
            )
        } else {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(horizontal = 4.dp)
            ) {
                items(filteredCategories) { category ->
                    CategoryItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = category.icon),
                                contentDescription = category.name
                            )
                        },
                        name = category.name,
                        itemCount = category.count
                    )
                }
            }
        }
    }
}
@Composable
fun CategoryItem(
    icon: @Composable () -> Unit,
    name: String,
    itemCount: Int
) {
    Card(
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(colorResource(id = R.color.gallery)),
                contentAlignment = Alignment.Center
            ) {
                icon()
            }
            Spacer(Modifier.width(10.dp))
            Column {
                Text(name, fontWeight = FontWeight.SemiBold)
                Text("$itemCount new", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
        }
    }
}

@Composable
fun TopAppBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .border(1.dp, alto, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_hamburgerp),
                contentDescription = "Menu",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
        }

        Box(
            modifier = Modifier
                .size(50.dp)
                .border(1.dp, alto, RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.mm),
                contentDescription = "Profile",
                modifier = Modifier.size(32.dp),
                tint = Color.Unspecified
            )
        }
    }
}
@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar(
        containerColor = colorResource(id = R.color.alto),
        tonalElevation = 0.dp
    ) {
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.ic_home), "Home") },
            selected = true,
            onClick = { navController.navigate("SplashScreen") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = colorResource(id = R.color.azure),
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.ic_view_cozy), "View") },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = colorResource(id = R.color.silver),
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.ic_bookmarkfilled), "Bookmark") },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = colorResource(id = R.color.silver),
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.ic_notificationbell), "Bell") },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = colorResource(id = R.color.silver),
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            icon = { Icon(painterResource(id = R.drawable.ic_shoppingbag), "Shopping") },
            selected = false,
            onClick = {},
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = colorResource(id = R.color.silver),
                unselectedIconColor = Color.Gray,
                indicatorColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProductScreenPreview() {
    ProductScreen(onClickItem = {}, navController = rememberNavController())
}

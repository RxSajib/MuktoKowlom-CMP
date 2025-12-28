package com.aliad.muktokowlom.ui.screen.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.aliad.muktokowlom.ui.navigation.AppDestination
import com.aliad.muktokowlom.ui.screen.component.HeightGap
import com.aliad.muktokowlom.ui.screen.component.MyCustomAppBar
import io.ktor.http.HttpHeaders.Destination
import muktokowlomcmp.composeapp.generated.resources.Res
import muktokowlomcmp.composeapp.generated.resources.explore_all_category
import muktokowlomcmp.composeapp.generated.resources.muktokowlom
import muktokowlomcmp.composeapp.generated.resources.view_all
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeScreen(backStack: NavBackStack<NavKey>) {
    Scaffold(
        topBar = {
            MyCustomAppBar(
                isActonButtonEnable = true,
                isBackButtonEnable = false,
                title = stringResource(Res.string.muktokowlom),
                homeHeaderEnable = true,
                onBackPress = {}
            )
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            HeightGap(15.dp)
            LazyRow(modifier = Modifier.fillMaxWidth()) {
               // Box(modifier = Modifier.height(100.dp))
            }
            HeightGap(15.dp)
            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = stringResource(Res.string.explore_all_category),
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                Text(
                    text = stringResource(Res.string.view_all),
                    modifier = Modifier.clickable{
                        backStack.add(AppDestination.CategoryScreen)
                    }
                )
            }
            HeightGap(15.dp)
        }
    }
}
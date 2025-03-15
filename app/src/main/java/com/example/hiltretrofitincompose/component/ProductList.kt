package com.example.hiltretrofitincompose.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.hiltretrofitincompose.model.Product
import com.example.hiltretrofitincompose.view_model.HiltRetrofitViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductList(
    products: List<Product>,
    navController: NavHostController
) {
    LazyColumn {
        items(items = products) {
            Card(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .clip(RoundedCornerShape(5.dp))
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row {
                    GlideImage(model = it.thumbnail,
                        contentDescription = it.title,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(
                                onClick = {
                                    navController.navigate("detail/product_id=${it.id}")
                                }
                            )
                            .align(Alignment.CenterVertically)
                            .border(
                                width = 2.dp,
                                color = MaterialTheme.colorScheme.inverseOnSurface,
                                shape = CircleShape
                            )
                    )
                    Column {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier
                                .padding(start = 10.dp, top = 10.dp)
                                .align(Alignment.Start),
                            color = MaterialTheme.colorScheme.primary,
                        )

                        Text(
                            text = "$ ${it.price.toString()}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.Start),
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }
    }
}
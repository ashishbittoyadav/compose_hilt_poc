package com.example.hiltretrofitincompose.component


import android.util.Log
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.hiltretrofitincompose.model.Product
import com.example.hiltretrofitincompose.view_model.HiltRetrofitViewModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductDetail(product: Product) {
    Row {
        GlideImage(model = product.thumbnail,
            contentDescription = product.title,
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        )
    }
}
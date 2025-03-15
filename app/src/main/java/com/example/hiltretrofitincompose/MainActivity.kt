package com.example.hiltretrofitincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hiltretrofitincompose.component.ProductDetail
import com.example.hiltretrofitincompose.component.ProductList
import com.example.hiltretrofitincompose.model.Product
import com.example.hiltretrofitincompose.ui.theme.HiltRetrofitInComposeTheme
import com.example.hiltretrofitincompose.view_model.HiltRetrofitViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.asStateFlow
import java.io.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val hiltRetrofitViewModel: HiltRetrofitViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            HiltRetrofitInComposeTheme {
                val productState = remember { mutableStateOf<List<Product>>(emptyList()) }
                LaunchedEffect(key1 = Unit) {
                    hiltRetrofitViewModel.products.asStateFlow().collect {
                        it.products?.let {
                            productState.value = it
                        }
                    }
                }
                NavHost(navController = navController, startDestination = "main") {

                    composable(route = "main") {
                        ProductList(products = productState.value, navController)
                    }

                    composable(route = "detail" + "/product_id={product_id}",
                        arguments = listOf(
                            navArgument("product_id") {
                                type = NavType.IntType
                            }
                        )
                    ) { backStackEntry ->
                        val id = backStackEntry.arguments?.getInt("product_id") ?: 0
                        val product = hiltRetrofitViewModel.products.value.products?.get(id)
                        if (product != null)
                            ProductDetail(product)
                        else
                            Text(text = "error while loading data....")
                    }
                }
            }
        }
    }
}


//sealed class Route(val route:String){
//    data object Main:Route("main_screen")
//    data object Detail:Route("detail_screen")
//}
package com.androidcourse.servicioapilab9ejercicio.Modulos

import com.androidcourse.servicioapilab9ejercicio.Interface.ProductApiService
import com.androidcourse.servicioapilab9ejercicio.ViewModel.ProductModel
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ScreenInicio() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Bienvenido a la Tienda", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun ScreenProductos(navController: NavHostController, servicio: ProductApiService) {
    var listaProductos by remember { mutableStateOf(emptyList<ProductModel>()) }

    LaunchedEffect(Unit) {
        val listado = servicio.getAllProducts().products
        listaProductos = listado
    }

    LazyColumn {
        items(listaProductos) { producto ->
            Row(modifier = Modifier.padding(8.dp)) {
                Text(text = producto.id.toString(), Modifier.weight(0.05f), textAlign = TextAlign.End)
                Spacer(Modifier.padding(horizontal = 1.dp))
                Text(text = producto.title, Modifier.weight(0.7f))
                IconButton(
                    onClick = {
                        navController.navigate("productoDetalle/${producto.id}")
                        Log.e("PRODUCTOS", "ID = ${producto.id}")
                    },
                    Modifier.weight(0.1f)
                ) {
                    Icon(imageVector = Icons.Outlined.ShoppingCart, contentDescription = "Ver Detalles")
                }
            }
        }
    }
}

@Composable
fun ScreenProductoDetalle(navController: NavHostController, servicio: ProductApiService, id: Int) {
    var producto by remember { mutableStateOf<ProductModel?>(null) }

    LaunchedEffect(id) {
        producto = servicio.getProductById(id)
    }

    Column(
        Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {
        if (producto != null) {
            OutlinedTextField(
                value = producto!!.id.toString(),
                onValueChange = {},
                label = { Text("ID") },
                readOnly = true
            )
            OutlinedTextField(
                value = producto!!.title,
                onValueChange = {},
                label = { Text("Título") },
                readOnly = true
            )
            OutlinedTextField(
                value = producto!!.description ?: "No disponible",
                onValueChange = {},
                label = { Text("Descripción") },
                readOnly = true
            )
            OutlinedTextField(
                value = producto!!.price.toString(),
                onValueChange = {},
                label = { Text("Precio") },
                readOnly = true
            )
        } else {
            Text(text = "Cargando producto...", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

package br.senai.sp.jandira.lazycolumnroom

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lazycolumnroom.components.ProductCard
import br.senai.sp.jandira.lazycolumnroom.dao.repository.ProductRepository
import br.senai.sp.jandira.lazycolumnroom.model.Product
import br.senai.sp.jandira.lazycolumnroom.ui.theme.LazyColumnRoomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LazyColumnRoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

fun items(count: List<Product>, itemContent: LazyItemScope.(index: Int) -> Unit) {

}

@Composable
fun Greeting() {

    var nameState by remember {
        mutableStateOf("")
    }
    var descriptonState by remember {
        mutableStateOf("")
    }
    var priceState by remember {
        mutableStateOf("")
    }

    var productState by remember {
        mutableStateOf(listOf<Product>())
    }

    val context = LocalContext
    val productRepository = ProductRepository(LocalContext.current)
    productState = productRepository.getProductsList()


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp)) {
        Text(text = "")
        Text(text = "")
        Text(text = "")

        OutlinedTextField(
                        value = nameState,
                        onValueChange = {nameState = it},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                        )
        OutlinedTextField(
            value = descriptonState,
            onValueChange = {descriptonState = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )
        OutlinedTextField(
            value = priceState,
            onValueChange = {priceState = it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )
        Button(onClick = {  val p = Product(productName = nameState, productDescription = descriptonState, productPrice = priceState.toDouble())
                            val newId = productRepository.save(p)
                            productState = productRepository.getProductsList()
                    //Toast.makeText(context, "$newId", Toast.LENGTH_LONG, )
        }) {
            Text(text = "Save")
        }


        LazyColumn(modifier = Modifier
            .padding(35.dp)
            .fillMaxWidth()){
                items(productState) {product ->
                    ProductCard(product = product)
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    LazyColumnRoomTheme {
        Greeting()
    }
}
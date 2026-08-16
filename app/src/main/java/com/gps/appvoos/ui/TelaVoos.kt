package com.gps.appvoos.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gps.appvoos.api.FlightApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun TelaVoos(
    modifier: Modifier = Modifier
){
    var resultado by remember {
        mutableStateOf("Nenhuma busca realizada")
    }

    val scope = rememberCoroutineScope()

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "AppVoos"
        )
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        Button(
            onClick = {
                scope.launch(Dispatchers.IO) {
                    try {
                        val api = FlightApi()
                        val resposta = api.buscarPrecos()

                        withContext(Dispatchers.Main) {
                            resultado = resposta
                        }
                    }catch (e: Exception){
                        withContext(Dispatchers.Main){
                            resultado = "Erro: ${e.message}"
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ){
            Text("Buscar preços")
        }
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        Text(
            text = resultado
        )
    }
}

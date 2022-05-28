package com.example.nomoneytrade.create


import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nomoneytrade.api.Api
import com.example.nomoneytrade.api.dto.ProductDto
import com.example.nomoneytrade.api.requests.ProductRequest
import com.example.nomoneytrade.entity.Product
import com.example.nomoneytrade.mvi.event.CreateProductEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class CreateProductViewModel @Inject constructor(private val api: Api) : ViewModel() {
    var imageFile: MultipartBody.Part? = null
    private val pickImageRequest = 71
    val event = MutableStateFlow<CreateProductEvent?>(CreateProductEvent.Loading)

    fun clickCreate(product: ProductRequest) {
        this.viewModelScope.launch {
            createProduct(product)
        }
    }

    fun chooseImage(interactionResult: ActivityResultLauncher<Intent>) {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.putExtra("requestCode", pickImageRequest)
        interactionResult.launch(Intent.createChooser(intent, "Select avatar"))
    }

    private suspend fun createProduct(product: ProductRequest) {
        event.value = CreateProductEvent.Loading

        val response = api.newProduct(product, imageFile)

        if (response.isSuccessful) {
            event.value = CreateProductEvent.Success
        } else {
            event.value = CreateProductEvent.Error
        }
    }
}
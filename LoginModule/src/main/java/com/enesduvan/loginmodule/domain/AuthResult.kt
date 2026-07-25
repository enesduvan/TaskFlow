package com.enesduvan.loginmodule.domain

//başarılı / başarısız
sealed class AuthResult {


    // İşlem başarılı olduğunda çalışır
    object Success : AuthResult()

    // İşlem başarısız olduğunda çalışır ve hatayı taşır
    data class Error(val message: String) : AuthResult()

    // İşlem devam ederken (yükleniyor) çalışır
    object Loading : AuthResult()
}
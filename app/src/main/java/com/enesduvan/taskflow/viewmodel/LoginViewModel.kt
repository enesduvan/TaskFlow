package com.enesduvan.taskflow.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    //şuanlık gerçek değil sahte giriş var
    val TrueMail = "test@gmail.com"
    val TruePassword = "123456"
    var emailState = mutableStateOf("")
    var passwordState = mutableStateOf("")
    var isPasswordVisible  = mutableStateOf(false)
    var errorMessage = mutableStateOf("")
    var isLoginSuccessful = mutableStateOf(false)
    var loggedInUser = mutableStateOf("")
    fun onEmailChange(newEmail: String) {
        emailState.value = newEmail
    }
    fun onPasswordChange(newPassword: String) {
        passwordState.value = newPassword
    }
    fun togglePasswordVisibility() {
        isPasswordVisible.value = !isPasswordVisible.value
    }
    fun loginControl() {
        val email = emailState.value
        val password = passwordState.value

        // 1. Kural: Boş alan kontrolü
        if (email.isBlank() || password.isBlank()) {
            errorMessage.value = "Lütfen tüm alanları doldurun."
            isLoginSuccessful.value = false
            return // Hata varsa kodu burada keser, aşağıya inmez!
        }

        // 2. Kural: Şifre uzunluk kontrolü (Döngü yerine .length kullandık)
        if (password.length < 6) {
            errorMessage.value = "Şifre en az 6 karakter olmalıdır."
            isLoginSuccessful.value = false
            return
        }

        // 3. Kural: Doğruluk kontrolü (Buraya geldiyse üstteki hatalar yok demektir)
        if (email == TrueMail && password == TruePassword) {
            errorMessage.value = "" // Hata mesajını temizle
            loggedInUser.value = email.substringBefore('@')
            isLoginSuccessful.value = true // Zili çaldık! UI bunu duyup ana sayfaya geçecek.
        } else {
            errorMessage.value = "E-posta veya şifre hatalı!"
            isLoginSuccessful.value = false
        }
    }
}
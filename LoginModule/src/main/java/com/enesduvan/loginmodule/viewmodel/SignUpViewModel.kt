package com.enesduvan.loginmodule.viewmodel

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enesduvan.loginmodule.data.AuthRepositoryImpl
import com.enesduvan.loginmodule.domain.AuthRepository
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {
    //şuanlık gerçek değil sahte giriş var
    var authRepositoryimpl: AuthRepository? = AuthRepositoryImpl()
    var nameState = mutableStateOf("")
    var emailState = mutableStateOf("")
    var passwordState = mutableStateOf("")
    var isPasswordVisible = mutableStateOf(false)
    var errorMessage = mutableStateOf("")
    var isLoginSuccessful = mutableStateOf(false)
    var loggedInUser = mutableStateOf("")
    var checkBoxTerms = mutableStateOf(false)
    var checkBoxPrivacy = mutableStateOf(false)
    val snackbarHostState =  SnackbarHostState()
    var confirmPasswordState =  mutableStateOf("")
    var isConfirmPasswordVisible =  mutableStateOf(false)





    fun onNameChange(newName: String) {
        nameState.value = newName
    }

    fun onEmailChange(newEmail: String) {
        emailState.value = newEmail
    }

    fun onPasswordChange(newPassword: String) {
        passwordState.value = newPassword
    }
    fun onConfirmPasswordChange(newPassword: String) {
        confirmPasswordState.value = newPassword
    }

    fun togglePasswordVisibility() {
        isPasswordVisible.value = !isPasswordVisible.value
    }

    fun toggleConfirmPasswordVisibility() {
        isConfirmPasswordVisible.value = !isConfirmPasswordVisible.value
    }

    fun singupControl() {
        val email = emailState.value
        val password = passwordState.value
        //önce isim sonra mail şifre daha sonra ise politika

        if (nameState.value.isBlank()) {
            errorMessage.value = "Lütfen adınızı girin."
            isLoginSuccessful.value = false
            return
        }
        if (email.isBlank() || password.isBlank()) {
            errorMessage.value = "Lütfen tüm alanları doldurun."
            isLoginSuccessful.value = false
            return // Hata varsa kodu burada keser, aşağıya inmez!
        }
        if (password.length < 6) {
            errorMessage.value = "Şifre en az 6 karakter olmalıdır."
            isLoginSuccessful.value = false
            return
        }
        if (!checkBoxTerms.value || !checkBoxPrivacy.value) {
            errorMessage.value = "Lütfen şartları ve gizlilik politikasını kabul edin."
            isLoginSuccessful.value = false
            return
        }


        // bütün ifler geçildi
        viewModelScope.launch {
            authRepositoryimpl?.signUp(emailState.value, passwordState.value)?.collect { result ->
                when (result) {
                    is com.enesduvan.loginmodule.domain.AuthResult.Loading -> {
                        // Yükleniyor durumu
                        isLoginSuccessful.value = false
                    }

                    is com.enesduvan.loginmodule.domain.AuthResult.Success -> {
                        // Başarılı giriş durumu
                        isLoginSuccessful.value = true
                        loggedInUser.value = emailState.value // Giriş yapan kullanıcıyı kaydet
                    }

                    is com.enesduvan.loginmodule.domain.AuthResult.Error -> {
                        // Hata durumu
                        errorMessage.value = result.message ?: "Bilinmeyen bir hata oluştu."
                        isLoginSuccessful.value = false
                    }
                }
            }

        }
    }

    fun googleLogin() {
        // Google giriş


    }
}
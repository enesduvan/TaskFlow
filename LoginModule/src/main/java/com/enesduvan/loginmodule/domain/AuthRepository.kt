package com.enesduvan.loginmodule.domain
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
fun login(email: String, password: String): Flow<AuthResult>
fun signUp(email: String, password: String): Flow<AuthResult>
}
/*Kullanıcı "Kayıt Ol" butonuna basar
         ↓
SinginScreen.kt (UI) → signupControl() çağırır
         ↓
SignUpViewModel.kt (Garson) → AuthRepositoryImpl'i çağırır
         ↓
AuthRepositoryImpl.kt (Mutfak) → Firebase'e createUserWithEmailAndPassword() gönderir
         ↓
Firebase yanıt verir → AuthResult (Loading / Success / Error) döner
         ↓
SignUpViewModel → durumu günceller
         ↓
SinginScreen → Loading spinner / Ana Ekran / Hata mesajı gösterir
*/
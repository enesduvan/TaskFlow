package com.enesduvan.loginmodule.data

import com.enesduvan.loginmodule.domain.AuthRepository
import com.enesduvan.loginmodule.domain.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

//firebase modül iletişim auth
class AuthRepositoryImpl : AuthRepository {
    override fun login(email: String, pass: String): Flow<AuthResult> = flow {
        var firebaseAuth: FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        // 1. Akışa ilk veriyi "emit" ediyoruz: Yükleniyor ekranı açılsın
        emit(AuthResult.Loading)

        try {
            // .await() sayesinde Listener eklememize gerek kalmıyor!
            // Kod Firebase yanıt verene kadar bu satırda duraklar (suspend), yanıt gelince alt satıra geçer.
            firebaseAuth.signInWithEmailAndPassword(email, pass).await()

            // 2. Başarılı olduysa akışa "Success" emit ediyoruz
            emit(AuthResult.Success)

        } catch (e: Exception) {
            // 3. Hata çıktıysa akışa "Error" emit ediyoruz
            emit(AuthResult.Error(e.localizedMessage ?: "Hata oluştu"))
        }
    }
    override fun signUp(email: String, pass: String): Flow<AuthResult> = flow {
        var firebaseAuth: FirebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        // 1. Akışa ilk veriyi "emit" ediyoruz: Yükleniyor ekranı açılsın
        emit(AuthResult.Loading)

        try {
            // .await() sayesinde Listener eklememize gerek kalmıyor!
            // Kod Firebase yanıt verene kadar bu satırda duraklar (suspend), yanıt gelince alt satıra geçer.
            firebaseAuth.createUserWithEmailAndPassword(email, pass).await()

            // 2. Başarılı olduysa akışa "Success" emit ediyoruz
            emit(AuthResult.Success)

        } catch (e: Exception) {
            // 3. Hata çıktıysa akışa "Error" emit ediyoruz
            emit(AuthResult.Error(e.localizedMessage ?: "Hata oluştu"))
        }
    }
}

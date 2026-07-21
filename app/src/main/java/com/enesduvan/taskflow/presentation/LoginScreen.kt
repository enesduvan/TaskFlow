package com.enesduvan.taskflow.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.enesduvan.taskflow.ui.theme.LoginBlack
import com.enesduvan.taskflow.ui.theme.LoginDarkPurple
import com.enesduvan.taskflow.ui.theme.LoginGray
import com.enesduvan.taskflow.ui.theme.LoginPurple
import com.enesduvan.taskflow.ui.theme.LoginWhite
import com.enesduvan.taskflow.ui.theme.TaskFlowTheme
import com.enesduvan.taskflow.viewmodel.LoginViewModel


@Composable
fun LoginScreen(viewModel: LoginViewModel = viewModel(),navController: NavController) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = viewModel.errorMessage.value) {
        // Eğer hata mesajı boş değilse (yani gerçekten bir hata varsa)
        if (viewModel.errorMessage.value.isNotEmpty()) {

            // Snackbar'ı ekranda göster (Asenkron işlem)
            snackbarHostState.showSnackbar(
                message = viewModel.errorMessage.value
            )

            // Gösterdikten hemen sonra state'i sıfırla ki kullanıcı
            // aynı hatayı tekrar yaparsa Snackbar tekrar çıkabilsin.
            viewModel.errorMessage.value = ""
        }

    }
    LaunchedEffect(key1 = viewModel.isLoginSuccessful.value) {
        if (viewModel.isLoginSuccessful.value) {
            navController.navigate("home_screen") {
                popUpTo("login_screen") { inclusive = true }
            }
        }
    }
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(LoginBlack).padding(paddingValues),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(100.dp), // İçindeki her şeyi dikeyde ortalar
                imageVector = Icons.Outlined.CheckCircle,
                contentDescription = "Onaylandı",
                tint = LoginPurple,
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "Tekarar Hoşgeldiniz", color = LoginWhite, fontSize = 32.sp
            )
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = LoginWhite, text = "Görevleriniz için giriş yapın veya kaydolun"
            )

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                //eklemeyi düşündüğüm bşr şey var ama şimdilik boş bırakıyorum o yüzden column içinde ekledim
                TextField(
                    value = viewModel.emailState.value,
                    placeholder = {
                        Text(
                            text = "name@gmail.com",
                            color = LoginWhite
                        )
                    }, //yer tutucu hint
                    shape = RoundedCornerShape(16.dp),        // shpae efektini ayarladım
                    colors = TextFieldDefaults.colors(
                        // Yazı rengi
                        focusedTextColor = LoginPurple,
                        unfocusedTextColor = LoginPurple,

                        // Arka plan rengi (Kutunun içi)
                        focusedContainerColor = LoginGray,
                        unfocusedContainerColor = LoginGray
                    ),
                    onValueChange = { email ->
                        viewModel.onEmailChange(email)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 0.dp, 0.dp),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Mail,
                            contentDescription = "Mail Icon",
                            tint = LoginPurple
                        )
                    }
                )

            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
                TextField(
                    value = viewModel.passwordState.value,
                    placeholder = { Text(text = "password", color = LoginWhite) },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        // Yazı rengi
                        focusedTextColor = LoginPurple,
                        unfocusedTextColor = LoginPurple,

                        // Arka plan rengi (Kutunun içi)
                        focusedContainerColor = LoginGray,
                        unfocusedContainerColor = LoginGray
                    ),
                    onValueChange = { password ->
                        viewModel.onPasswordChange(password)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp, 0.dp, 0.dp, 0.dp),

                    // Şifreyi gösterme veya gizleme durumuna göre görsel dönüşümü ayarlıyoruz
                    visualTransformation = if (viewModel.isPasswordVisible.value) VisualTransformation.None
                    else PasswordVisualTransformation(),

                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Key,
                            contentDescription = "Password Icon",
                            tint = LoginPurple
                        )
                    },
                    trailingIcon = {
                        Icon(
                            // 4. Duruma göre göz ikonunu değiştiriyoruz:
                            imageVector = if (viewModel.isPasswordVisible.value) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                            contentDescription = "Password Visible Icon",
                            tint = LoginPurple,
                            modifier = Modifier.clickable {
                                //şifreyi gösterme şaunlık için sadece ikon var ama şifreyi gösterme özelliği yok
                                viewModel.togglePasswordVisibility()
                            }
                        )
                    }
                )
                Text(
                    text = "Şifremi Unuttum",
                    color = LoginPurple,
                    modifier = Modifier
                        .align(Alignment.End)
                        .clickable {
                            //şifre sıfırlama
                        },
                    fontSize = 12.sp
                )
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp, 48.dp, 8.dp, 0.dp),
                    onClick = {
                        viewModel.loginControl()

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = LoginPurple, // Butonun arka plan rengi (Mor tonu)
                        contentColor = LoginDarkPurple    // Butonun içindeki yazının rengi
                    )
                ){
                    Text(text = "Giriş Yap")
                }

            }
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .clickable {
                        // Kayıt olma işlemi
                    },
                text = "Hesabınız yok mu? Kayıt Olun",
                color = LoginPurple,
                fontSize = 12.sp
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskFlowTheme {
        LoginScreen(navController = rememberNavController())
    }
}
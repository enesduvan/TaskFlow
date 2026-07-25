package com.enesduvan.loginmodule.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowForward
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.enesduvan.loginmodule.theme.LoginBlack
import com.enesduvan.loginmodule.theme.LoginDarkPurple
import com.enesduvan.loginmodule.theme.LoginPurple
import com.enesduvan.loginmodule.theme.LoginWhite
import com.enesduvan.loginmodule.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = viewModel(),
    navController: NavController
) {



    LaunchedEffect(key1 = viewModel.errorMessage.value) {
        if (viewModel.errorMessage.value.isNotEmpty()) {
            viewModel.snackbarHostState.showSnackbar(message = viewModel.errorMessage.value)
            viewModel.errorMessage.value = ""
        }
    }

    LaunchedEffect(key1 = viewModel.isLoginSuccessful.value) {
        if (viewModel.isLoginSuccessful.value) {
            navController.navigate("home_screen") {
                popUpTo("login_screen") { inclusive = true }
                popUpTo("signup_screen") { inclusive = true }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(viewModel.snackbarHostState) },
        containerColor = LoginBlack
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        modifier = Modifier.clickable(onClick = { navController.popBackStack() }),
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Geri",
                        tint = LoginWhite
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(LoginPurple, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.PersonAdd,
                    contentDescription = null,
                    tint = LoginDarkPurple,
                    modifier = Modifier.size(36.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = "Create Account",
                color = LoginWhite,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Join us and start organizing your tasks",
                color = Color.Gray,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(32.dp))


            CustomTextField(
                value = viewModel.nameState.value,
                onValueChange = { viewModel.onNameChange(it) },
                placeholder = "Full Name",
                leadingIcon = Icons.Outlined.Person
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomTextField(
                value = viewModel.emailState.value,
                onValueChange = { viewModel.onEmailChange(it) },
                placeholder = "Email Address",
                leadingIcon = Icons.Outlined.Mail
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomTextField(
                value = viewModel.passwordState.value,
                onValueChange = { viewModel.onPasswordChange(it) },
                placeholder = "Password",
                leadingIcon = Icons.Outlined.Lock,
                isPassword = true,
                isPasswordVisible = viewModel.isPasswordVisible.value,
                onVisibilityToggle = { viewModel.togglePasswordVisibility() }
            )

            Spacer(modifier = Modifier.height(12.dp))


            CustomTextField(
                value = viewModel.confirmPasswordState.value,
                onValueChange = { viewModel.onConfirmPasswordChange(it) },
                placeholder = "Confirm Password",
                leadingIcon = Icons.Outlined.Lock,
                isPassword = true,
                isPasswordVisible = viewModel.isConfirmPasswordVisible.value,
                onVisibilityToggle = { viewModel.toggleConfirmPasswordVisibility() }
            )

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = viewModel.checkBoxTerms.value,
                    onCheckedChange = { viewModel.checkBoxTerms.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = LoginDarkPurple,
                        uncheckedColor = LoginPurple
                    )
                )
                Text(
                    text = "I agree to the ",
                    color = Color.Gray,
                    fontSize = 13.sp
                )
                Text(
                    text = "Terms of Service",
                    color = LoginPurple,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable { /* kocaman bir boşluk */ }
                )
            }


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = viewModel.checkBoxPrivacy.value,
                    onCheckedChange = { viewModel.checkBoxPrivacy.value = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = LoginDarkPurple,
                        uncheckedColor = LoginPurple
                    )
                )
                Text(
                    text = "I agree to the ",
                    color = Color.Gray,
                    fontSize = 13.sp
                )
                Text(
                    text = "Privacy Policy",
                    color = LoginPurple,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable { /* kocaman ikinxi boşluk */ }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = { viewModel.singupControl() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LoginPurple,
                    contentColor = LoginDarkPurple
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Create Account",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

           
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFF2A2A32)
                )
                Text(
                    text = "OR",
                    color = Color.Gray,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFF2A2A32)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = { /* google giriş */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1E1E24),
                    contentColor = LoginWhite
                )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "G", // icon google eklenecek
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = LoginWhite
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Sign up with Google",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            //Sign in
            Row(
                modifier = Modifier.padding(bottom = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = "Sign in",
                    color = LoginPurple,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.clickable {
                        navController.navigate("login_screen")
                    }
                )
            }
        }
    }
}

// bitane texfield yazdık 3 yerde kullanduk
@Composable
private fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: androidx.compose.ui.graphics.vector.ImageVector,
    isPassword: Boolean = false,
    isPasswordVisible: Boolean = false,
    onVisibilityToggle: () -> Unit = {}
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder, color = Color.Gray) },
        shape = RoundedCornerShape(16.dp),
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = LoginWhite,
            unfocusedTextColor = LoginWhite,
            focusedContainerColor = Color(0xFF1E1E24),
            unfocusedContainerColor = Color(0xFF1E1E24),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        visualTransformation = if (isPassword && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = LoginPurple
            )
        },
        trailingIcon = if (isPassword) {
            {
                Icon(
                    imageVector = if (isPasswordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                    contentDescription = null,
                    tint = LoginPurple,
                    modifier = Modifier.clickable { onVisibilityToggle() }
                )
            }
        } else null,
        modifier = Modifier.fillMaxWidth()
    )
}


@Suppress("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignUpScreen(
        viewModel = viewModel(), navController = rememberNavController()
    )
}
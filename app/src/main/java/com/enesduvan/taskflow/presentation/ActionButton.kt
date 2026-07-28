package com.enesduvan.taskflow.presentation

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.enesduvan.loginmodule.theme.LoginDarkPurple
import com.enesduvan.loginmodule.theme.LoginPurple

@Composable
fun ActionButton(
 ) {
    androidx.compose.material3.FloatingActionButton(
        modifier = Modifier.size(48.dp),
        onClick = {

        },
            containerColor = LoginPurple,
        contentColor = LoginDarkPurple
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Görev Ekle"
        )
    }
}
package com.enesduvan.taskflow.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.AppBarMenuState
import androidx.compose.material3.AppBarRow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.enesduvan.taskflow.ui.theme.HomeGray
import com.enesduvan.taskflow.ui.theme.LoginBlack
import com.enesduvan.taskflow.ui.theme.LoginDarkPurple
import com.enesduvan.taskflow.ui.theme.LoginGray
import com.enesduvan.taskflow.ui.theme.LoginPurple
import com.enesduvan.taskflow.ui.theme.LoginWhite
import com.enesduvan.taskflow.ui.theme.TaskFlowTheme
import com.enesduvan.taskflow.viewmodel.HomeViewModel
import com.enesduvan.taskflow.viewmodel.TaskModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navController: NavController) {
    //statik görev daha sonra sonradan eklenebilir yapacaım

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = LoginBlack, // Scaffold'un ana arka plan rengi
        topBar = {
            TopAppBar(
                title = {
                    Row() {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "App Logo",
                            tint = LoginPurple,
                            modifier = Modifier.size(32.dp).padding(end = 8.dp)
                        )
                        Text(
                            text = "Görevlerim",
                            color = LoginWhite,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                // TopBar'ın arka plan rengini de sayfa ile uyumlu yapıyoruz
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = LoginBlack
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.size(48.dp), onClick = {
                // görev ekleme daha sonra eklenecek
            },containerColor = LoginPurple, // Butonun arka plan rengi
                contentColor = LoginDarkPurple   // İçindeki ikonun rengi
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Görev Ekle"
                )
            }
        }
    ) {



        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it).background(LoginBlack)
        ) {
            items(viewModel.taskList.size) { index ->
                MyTaskCard(viewModel = viewModel, task = viewModel.taskList[index])
            }
        }
    }
}
@Composable
fun MyTaskCard(viewModel: HomeViewModel,task: TaskModel) {
    Card(
        // Kartın dışındaki boşluklar ve renk
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = HomeGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Kartın içindeki boşluk
            verticalAlignment = Alignment.Top // Checkbox'ı en üste hizalar
        ) {
            // 1. ONAY KUTUSU (CHECKBOX)
            Checkbox(
                checked = task.Checked,
                colors = CheckboxDefaults.colors(
                    uncheckedColor = LoginWhite,
                    checkedColor = LoginPurple,
                    checkmarkColor = LoginDarkPurple //mark tik işareti
                ),
                onCheckedChange = { isChecked ->
                    viewModel.onCheckedChange(task, isChecked)
                },
                // Checkbox'ın kendi içindeki gereksiz boşluğu sıfırlayıp sağdan biraz açıyoruz
                modifier = Modifier.padding(end = 8.dp, top = 2.dp)
            )


            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = task.Task,
                    color = LoginWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = task.Description,
                    color = LoginWhite,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis // Sığmayan kısmı "..." ile keser
                )


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // Takvim İkonu ve Yazısı (Kendi içinde bir Row daha)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarMonth,
                            contentDescription = "Calendar Icon",
                            tint = LoginPurple,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = task.Date,
                            color = LoginWhite,
                            fontSize = 13.sp
                        )
                    }


                    Box(
                        modifier = Modifier
                            .background(
                                color = task.PriorityColor(),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 2.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = task.Priority,
                            color = LoginWhite,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    //test@gmail.com
    TaskFlowTheme {
        HomeScreen(viewModel = viewModel(), navController = rememberNavController())
    }
}
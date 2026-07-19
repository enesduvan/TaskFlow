package com.enesduvan.taskflow.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.enesduvan.taskflow.ui.theme.LoginBlack
import com.enesduvan.taskflow.ui.theme.LoginGray
import com.enesduvan.taskflow.ui.theme.LoginPurple
import com.enesduvan.taskflow.ui.theme.LoginWhite
import com.enesduvan.taskflow.ui.theme.TaskFlowTheme

@Composable
fun HomeScreen(navController: NavController) {
    // Ana sayfa içeriği buraya gelecek
}
@Composable
fun MyTaskCard() {
    Card(
        // Kartın dışındaki boşluklar ve renk
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = LoginGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), // Kartın içindeki boşluk
            verticalAlignment = Alignment.Top // Checkbox'ı en üste hizalar
        ) {
            // 1. ONAY KUTUSU (CHECKBOX)
            Checkbox(
                checked = false,
                colors = CheckboxDefaults.colors(
                    uncheckedColor = LoginWhite,
                    checkedColor = LoginPurple
                ),
                onCheckedChange = { },
                // Checkbox'ın kendi içindeki gereksiz boşluğu sıfırlayıp sağdan biraz açıyoruz
                modifier = Modifier.padding(end = 8.dp, top = 2.dp)
            )

            // 2. İÇERİK SÜTUNU
            Column(
                modifier = Modifier.fillMaxWidth(),
                // İçindeki elemanların (Başlık, Açıklama, Alt Satır) arasına otomatik 6.dp boşluk atar
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {

                // Başlık
                Text(
                    text = "Görev Başlığı",
                    color = LoginWhite,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold // Tasarımdaki gibi kalın yaptık
                )

                // Açıklama
                Text(
                    text = "Görev Açıklaması ",
                    color = LoginWhite, // Tasarımdaki gibi biraz daha soluk bir gri
                    fontSize = 14.sp,
                    maxLines = 1, // Sadece tek satır olmasını sağlar
                    overflow = TextOverflow.Ellipsis // Sığmayan kısmı "..." ile keser
                )

                // 3. ALT SATIR (Takvim ve Öncelik Etiketi)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp) // Takvim grubu ile etiket arası boşluk
                ) {

                    // Takvim İkonu ve Yazısı (Kendi içinde bir Row daha)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.CalendarMonth, // Outlined ikon tasarıma daha uygun
                            contentDescription = "Calendar Icon",
                            tint = LoginPurple,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp)) // İkon ile yazı arası minik boşluk
                        Text(
                            text = "Jul 18",
                            color = LoginWhite,
                            fontSize = 13.sp
                        )
                    }

                    // Öncelik Etiketi (Badge)
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color(0xFFFFB74D), // değişken renk olacak
                                shape = RoundedCornerShape(12.dp) // Köşeleri yuvarlattık
                            )
                            .padding(horizontal = 10.dp, vertical = 2.dp), // Kutunun iç boşluğu
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Öncelik",
                            color = LoginWhite,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold // Etiket yazısı çok kalın olmalı
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
    TaskFlowTheme {
        MyTaskCard()
    }
}
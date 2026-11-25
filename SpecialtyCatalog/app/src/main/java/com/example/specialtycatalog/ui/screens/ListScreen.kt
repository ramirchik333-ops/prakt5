package com.example.specialtycatalog.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.specialtycatalog.data.Specialty
import com.example.specialtycatalog.data.SpecialtyRepository
import com.example.specialtycatalog.ui.theme.SpecialtyCatalogTheme

/**
 * Главный экран (ListScreen).
 * Состояние темы не хранится внутри — оно поднято в MainActivity,
 * поэтому экран только получает darkTheme и вызывает onThemeChange.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    navController: NavController,
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    Scaffold(
        // Явно учитываем системные отступы: «капля» камеры и панель навигации
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            TopAppBar(
                title = { Text(text = "ИТ-специальности") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Тема: " + if (darkTheme) "тёмная" else "светлая",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    TextButton(onClick = { onThemeChange(!darkTheme) }) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "Сменить тему",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Сменить",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Выберите специальность, чтобы посмотреть подробности",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
                )
            }
            items(SpecialtyRepository.specialties) { specialty ->
                SpecialtyCard(
                    specialty = specialty,
                    onClick = { navController.navigate("detail/${specialty.id}") }
                )
            }
            item { Spacer(modifier = Modifier.height(12.dp)) }
        }
    }
}

/** Карточка одной специальности в списке. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpecialtyCard(
    specialty: Specialty,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .padding(end = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = specialty.code.takeLast(2),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = specialty.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Код: ${specialty.code}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = specialty.qualification,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                contentDescription = "Открыть",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Preview(showBackground = true, name = "Список специальностей")
@Composable
fun ListScreenPreview() {
    SpecialtyCatalogTheme(darkTheme = false) {
        ListScreen(
            navController = rememberNavController(),
            darkTheme = false,
            onThemeChange = {}
        )
    }
}

@Preview(
    showBackground = true,
    name = "Список специальностей (тёмная тема)",
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES,
    locale = "ru"
)
@Composable
fun ListScreenDarkPreview() {
    SpecialtyCatalogTheme(darkTheme = true) {
        Box(modifier = Modifier.fillMaxSize()) {
            ListScreen(
                navController = rememberNavController(),
                darkTheme = true,
                onThemeChange = {}
            )
        }
    }
}

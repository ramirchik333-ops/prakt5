package com.example.specialtycatalog.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColors = lightColorScheme(
    primary = PurplePrimary,
    onPrimary = PurpleOnPrimary,
    primaryContainer = PurplePrimaryContainer,
    onPrimaryContainer = PurpleOnPrimaryContainer,
    secondary = PurpleSecondary,
    onSecondary = PurpleOnPrimary,
    secondaryContainer = PurpleSecondaryContainer,
    onSecondaryContainer = PurpleOnSecondaryContainer,
    tertiary = PurpleTertiary,
    onTertiary = PurpleOnPrimary,
    background = PurpleBackground,
    onBackground = PurpleOnSurface,
    surface = PurpleSurface,
    onSurface = PurpleOnSurface,
    surfaceVariant = PurpleSurfaceVariant,
    onSurfaceVariant = PurpleOnSurfaceVariant,
    outline = PurpleOutline
)

private val DarkColors = darkColorScheme(
    primary = PurpleDarkPrimary,
    onPrimary = PurpleDarkOnPrimary,
    primaryContainer = PurpleDarkPrimaryContainer,
    onPrimaryContainer = PurpleDarkOnPrimaryContainer,
    secondary = PurpleDarkSecondary,
    onSecondary = PurpleDarkOnPrimary,
    secondaryContainer = PurpleDarkSecondaryContainer,
    onSecondaryContainer = PurpleDarkOnSecondaryContainer,
    tertiary = PurpleDarkTertiary,
    onTertiary = PurpleDarkOnPrimary,
    background = PurpleDarkBackground,
    onBackground = PurpleDarkOnSurface,
    surface = PurpleDarkSurface,
    onSurface = PurpleDarkOnSurface,
    surfaceVariant = PurpleDarkSurfaceVariant,
    onSurfaceVariant = PurpleDarkOnSurfaceVariant,
    outline = PurpleDarkOutline
)

/**
 * Тема приложения. darkTheme приходит сверху (из MainActivity),
 * поэтому выбор пользователя сохраняется при повороте экрана.
 */
@Composable
fun SpecialtyCatalogTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = AppTypography,
        content = content
    )
}

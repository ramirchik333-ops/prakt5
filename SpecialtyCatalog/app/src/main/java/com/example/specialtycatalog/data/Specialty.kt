package com.example.specialtycatalog.data

/**
 * Модель одной ИТ-специальности.
 * id — уникальный идентификатор, по нему экран деталей находит запись в репозитории.
 */
data class Specialty(
    val id: Int,
    val title: String,
    val code: String,
    val description: String,
    val duration: String,
    val qualification: String
)

package com.chakra.nafas.data.model

import androidx.compose.ui.graphics.Color

enum class Chakra(
    val id: String,
    val arabicName: String,
    val sanskritName: String,
    val color: Color,
    val frequency: Int,
    val vibrationPattern: LongArray,
    val description: String
) {
    ROOT("root", "تشاكرا الجذر", "Muladhara", Color(0xFFFF0000), 396, longArrayOf(0, 600, 200, 600), "الاستقرار والأمان"),
    SACRAL("sacral", "تشاكرا العجز", "Svadhisthana", Color(0xFFFFA500), 417, longArrayOf(0, 400, 150, 400), "الإبداع والعاطفة"),
    SOLAR("solar", "تشاكرا الشمس", "Manipura", Color(0xFFFFFF00), 528, longArrayOf(0, 300, 100, 300), "الثقة بالنفس"),
    HEART("heart", "تشاكرا القلب", "Anahata", Color(0xFF00FF00), 639, longArrayOf(0, 500, 500, 500), "الحب والتعاطف"),
    THROAT("throat", "تشاكرا الحنجرة", "Vishuddha", Color(0xFF00BFFF), 741, longArrayOf(0, 200, 100, 200), "التواصل"),
    THIRD_EYE("third_eye", "العين الثالثة", "Ajna", Color(0xFF4B0082), 852, longArrayOf(0, 150, 50, 150), "الحدس"),
    CROWN("crown", "تشاكرا التاج", "Sahasrara", Color(0xFF9400D3), 963, longArrayOf(0, 800, 300, 800), "الروحانية");
    
    companion object {
        fun getById(id: String) = values().find { it.id == id } ?: ROOT
    }
}

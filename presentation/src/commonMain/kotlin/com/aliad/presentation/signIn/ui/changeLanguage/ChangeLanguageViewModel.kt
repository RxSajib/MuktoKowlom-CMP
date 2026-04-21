package com.aliad.presentation.signIn.ui.changeLanguage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aliad.model.Language
import com.aliad.usecase.dataStore.GetStringData
import com.sajib.data.appConstant.AppConstant
import kotlinx.coroutines.launch
import kotlin.code

class ChangeLanguageViewModel constructor(
    val getStringData: GetStringData
) : ViewModel() {

    val languageData: List<Language> = listOf(
        Language(text = "বাংলা", code = "bn", symbol = "অ", description = "Bangla"),
        Language("English", "en", symbol = "A", description = "English_US"),
        //     Language("हिन्दी", "hi", symbol = "अ", description = "Hindi"),
        //  Language("ગુજરાતી", "gu", isEnable = false),
        //  Language("मराठी", "mr", isEnable = false),
        //  Language("తెలుగు", "te", isEnable = false),
        //   Language("தமிழ்", "ta", isEnable = false),
        //   Language("ਪੰਜਾਬੀ", "pa", isEnable = false),
        //  Language("ଓଡ଼ିଆ", "or", isEnable = false),
        //  Language("اردو", "ur", isEnable = false),
        //  Language("অসমীয়া", "as", isEnable = false),
        //  Language("മലയാളം", "ml", false),
        // Language("ಕನ್ನಡ", "kn", false),

    )


    var selectedPosition by mutableStateOf(0)

    var selectedLanguage by mutableStateOf(Language())

    init {
        getLanguage()
    }

    fun getLanguage(){
        viewModelScope.launch {
            getStringData.getStringData(key = AppConstant.SELECT_LOCAL).collect { lan ->
                selectedLanguage = selectedLanguage.copy(code = lan)
            }
        }
    }

}
package com.aliad.muktokowlom.utils


    fun getTitle(selectLn: String, title: String?, titleBn: String?): String {
        return when (selectLn) {
            "en" -> {
                // If the title is not null, return it, otherwise return title_bn
                title ?: titleBn ?: "Default"  // Default if both are null
            }

            "bn" -> {
                // If the Bengali title is not null, return it, otherwise return title
                titleBn ?: title ?: "Default"  // Default if both are null
            }

            else -> {
                // Default case: return title if available, else title_bn
                title ?: titleBn ?: "Default"  // Default if both are null
            }
        }
    }


fun getStoryData(dataBn : String?, dataEn : String?) : String {
    return when {
        !dataBn.isNullOrEmpty() -> dataBn
        !dataEn.isNullOrEmpty() -> dataEn
        else -> ""
    }
}

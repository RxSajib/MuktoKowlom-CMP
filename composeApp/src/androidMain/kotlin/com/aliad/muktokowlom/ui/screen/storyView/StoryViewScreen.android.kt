package com.aliad.muktokowlom.ui.screen.storyView

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
actual fun LoadPdf(pdfUrl: String) {

    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(pdfUrl),
        isZoomEnable = true
    )

    if (!pdfState.isLoaded) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }

    VerticalPDFReader(
        state = pdfState,
        modifier = Modifier.fillMaxSize()
    )
}
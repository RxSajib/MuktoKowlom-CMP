package com.aliad.muktokowlom.ui.screen.storyView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.viewinterop.UIKitView
import platform.Foundation.NSURL
import platform.PDFKit.PDFDocument
import platform.PDFKit.PDFView

@androidx.compose.runtime.Composable
actual fun LoadPdf(pdfUrl: String) {

    val nsUrl = NSURL(string = pdfUrl)
    val document = PDFDocument(uRL = nsUrl)

    val view = PDFView().apply {
        this.document = document
        this.autoScales = true
        this.displayMode = 1 // Represent Enum entry 1 ( Single Page Continuous ) from PDFDisplayMode
        this.displayDirection = 0 // Represent Enum entry 1 ( Horizontal ) from PDFDisplayDirection
    }

    UIKitView(
        factory = { view },
        modifier = Modifier.fillMaxSize().background(Color.White),
    )
}
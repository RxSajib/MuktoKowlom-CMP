package com.aliad.muktokowlom.ui.screen.storyView

import androidx.compose.foundation.Image
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.PDFRenderer
import java.io.File
import java.net.URL

@Composable
actual fun LoadPdf(pdfUrl: String) {

    val file = File("temp.pdf")

    URL(pdfUrl).openStream().use { input ->
        file.outputStream().use { output ->
            input.copyTo(output)
        }
    }

    val document = PDDocument.load(file)
    val renderer = PDFRenderer(document)

    val pages = mutableListOf<ImageBitmap>()

    for (i in 0 until document.numberOfPages) {
        val image = renderer.renderImageWithDPI(i, 200f)
        pages.add(image.toComposeImageBitmap())
    }

    document.close()



    var pagess by remember { mutableStateOf<List<ImageBitmap>>(emptyList()) }


    LazyColumn {

        items(pages) { page ->

            Image(
                bitmap = page,
                contentDescription = "PDF Page"
            )

        }

    }
}
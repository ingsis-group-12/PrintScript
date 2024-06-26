package edu.austral.ingsis.gradle.iterator

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class FileBuffer(file: InputStream) {
    private val bufferReader = BufferedReader(InputStreamReader(file))

    fun getFileBuffered() = bufferReader
}

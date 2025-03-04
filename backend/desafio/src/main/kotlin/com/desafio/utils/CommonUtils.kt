package com.desafio.utils

import org.mozilla.universalchardet.UniversalDetector
import java.nio.charset.Charset

object CommonUtils {

    fun convertToDouble(value: String?): Double? {
        return value
            ?.replace(".", "")  // Remove thousand separator (if present)
            ?.replace(",", ".") // Convert decimal separator
            ?.toDoubleOrNull()  // Convert to Double safely
    }

    fun convertToUtf8(bytes: ByteArray): String {
        val encoding = detectEncoding(bytes)
        val charset = Charset.forName(encoding) // Convert to detected charset
        return bytes.toString(charset)
    }

    private fun detectEncoding(bytes: ByteArray): String {
        val detector = UniversalDetector(null)
        detector.handleData(bytes, 0, bytes.size)
        detector.dataEnd()
        val encoding = detector.detectedCharset ?: "UTF-8" // Default to UTF-8 if unknown
        detector.reset()
        return encoding
    }
}

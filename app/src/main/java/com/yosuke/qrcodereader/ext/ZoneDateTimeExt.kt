package com.yosuke.qrcodereader.ext

import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter

fun ZonedDateTime.toStringForFileName(): String {
    return format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
}
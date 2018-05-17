package net.kibotu.androidmanifestcommandlineupdater

import java.io.File
import java.io.InputStream
import java.nio.charset.Charset


/**
 * Created by janrabe on 17.05.18.
 */


fun main(args: Array<String>?) {

    if (args?.find { it.contains("-help") } != null) {
        println("java -jar manifestupdater -versionCode=[d] -versionName=[w] -path=[s]")
        return
    }

    val file = File(args?.find { it.contains("-path=") } ?: "src/main/resources/AndroidManifest.xml")
    val manifest = file.inputStream().readTextAndClose()
    val versionCode = args?.find { it.contains("-versionCode=") } ?: "1"
    val versionName = args?.find { it.contains("-versionName=") } ?: "0.0.1"

    val versionCodeMatcher = Regex("android:versionCode=\"(.*?)\"")
    val versionNameMatcher = Regex("android:versionName=\"(.*?)\"")
    val packageMatcher = Regex("package=\"(.*?)\"")

    val currentVersionCode = versionNameMatcher.find(manifest)?.groupValues?.get(1)
    val currentVersionName = versionCodeMatcher.find(manifest)?.groupValues?.get(1)
    val currentPackageName = packageMatcher.find(manifest)?.groupValues?.get(1)

    var newManifest = manifest.replace(versionCodeMatcher, "android:versionCode=\"$versionCode\"")
    newManifest = newManifest.replace(versionNameMatcher, "android:versionName=\"$versionName\"")

    print("Updating versionCode: $currentVersionCode -> $versionCode and versionName: $currentVersionName -> $versionName for $currentPackageName \n${file.absolutePath}")

    file.writeText(newManifest)
}

fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String = bufferedReader(charset).use { it.readText() }
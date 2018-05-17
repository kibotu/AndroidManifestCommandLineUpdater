package net.kibotu.androidmanifestcommandlineupdater

import java.io.File
import java.io.InputStream
import java.nio.charset.Charset


/**
 * Created by janrabe on 17.05.18.
 */


fun main(args: Array<String>) {

    if (args.find { it.contains("-help") } != null) {
        println("java -jar manifestupdater -versionCode=[d] -versionName=[w] -path=[s]")
        return
    }

    if (!args.any { it.contains("-path=") }) {
        println("AndroidManifest.xml path is required: -path=")
        return
    }

    val file = File(args.find { it.contains("-path=") }?.replace("-path=", "")
            ?: "src/main/resources/AndroidManifest.xml")

    val manifest = file.inputStream().readTextAndClose()
    val versionCode = args.find { it.contains("-versionCode=") }?.replace("-versionCode=", "")
    val versionName = args.find { it.contains("-versionName=") }?.replace("-versionName=", "")

    val versionCodeMatcher = Regex("android:versionCode=\"(.*?)\"")
    val versionNameMatcher = Regex("android:versionName=\"(.*?)\"")
    val packageMatcher = Regex("package=\"(.*?)\"")

    val currentVersionCode = versionCodeMatcher.find(manifest)?.groupValues?.get(1)
    val currentVersionName = versionNameMatcher.find(manifest)?.groupValues?.get(1)
    val currentPackageName = packageMatcher.find(manifest)?.groupValues?.get(1)

    var newManifest = manifest
    if (versionCode != null) newManifest = newManifest.replace(versionCodeMatcher, "android:versionCode=\"$versionCode\"")
    if (versionName != null) newManifest = newManifest.replace(versionNameMatcher, "android:versionName=\"$versionName\"")

    print("Updating versionCode: $currentVersionCode -> ${versionCode
            ?: currentVersionCode} and versionName: $currentVersionName -> ${versionName
            ?: currentVersionName} for $currentPackageName \n${file.absolutePath}")

    file.writeText(newManifest)
}

fun InputStream.readTextAndClose(charset: Charset = Charsets.UTF_8): String = bufferedReader(charset).use { it.readText() }
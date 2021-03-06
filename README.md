# AndroidManifest.xml CommandLine Updater  [![Licence](https://img.shields.io/badge/licence-Apache%202-blue.svg)](https://raw.githubusercontent.com/kibotu/KalmanRx/master/LICENSE) [![Gradle Version](https://img.shields.io/badge/gradle-4.7-green.svg)](https://docs.gradle.org/current/release-notes) [![Kotlin](https://img.shields.io/badge/kotlin-1.2.41-green.svg)](https://kotlinlang.org/)

# How to use

### Change versionCode and versionNumber

    java -jar build/libs/manifestupdater-1.0.0-all.jar -path=src/main/resources/AndroidManifest.xml -versionName=1.0.0 -versionCode=1024

### Bitrise (e.g.: Xamarin.Droid)

If you have a release branch named **release/1.0**, then you could use the following to automate your versionCode and versionName like this: 

    releaseVersion=`echo $BITRISE_GIT_BRANCH | sed 's/release\///'`

    java -jar manifestupdater-1.0.0-all.jar -path=Xamarin.Droid/Properties/AndroidManifest.xml -versionCode=$BITRISE_BUILD_NUMBER -versionName=$releaseVersion.$GIT_CLONE_COMMIT_COUNT

e.g.: android:versionName=100 and android:versionCode=1.0.255

### Help

    java -jar build/libs/manifestupdater-1.0.0-all.jar -help

# How to build

    gradle clean build

## Contributors

[Jan Rabe](jan.rabe@kibotu.net)

### License
<pre>
Copyright 2018 Jan Rabe

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</pre>

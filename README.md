<p align="center">
  <img src="Title.png"/>
</p>

## What is AdvancedGDX?
**AdvancedGDX** includes all the classes that I ussualy create when I work with [libGDX](https://libgdx.badlogicgames.com/).
I decided to create this library because I'm getting tired of writing the same classes for each different libGDX project, and
I published it in GitHub to be sure that I won't lose the code, and to be used by everyone who want.

## Status
At this time, **AdvancedGDX** is in early development, so some classes and methods can fail.

## How to use this library
If you want to use this library, you have to clone it in a directory inside your libGDX project, e.g.: *lib* or *advanced*. Then, you have to add the following code inside the build.gradle (**IMPORTANT**: for all the examples, this library is cloned inside a directory called lib)

``` gradle
project(":lib") {
    apply plugin: "java"
    apply plugin: "kotlin"


    dependencies {
        compile "com.badlogicgames.gdx:gdx:$gdxVersion"
        compile "com.badlogicgames.gdx:gdx-box2d:$gdxVersion"
        compile "com.badlogicgames.gdx:gdx-freetype:$gdxVersion"
        compile "com.badlogicgames.box2dlights:box2dlights:$box2DLightsVersion"
        compile 'org.jetbrains.kotlin:kotlin-stdlib:1.0.0-beta-3595'
    }
}
```
You have to replace $«sth»Version with a real version of those libraries, or you have to create a variable. In both cases, you can find all the information that you need [here](https://github.com/libgdx/libgdx/wiki/Dependency-management-with-Gradle).

**IMPORTANT**: This library is made in [Kotlin](https://kotlinlang.org/), which is totally compatible with Java, but requires to be set in the build.gradle. As you can see, there is a compile for Kotlin, and an apply plugin, but that's **not** enough. You have to read [this article](https://kotlinlang.org/docs/reference/using-gradle.html) to configure Kotlin succesfully. You can see the version of the plugin in the code above: you should write the newest one, acording to the article.

Once you've done that, it's time for the last step: adding the name of the directory in the file settings.gradle.

Now, you have to do one last thing: write in the about tab/file the following text:
> This software includes code from AdvancedGDX

> https://github.com/matiuri/AdvancedGDX

It's not obligatory to do it, but writing that in the credits would be great.

### Example
The files in one of my projects are like this:
* build.gradle
``` gradle
buildscript {
    repositories {
        mavenCentral()
        maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:1.2.3'
        classpath 'org.jetbrains.kotlin:kotlin-gradle-plugin:1.0.0-beta-3595'
    }
}

allprojects {
    apply plugin: "eclipse"
    apply plugin: "idea"

    version = '1.0'
    ext {
        appName = "A la Nave"
        gdxVersion = '1.7.1'
        roboVMVersion = '1.9.0'
        box2DLightsVersion = '1.4'
        ashleyVersion = '1.7.0'
        aiVersion = '1.6.0'
    }

    repositories {
        mavenCentral()
        maven { url "https://oss.sonatype.org/content/repositories/snapshots/" }
        maven { url "https://oss.sonatype.org/content/repositories/releases/" }
    }
}

project(":desktop") {
    apply plugin: "java"
    apply plugin: "kotlin"

    dependencies {
        compile project(":core")
        compile project(":lib")
        compile "com.badlogicgames.gdx:gdx-backend-lwjgl:$gdxVersion"
        compile "com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop"
        compile "com.badlogicgames.gdx:gdx-box2d-platform:$gdxVersion:natives-desktop"
        compile "com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-desktop"
        compile 'org.jetbrains.kotlin:kotlin-stdlib:1.0.0-beta-3595'
    }
}

project(":core") {
    apply plugin: "java"
    apply plugin: "kotlin"

    dependencies {
        compile project(":lib")
        compile "com.badlogicgames.gdx:gdx:$gdxVersion"
        compile "com.badlogicgames.gdx:gdx-box2d:$gdxVersion"
        compile "com.badlogicgames.box2dlights:box2dlights:$box2DLightsVersion"
        compile "com.badlogicgames.gdx:gdx-freetype:$gdxVersion"
        compile "com.badlogicgames.gdx:gdx-ai:$aiVersion"
        compile 'org.jetbrains.kotlin:kotlin-stdlib:1.0.0-beta-3595'
    }
}

project(":android") {
    apply plugin: "android"
    apply plugin: 'kotlin-android'

    configurations { natives }

    dependencies {
        compile project(":core")
        compile project(":lib")
        compile "com.badlogicgames.gdx:gdx-backend-android:$gdxVersion"
        natives "com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-armeabi"
        natives "com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-armeabi-v7a"
        natives "com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-x86"
        compile "com.badlogicgames.gdx:gdx-box2d:$gdxVersion"
        natives "com.badlogicgames.gdx:gdx-box2d-platform:$gdxVersion:natives-armeabi"
        natives "com.badlogicgames.gdx:gdx-box2d-platform:$gdxVersion:natives-armeabi-v7a"
        natives "com.badlogicgames.gdx:gdx-box2d-platform:$gdxVersion:natives-x86"
        compile "com.badlogicgames.box2dlights:box2dlights:$box2DLightsVersion"
        compile "com.badlogicgames.gdx:gdx-freetype:$gdxVersion"
        natives "com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-armeabi"
        natives "com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-armeabi-v7a"
        natives "com.badlogicgames.gdx:gdx-freetype-platform:$gdxVersion:natives-x86"
        compile "com.badlogicgames.gdx:gdx-ai:$aiVersion"
        compile 'org.jetbrains.kotlin:kotlin-stdlib:1.0.0-beta-3595'
    }
}

project(":lib") {
    apply plugin: "java"
    apply plugin: "kotlin"


    dependencies {
        compile "com.badlogicgames.gdx:gdx:$gdxVersion"
        compile "com.badlogicgames.gdx:gdx-box2d:$gdxVersion"
        compile "com.badlogicgames.gdx:gdx-freetype:$gdxVersion"
        compile "com.badlogicgames.box2dlights:box2dlights:$box2DLightsVersion"
        compile 'org.jetbrains.kotlin:kotlin-stdlib:1.0.0-beta-3595'
    }
}

tasks.eclipse.doLast {
    delete ".project"
}
```
* settings.gradle
``` gradle
include 'desktop', 'core', 'android', 'lib'
```

Adding this library into the Maven Central Repositories could be done in the future.

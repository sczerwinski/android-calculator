import org.gradle.api.plugins.*
import org.gradle.api.tasks.wrapper.*
import org.gradle.script.lang.kotlin.*

val kotlin_version = "1.0.2"

repositories {
	jcenter()
	mavenCentral()
}

dependencies {
	"classpath"("com.android.tools.build:gradle:2.1.0")
	"classpath"("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
	"classpath"("org.jetbrains.kotlin:kotlin-android-extensions:$kotlin_version")
}

tasks.withType<Wrapper> {
	distributionUrl = "https://repo.gradle.org/gradle/repo/gradle-gsk-1.0.0-M1.zip"
}

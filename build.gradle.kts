plugins {
	java
	id("org.springframework.boot") version "3.3.2"
	id("io.spring.dependency-management") version "1.1.5"
	id("org.graalvm.buildtools.native") version "0.10.2"
}

group = "de.exxeta"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.cloud:spring-cloud-starter-function-webflux:3.2.2")
	runtimeOnly("com.h2database:h2")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}






tasks.register<Zip>("packageDistribution") {
	dependsOn("nativeCompile")
	archiveFileName.set("${project.name}.zip")
	destinationDirectory.set(layout.buildDirectory.dir("dist"))
	from("src/main/function")
	from("build/native/nativeCompile/azure-native-spring-function")
}


graalvmNative {

	binaries.all { resources.autodetect()
	}
}

tasks.withType<Test>{
	useJUnitPlatform()
}


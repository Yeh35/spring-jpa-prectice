import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.7"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
	kotlin("kapt") version "1.6.21" // 추가
}

group = "com.yeh35.spring-jpa-prectice"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	implementation("com.querydsl:querydsl-jpa") //querydsl 추가
	kapt("com.querydsl:querydsl-apt::jpa")
	kapt("org.springframework.boot:spring-boot-configuration-processor")

	implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter")

	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// QueryDSL이 만들어주는 Qclass를 사용하기 위해 저 위치로 접근할 수 있도록 설정해주는 부분
kotlin.sourceSets.main {
	println("kotlin sourceSet buildDir :: $buildDir")
	setBuildDir("$buildDir/generated/querydsl")
}

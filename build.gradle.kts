plugins {
    id("org.springframework.boot") version "2.5.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("plugin.spring") version "1.5.10"
    kotlin("jvm") version "1.5.10"
    id("com.netflix.dgs.codegen") version "4.6.4"

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation(kotlin("stdlib"))
    implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter:latest.release")

}
tasks.getByName<com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask>("generateJava") {
    packageName = "movie_graph.model"
    schemaPaths = listOf("${projectDir}/src/main/resources/schema").toMutableList()
    generateClient = true
    
}
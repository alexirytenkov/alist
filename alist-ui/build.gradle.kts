import com.moowork.gradle.node.NodeExtension
import com.moowork.gradle.node.npm.NpmTask

buildscript {
	repositories {
	  mavenCentral()
	  maven(url = "https://plugins.gradle.org/m2/")
	}

	dependencies {
        classpath("com.moowork.gradle:gradle-node-plugin:1.3.1")
    }
}

plugins {
    base
	id("com.moowork.node") version "1.3.1"
}

node {
	version = "16.3.0"
	npmVersion = "7.15.1"
	download = false
}

tasks.named<NpmTask>("npm_run_build") {
	inputs.files(fileTree("public"))
	inputs.files(fileTree("src"))
	inputs.file("package.json")
	inputs.file("package-lock.json")
	outputs.dir("dist")
}

val packageNpmApp by tasks.registering(Jar::class) {
	dependsOn("npm_run_build")
	baseName = "alist-ui"
	extension = "jar"
	destinationDir = file("$projectDir/build")
	from("dist") {
	    into("static")
	}
}

val npmResources by configurations.creating

configurations.named("default").get().extendsFrom(npmResources)

artifacts {
	add(npmResources.name, packageNpmApp.get().archivePath) {
	    builtBy(packageNpmApp)
	    type = "jar"
	}
}

tasks.assemble {
	dependsOn(packageNpmApp)
}

tasks.clean {
	delete(packageNpmApp.get().archivePath)
}

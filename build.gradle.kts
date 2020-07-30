import io.fluidsonic.gradle.*

plugins {
	id("io.fluidsonic.gradle") version "1.1.2"
}

fluidLibrary(name = "mongo", version = "1.1.1")

fluidLibraryModule(description = "Kotlin coroutine support for MongoDB built on top of the official Reactive Streams Java Driver") {
	publishSingleTargetAsModule()

	targets {
		jvm {
			dependencies {
				api(kotlinx("coroutines-core", "1.3.8-1.4.0-rc"))
				api(mongodb("driver-core"))

				implementation(kotlinx("coroutines-reactive", "1.3.8-1.4.0-rc"))
				implementation(mongodb("driver-reactivestreams"))
			}
		}
	}
}


fun mongodb(name: String, version: String = "4.0.5") =
	"org.mongodb:mongodb-$name:$version"

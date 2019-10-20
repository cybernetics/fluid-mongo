/*
 * Copyright 2008-present MongoDB, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.fluidsonic.mongo

import com.mongodb.*
import com.mongodb.client.model.*
import kotlinx.coroutines.reactive.*
import org.bson.codecs.configuration.*
import org.bson.conversions.*
import kotlin.reflect.*


internal class ReactiveMongoDatabase(
	val source: com.mongodb.reactivestreams.client.MongoDatabase
) : MongoDatabase {

	override val name: String
		get() = source.name

	override val codecRegistry: CodecRegistry
		get() = source.codecRegistry

	override val readPreference: ReadPreference
		get() = source.readPreference

	override val writeConcern: WriteConcern
		get() = source.writeConcern

	override val readConcern: ReadConcern
		get() = source.readConcern


	override fun withCodecRegistry(codecRegistry: CodecRegistry) =
		source.withCodecRegistry(codecRegistry).wrap()


	override fun withReadPreference(readPreference: ReadPreference) =
		source.withReadPreference(readPreference).wrap()


	override fun withWriteConcern(writeConcern: WriteConcern) =
		source.withWriteConcern(writeConcern).wrap()


	override fun withReadConcern(readConcern: ReadConcern) =
		source.withReadConcern(readConcern).wrap()


	override fun getCollection(collectionName: String) =
		source.getCollection(collectionName).wrap()


	override fun <TDocument : Any> getCollection(collectionName: String, documentClass: KClass<TDocument>) =
		source.getCollection(collectionName, documentClass.java).wrap()


	override suspend fun runCommand(command: Bson) =
		source.runCommand(command).awaitFirst()!!


	override suspend fun runCommand(command: Bson, readPreference: ReadPreference) =
		source.runCommand(command, readPreference).awaitFirst()!!


	override suspend fun <TResult : Any> runCommand(command: Bson, resultClass: KClass<out TResult>) =
		source.runCommand(command, resultClass.java).awaitFirst()!!


	override suspend fun <TResult : Any> runCommand(command: Bson, readPreference: ReadPreference, resultClass: KClass<out TResult>) =
		source.runCommand(command, readPreference, resultClass.java).awaitFirst()!!


	override suspend fun runCommand(clientSession: ClientSession, command: Bson) =
		source.runCommand(clientSession.unwrap(), command).awaitFirst()!!


	override suspend fun runCommand(clientSession: ClientSession, command: Bson, readPreference: ReadPreference) =
		source.runCommand(clientSession.unwrap(), command, readPreference).awaitFirst()!!


	override suspend fun <TResult : Any> runCommand(clientSession: ClientSession, command: Bson, resultClass: KClass<out TResult>) =
		source.runCommand(clientSession.unwrap(), command, resultClass.java).awaitFirst()!!


	override suspend fun <TResult : Any> runCommand(clientSession: ClientSession, command: Bson, readPreference: ReadPreference, resultClass: KClass<out TResult>) =
		source.runCommand(clientSession.unwrap(), command, readPreference, resultClass.java).awaitFirst()!!


	override suspend fun drop() {
		source.drop().awaitSuccess()
	}


	override suspend fun drop(clientSession: ClientSession) {
		source.drop(clientSession.unwrap()).awaitSuccess()
	}


	override fun listCollectionNames() =
		source.listCollectionNames().asFlow()


	override fun listCollectionNames(clientSession: ClientSession) =
		source.listCollectionNames(clientSession.unwrap()).asFlow()


	override fun listCollections() =
		source.listCollections().wrap()


	override fun <TResult : Any> listCollections(resultClass: KClass<out TResult>) =
		source.listCollections(resultClass.java).wrap()


	override fun listCollections(clientSession: ClientSession) =
		source.listCollections(clientSession.unwrap()).wrap()


	override fun <TResult : Any> listCollections(clientSession: ClientSession, resultClass: KClass<out TResult>) =
		source.listCollections(clientSession.unwrap(), resultClass.java).wrap()


	override suspend fun createCollection(collectionName: String) {
		source.createCollection(collectionName).awaitSuccess()
	}


	override suspend fun createCollection(collectionName: String, options: CreateCollectionOptions) {
		source.createCollection(collectionName, options).awaitSuccess()
	}


	override suspend fun createCollection(clientSession: ClientSession, collectionName: String) {
		source.createCollection(clientSession.unwrap(), collectionName).awaitSuccess()
	}


	override suspend fun createCollection(clientSession: ClientSession, collectionName: String, options: CreateCollectionOptions) {
		source.createCollection(clientSession.unwrap(), collectionName, options).awaitSuccess()
	}


	override suspend fun createView(viewName: String, viewOn: String, pipeline: List<Bson>) {
		source.createView(viewName, viewOn, pipeline).awaitSuccess()
	}


	override suspend fun createView(viewName: String, viewOn: String, pipeline: List<Bson>, createViewOptions: CreateViewOptions) {
		source.createView(viewName, viewOn, pipeline, createViewOptions).awaitSuccess()
	}


	override suspend fun createView(clientSession: ClientSession, viewName: String, viewOn: String, pipeline: List<Bson>) {
		source.createView(clientSession.unwrap(), viewName, viewOn, pipeline).awaitSuccess()
	}


	override suspend fun createView(clientSession: ClientSession, viewName: String, viewOn: String, pipeline: List<Bson>, createViewOptions: CreateViewOptions) {
		source.createView(clientSession.unwrap(), viewName, viewOn, pipeline, createViewOptions).awaitSuccess()
	}


	override fun watch() =
		source.watch().wrap()


	override fun <TResult : Any> watch(resultClass: KClass<out TResult>) =
		source.watch(resultClass.java).wrap()


	override fun watch(pipeline: List<Bson>) =
		source.watch(pipeline).wrap()


	override fun <TResult : Any> watch(pipeline: List<Bson>, resultClass: KClass<out TResult>) =
		source.watch(pipeline, resultClass.java).wrap()


	override fun watch(clientSession: ClientSession) =
		source.watch(clientSession.unwrap()).wrap()


	override fun <TResult : Any> watch(clientSession: ClientSession, resultClass: KClass<out TResult>) =
		source.watch(clientSession.unwrap(), resultClass.java).wrap()


	override fun watch(clientSession: ClientSession, pipeline: List<Bson>) =
		source.watch(clientSession.unwrap(), pipeline).wrap()


	override fun <TResult : Any> watch(clientSession: ClientSession, pipeline: List<Bson>, resultClass: KClass<out TResult>) =
		source.watch(clientSession.unwrap(), pipeline, resultClass.java).wrap()


	override fun aggregate(pipeline: List<Bson>) =
		source.aggregate(pipeline).wrap()


	override fun <TResult : Any> aggregate(pipeline: List<Bson>, resultClass: KClass<out TResult>) =
		source.aggregate(pipeline, resultClass.java).wrap()


	override fun aggregate(clientSession: ClientSession, pipeline: List<Bson>) =
		source.aggregate(clientSession.unwrap(), pipeline).wrap()


	override fun <TResult : Any> aggregate(clientSession: ClientSession, pipeline: List<Bson>, resultClass: KClass<out TResult>) =
		source.aggregate(clientSession.unwrap(), pipeline, resultClass.java).wrap()
}


internal fun com.mongodb.reactivestreams.client.MongoDatabase.wrap() =
	ReactiveMongoDatabase(this)

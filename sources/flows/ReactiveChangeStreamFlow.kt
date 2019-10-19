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

import com.mongodb.client.model.*
import com.mongodb.client.model.changestream.*
import com.mongodb.reactivestreams.client.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.*
import org.bson.*
import java.util.concurrent.*


internal class ReactiveChangeStreamFlow<TResult : Any>(
	private val source: ChangeStreamPublisher<TResult>
) : ChangeStreamFlow<TResult>, Flow<ChangeStreamDocument<TResult>> by source.asFlow() {

	override fun fullDocument(fullDocument: FullDocument) = apply {
		source.fullDocument(fullDocument)
	}


	override fun resumeAfter(resumeToken: BsonDocument) = apply {
		source.resumeAfter(resumeToken)
	}


	override fun startAtOperationTime(startAtOperationTime: BsonTimestamp) = apply {
		source.startAtOperationTime(startAtOperationTime)
	}


	override fun startAfter(startAfter: BsonDocument) = apply {
		source.startAfter(startAfter)
	}


	override fun batchSize(batchSize: Int) = apply {
		source.batchSize(batchSize)
	}


	override fun maxAwaitTime(maxAwaitTime: Long, timeUnit: TimeUnit) = apply {
		source.maxAwaitTime(maxAwaitTime, timeUnit)
	}


	override fun collation(collation: Collation?) = apply {
		source.collation(collation)
	}


	override fun <TDocument : Any> withDocumentClass(clazz: Class<TDocument>): Flow<TDocument> =
		source.withDocumentClass(clazz).asFlow()


	override suspend fun firstOrNull(): ChangeStreamDocument<TResult>? =
		source.first().awaitFirstOrNull()
}


internal fun <TResult : Any> ChangeStreamPublisher<TResult>.wrap() =
	ReactiveChangeStreamFlow(this)

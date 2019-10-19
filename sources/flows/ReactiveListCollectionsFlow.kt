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

import com.mongodb.reactivestreams.client.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.*
import org.bson.conversions.*
import java.util.concurrent.*


internal class ReactiveListCollectionsFlow<TResult : Any>(
	private val source: ListCollectionsPublisher<TResult>
) : ListCollectionsFlow<TResult>, Flow<TResult> by source.asFlow() {

	override fun filter(filter: Bson?) = apply {
		source.filter(filter)
	}


	override fun maxTime(maxTime: Long, timeUnit: TimeUnit) = apply {
		source.maxTime(maxTime, timeUnit)
	}


	override fun batchSize(batchSize: Int) = apply {
		source.batchSize(batchSize)
	}


	override suspend fun firstOrNull(): TResult? =
		source.first().awaitFirstOrNull()
}


internal fun <TResult : Any> ListCollectionsPublisher<TResult>.wrap() =
	ReactiveListCollectionsFlow(this)

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

import org.bson.conversions.*
import java.util.concurrent.*


internal class CoroutineListCollectionsIterable<TResult>(
	override val async: com.mongodb.async.client.ListCollectionsIterable<TResult>
) : CoroutineMongoIterable<TResult>(async), ListCollectionsIterable<TResult> {

	override fun filter(filter: Bson?): ListCollectionsIterable<TResult> {
		async.filter(filter)
		return this
	}


	override fun maxTime(maxTime: Long, timeUnit: TimeUnit): ListCollectionsIterable<TResult> {
		async.maxTime(maxTime, timeUnit)
		return this
	}


	override fun batchSize(batchSize: Int): ListCollectionsIterable<TResult> {
		async.batchSize(batchSize)
		return this
	}
}


internal fun <TResult> com.mongodb.async.client.ListCollectionsIterable<TResult>.wrap() =
	CoroutineListCollectionsIterable(this)

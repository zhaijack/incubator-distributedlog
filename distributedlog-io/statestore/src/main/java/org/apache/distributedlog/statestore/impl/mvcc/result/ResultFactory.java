/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.distributedlog.statestore.impl.mvcc.result;

import io.netty.util.Recycler;

/**
 * Factory to create results.
 */
public class ResultFactory<K, V> {

    private final Recycler<PutResultImpl<K, V>> putResultRecycler;
    private final Recycler<DeleteResultImpl<K, V>> deleteResultRecycler;
    private final Recycler<RangeResultImpl<K, V>> rangeResultRecycler;
    private final Recycler<TxnResultImpl<K, V>> txnResultRecycler;

    public ResultFactory() {
        this.putResultRecycler = new Recycler<PutResultImpl<K, V>>() {
            @Override
            protected PutResultImpl<K, V> newObject(Handle<PutResultImpl<K, V>> handle) {
                return new PutResultImpl<>(handle);
            }
        };
        this.deleteResultRecycler = new Recycler<DeleteResultImpl<K, V>>() {
            @Override
            protected DeleteResultImpl<K, V> newObject(Handle<DeleteResultImpl<K, V>> handle) {
                return new DeleteResultImpl<>(handle);
            }
        };
        this.rangeResultRecycler = new Recycler<RangeResultImpl<K, V>>() {
            @Override
            protected RangeResultImpl<K, V> newObject(Handle<RangeResultImpl<K, V>> handle) {
                return new RangeResultImpl<>(handle);
            }
        };
        this.txnResultRecycler = new Recycler<TxnResultImpl<K, V>>() {
            @Override
            protected TxnResultImpl<K, V> newObject(Handle<TxnResultImpl<K, V>> handle) {
                return new TxnResultImpl<>(handle);
            }
        };
    }

    public PutResultImpl<K, V> newPutResult() {
        return this.putResultRecycler.get();
    }

    public DeleteResultImpl<K, V> newDeleteResult() {
        return this.deleteResultRecycler.get();
    }

    public RangeResultImpl<K, V> newRangeResult() {
        return this.rangeResultRecycler.get();
    }

    public TxnResultImpl<K, V> newTxnResult() {
        return this.txnResultRecycler.get();
    }

}

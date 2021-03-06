/*
 *    Copyright 2018 Timothy J Veil
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package veil.hdp.hive.jdbc.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import veil.hdp.hive.jdbc.HiveException;
import veil.hdp.hive.jdbc.bindings.*;
import veil.hdp.hive.jdbc.metadata.ColumnTypeDescriptor;
import veil.hdp.hive.jdbc.metadata.HiveType;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TypeDescriptorUtils {

    private static final Logger log = LogManager.getLogger(TypeDescriptorUtils.class);

    private static final LoadingCache<TTypeDesc, ColumnTypeDescriptor> CACHE = CacheBuilder.newBuilder()
            .maximumSize(500)
            .build(new ColumnTypeCacheLoader());

    public static ColumnTypeDescriptor getCachedDescriptor(TTypeDesc type) {
        try {
            return CACHE.get(type);
        } catch (ExecutionException e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }

    private static ColumnTypeDescriptor getDescriptor(TTypeDesc typeDesc) {

        TTypeEntry entry = typeDesc.getTypes().get(0);

        HiveType hiveType = null;

        Integer scale = null;
        Integer precision = null;
        Integer maxLength = null;

        if (entry.isSetPrimitiveEntry()) {
            TPrimitiveTypeEntry primitiveEntry = entry.getPrimitiveEntry();

            if (primitiveEntry.isSetTypeQualifiers()) {
                TTypeQualifiers typeQualifiers = primitiveEntry.getTypeQualifiers();
                Map<String, TTypeQualifierValue> map = typeQualifiers.getQualifiers();

                scale = getQualifier("scale", map);
                precision = getQualifier("precision", map);
                maxLength = getQualifier("characterMaximumLength", map);
            }

            if (primitiveEntry.isSetType()) {
                hiveType = HiveType.valueOf(primitiveEntry.getType());
            }
        }

        if (hiveType == null) {
            throw new HiveException("unable to determine type for entry [" + entry + ']');
        }

        return ColumnTypeDescriptor.builder().hiveType(hiveType).scale(scale).precision(precision).maxLength(maxLength).build();
    }

    private static Integer getQualifier(String key, Map<String, TTypeQualifierValue> map) {
        if (map.containsKey(key)) {
            TTypeQualifierValue qualifierValue = map.get(key);

            if (qualifierValue.isSetI32Value()) {
                return qualifierValue.getI32Value();
            }
        }

        return null;
    }


    private static class ColumnTypeCacheLoader extends CacheLoader<TTypeDesc, ColumnTypeDescriptor> {
        @Override
        public ColumnTypeDescriptor load(@Nonnull TTypeDesc key) {
            return TypeDescriptorUtils.getDescriptor(key);
        }
    }
}

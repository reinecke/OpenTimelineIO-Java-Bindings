// SPDX-License-Identifier: Apache-2.0
// Copyright Contributors to the OpenTimelineIO Project.

package io.opentimeline.opentimelineio;

import io.opentimeline.OTIONative;

/**
 * Represents an object whose schema is unknown to us.
 */
public class UnknownSchema extends SerializableObject {

    protected UnknownSchema() {
    }

    UnknownSchema(OTIONative otioNative) {
        this.nativeManager = otioNative;
    }

    public UnknownSchema(String originalSchemaName, int originalSchemaVersion) {
        this.initObject(originalSchemaName, originalSchemaVersion);
    }

    public UnknownSchema(UnknownSchema.UnknownSchemaBuilder unknownSchemaBuilder) {
        this.initObject(
                unknownSchemaBuilder.originalSchemaName,
                unknownSchemaBuilder.originalSchemaVersion);
    }

    private void initObject(String originalSchemaName, int originalSchemaVersion) {
        this.initialize(originalSchemaName, originalSchemaVersion);
        this.nativeManager.className = this.getClass().getCanonicalName();
    }

    private native void initialize(String originalSchemaName, int originalSchemaVersion);

    public static class UnknownSchemaBuilder {
        private String originalSchemaName = "UnknownSchema";
        private int originalSchemaVersion = 1;

        public UnknownSchemaBuilder() {
        }

        public UnknownSchema.UnknownSchemaBuilder setOriginalSchemaName(String originalSchemaName) {
            this.originalSchemaName = originalSchemaName;
            return this;
        }

        public UnknownSchema.UnknownSchemaBuilder setOriginalSchemaVersion(int originalSchemaVersion) {
            this.originalSchemaVersion = originalSchemaVersion;
            return this;
        }

        public UnknownSchema build() {
            return new UnknownSchema(this);
        }
    }

    public native String getOriginalSchemaName();

    public native int getOriginalSchemaVersion();

    public native boolean isUnknownSchema();

    @Override
    public String toString() {
        return this.getClass().getCanonicalName() +
                "(" +
                "originalSchemaName=" + this.getOriginalSchemaName() +
                ", originalSchemaVersion=" + this.getOriginalSchemaVersion() +
                ")";
    }
}

package dev.morphia.internal;

import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.client.MongoCollection;
import com.mongodb.lang.Nullable;

/**
 * @param <T> the type being defined
 * @morphia.internal
 * @since 2.0
 */
public interface ReadConfigurable<T> {
    /**
     * Gets the read concern
     *
     * @return the read concern
     */
    @Nullable
    ReadConcern getReadConcern();

    /**
     * @return the read preference
     */
    @Nullable
    ReadPreference getReadPreference();

    default <C> MongoCollection<C> prepare(MongoCollection<C> collection) {
        MongoCollection<C> updated = collection;
        ReadConcern readConcern = getReadConcern();
        if (readConcern != null) {
            updated = updated.withReadConcern(readConcern);
        }
        ReadPreference readPreference = getReadPreference();
        if (readPreference != null) {
            updated = updated.withReadPreference(readPreference);
        }

        return updated;
    }

    /**
     * Sets the read concern to apply
     *
     * @param readConcern the read concern
     * @return this
     */
    T readConcern(ReadConcern readConcern);

    /**
     * Sets the read preference to apply
     *
     * @param readPreference the read preference
     * @return this
     */
    T readPreference(ReadPreference readPreference);
}

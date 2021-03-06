package com.vladmihalcea.flexypool.adaptor;

import com.vladmihalcea.flexypool.metric.Metrics;
import com.vladmihalcea.flexypool.util.ConfigurationProperties;

import javax.sql.DataSource;

/**
 * <code>DataSourcePoolAdapter</code> is an {@link AbstractPoolAdapter} that's compatible with any {@link DataSource}
 *
 * Because it's a generic PoolAdapter, it cannot read or write the pool size, since the DataSource might not even be
 * a connection pool.
 *
 * @author Vlad Mihalcea
 * @version    %I%, %E%
 * @since 1.2
 * @see com.vladmihalcea.flexypool.adaptor.PoolAdapter
 */
public class DataSourcePoolAdapter extends AbstractPoolAdapter<DataSource> {

    public static final PoolAdapterFactory<DataSource> FACTORY = new PoolAdapterFactory<DataSource>() {

        @Override
        public PoolAdapter<DataSource> newInstance(
                ConfigurationProperties<DataSource, Metrics, PoolAdapter<DataSource>> configurationProperties) {
        return new DataSourcePoolAdapter(configurationProperties);
        }
    };

    public DataSourcePoolAdapter(ConfigurationProperties<DataSource, Metrics, PoolAdapter<DataSource>> configurationProperties) {
        super(configurationProperties);
    }

    /**
     * This method throws an {@link UnsupportedOperationException} because there's no way we could know if the
     * {@link DataSource} even supports configurable pool sizing
     *
     * @return throws exception
     */
    @Override
    public int getMaxPoolSize() {
        throw new UnsupportedOperationException("The DataSourcePoolAdapter cannot read the max pool size");
    }

    /**
     * This method throws an {@link UnsupportedOperationException} because there's no way we could know if the
     * {@link DataSource} even supports configurable pool sizing
     *
     * @param maxPoolSize the upper amount of pooled connections.
     */
    @Override
    public void setMaxPoolSize(int maxPoolSize) {
        throw new UnsupportedOperationException("The DataSourcePoolAdapter cannot write the max pool size");
    }
}

package com.pronoia.junit.rule.infinispan;

import java.util.concurrent.TimeUnit;

import org.infinispan.commons.api.BasicCacheContainer;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.CacheContainer;
import org.infinispan.manager.DefaultCacheManager;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmbeddedInfinispanResource extends ExternalResource {
    Logger log = LoggerFactory.getLogger(this.getClass());

    CacheContainer cacheContainer;

    ConfigurationBuilder config = new ConfigurationBuilder();

    @Override
    protected void before() throws Throwable {
        cacheContainer = new DefaultCacheManager(config.build(), false);
        cacheContainer.start();
    }

    @Override
    protected void after() {
        cacheContainer.stop();
    }

    public void setExpiration(int value, TimeUnit timeUnit) {
        config.expiration().lifespan(5, TimeUnit.SECONDS);
    }

    public CacheContainer getCacheContainer() {
        return cacheContainer;
    }

    public Object getValue(String key) {
        Object value = cacheContainer.getCache().get(key);

        return value;
    }
}

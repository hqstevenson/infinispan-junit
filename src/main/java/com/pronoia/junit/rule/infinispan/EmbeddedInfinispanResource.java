package com.pronoia.junit.rule.infinispan;

import java.util.concurrent.TimeUnit;

import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.DefaultCacheManager;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmbeddedInfinispanResource extends ExternalResource {
    Logger log = LoggerFactory.getLogger(this.getClass());

    DefaultCacheManager cacheManager;

    ConfigurationBuilder config = new ConfigurationBuilder();

    @Override
    protected void before() throws Throwable {
        cacheManager = new DefaultCacheManager(config.build(), false);
        cacheManager.start();
    }

    @Override
    protected void after() {
        cacheManager.stop();
    }

    public void setExpiration(int value, TimeUnit timeUnit) {
        config.expiration().lifespan(5, TimeUnit.SECONDS);
    }

}

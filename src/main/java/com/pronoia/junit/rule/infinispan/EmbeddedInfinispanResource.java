package com.pronoia.junit.rule.infinispan;

import org.infinispan.manager.DefaultCacheManager;
import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmbeddedInfinispanResource extends ExternalResource {
    Logger log = LoggerFactory.getLogger(this.getClass());

    DefaultCacheManager cacheManager;

    @Override
    protected void before() throws Throwable {
        cacheManager = new DefaultCacheManager(false);
        cacheManager.start();
    }

    @Override
    protected void after() {
        // TODO:  Stop Infinispan Server
        cacheManager.stop();
    }
}

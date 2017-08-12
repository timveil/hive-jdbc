package veil.hdp.hive.jdbc.thrift;


import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import veil.hdp.hive.jdbc.Builder;
import veil.hdp.hive.jdbc.bindings.TCLIService;
import veil.hdp.hive.jdbc.bindings.TOpenSessionResp;
import veil.hdp.hive.jdbc.bindings.TProtocolVersion;
import veil.hdp.hive.jdbc.bindings.TSessionHandle;
import veil.hdp.hive.jdbc.utils.ThriftUtils;

import java.io.Closeable;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ThriftSession implements Closeable {

    private static final Logger log = LoggerFactory.getLogger(ThriftSession.class);

    // constructor
    private final ThriftTransport thriftTransport;
    private final TCLIService.Client client;
    private final TSessionHandle sessionHandle;
    private final Properties properties;

    // atomic
    private final AtomicBoolean closed = new AtomicBoolean(true);
    private final ReentrantLock sessionLock = new ReentrantLock(true);


    private ThriftSession(Properties properties, ThriftTransport thriftTransport, TCLIService.Client client, TSessionHandle sessionHandle) {
        this.properties = properties;
        this.thriftTransport = thriftTransport;
        this.client = client;
        this.sessionHandle = sessionHandle;

        closed.set(false);
    }

    public static ThriftSessionBuilder builder() {
        return new ThriftSessionBuilder();
    }

    public TCLIService.Client getClient() {
        return client;
    }

    public TSessionHandle getSessionHandle() {
        return sessionHandle;
    }

    public boolean isClosed() {
        return closed.get();
    }

    public ReentrantLock getSessionLock() {
        return sessionLock;
    }

    public Properties getProperties() {
        return properties;
    }

    /**
     * Determines if the ThriftSession is in a valid state to execute another Thrift call. It checks both the closed flag as well as the underlying thrift transport status.
     * @return true if valid, false if not valid
     */
    public boolean isValid() {
        return !closed.get() && thriftTransport.isValid();
    }

    @Override
    public void close() throws IOException {
        if (closed.compareAndSet(false, true)) {

            if (log.isTraceEnabled()) {
                log.trace("attempting to close {}", this.getClass().getName());
            }

            ThriftUtils.closeSession(this);

            thriftTransport.close();
        }
    }

    public static class ThriftSessionBuilder implements Builder<ThriftSession> {
        private Properties properties;

        private ThriftSessionBuilder() {
        }

        public ThriftSessionBuilder properties(Properties properties) {
            this.properties = properties;
            return this;
        }


        @Override
        public ThriftSession build() {

            ThriftTransport thriftTransport = ThriftTransport.builder().properties(properties).build();

            TCLIService.Client client = ThriftUtils.createClient(thriftTransport);

            TOpenSessionResp openSessionResp = ThriftUtils.openSession(properties, client);

            TSessionHandle sessionHandle = openSessionResp.getSessionHandle();

            return new ThriftSession(properties, thriftTransport, client, sessionHandle);
        }

    }
}

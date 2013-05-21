package net.lmxm.ute;

import net.lmxm.ute.beans.configuration.Configuration;
import net.lmxm.ute.configuration.ConfigurationHolder;

/**
 * Configuration holder instances used for testing purposes.
 */
public final class TestConfigurationHolders {
    public static final ConfigurationHolder BLANK_CONFIGURATION = new ConfigurationHolder() {
        private final Configuration configuration = new Configuration();

        @Override
        public Configuration getConfiguration() {
            return configuration;
        }
    };

    public static final ConfigurationHolder CONFIGURATION_ALWAYS_NULL = new ConfigurationHolder() {
        @Override
        public Configuration getConfiguration() {
            return null;
        }
    };
}

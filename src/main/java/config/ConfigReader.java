package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Simple configuration reader.
 * Priority:
 * 1) System property (eg. -Dbase.url=...)
 * 2) src/main/resources/config.properties if present
 * 3) defaults coded below
 */
public class ConfigReader {
    private final Properties props = new Properties();

    public ConfigReader() {
        // load resource properties if available
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) {
                props.load(is);
            }
        } catch (IOException e) {
            // ignore - we'll rely on defaults or system properties
        }
    }

    public String getBaseUrl() {
        String sys = System.getProperty("base.url");
        if (sys != null) return sys;
        return props.getProperty("base.url", "https://example.com");
    }

    public String getBrowser() {
        String sys = System.getProperty("browser");
        if (sys != null) return sys;
        return props.getProperty("browser", "chrome");
    }

    // Add other config getters as needed
}

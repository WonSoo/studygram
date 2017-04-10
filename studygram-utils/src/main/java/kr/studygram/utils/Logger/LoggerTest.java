package kr.studygram.utils.Logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.xml.XmlConfigurationFactory;

/**
 * Created by production on 2017-04-07.
 */
public enum LoggerTest {
    INSTANCE;
    private Logger logger;
    private String LOGGER_CONFIG_PATH = LoggerTest.class.getClassLoader().getResource("log4j2.xml").toString();

    LoggerTest()
    {
        System.setProperty(XmlConfigurationFactory.CONFIGURATION_FILE_PROPERTY,LOGGER_CONFIG_PATH);
        logger = LogManager.getLogger("SecureConfig");
    }

    public void log(String level, String message)
    {
        logger.log(Level.toLevel(level), message);
    }

    public Logger getLogger() {
        return LogManager.getLogger();
    }

    public Logger getLogger(String name) {
        return LogManager.getLogger(name);
    }

    public static LoggerTest getInstance() {
        return INSTANCE;
    }
}

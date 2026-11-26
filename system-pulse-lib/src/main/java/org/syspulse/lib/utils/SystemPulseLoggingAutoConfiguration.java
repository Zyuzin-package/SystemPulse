package org.syspulse.lib.utils;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.syspulse.lib.core.ConsoleErrorLogHandler;
import org.syspulse.lib.core.ErrorLogHandler;
import org.syspulse.lib.core.SystemPulseLogbackAppender;

/**
 * Регистрирует appender SystemPulse в корневом logger Logback приложений Spring Boot.
 */
@AutoConfiguration
public class SystemPulseLoggingAutoConfiguration {

    @Bean
    ErrorLogHandler consoleErrorLogHandler() {
        return new ConsoleErrorLogHandler();
    }

    @Bean
    SystemPulseLogbackAppender systemPulseLogbackAppender(ErrorLogHandler errorLogHandler) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger rootLogger = loggerContext.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        SystemPulseLogbackAppender appender = new SystemPulseLogbackAppender();
        appender.setName(SystemPulseLogbackAppender.APPENDER_NAME);
        appender.setContext(loggerContext);
        appender.setErrorLogHandler(errorLogHandler);
        appender.start();
        rootLogger.addAppender(appender);
        return appender;
    }
}

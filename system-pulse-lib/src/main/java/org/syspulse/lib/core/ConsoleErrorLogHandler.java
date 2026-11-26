package org.syspulse.lib.core;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;

import java.time.Instant;

/**
 * Выводит перехваченные ошибки в стандартный поток вывода до появления удалённого приёмника SystemPulse.
 */
public class ConsoleErrorLogHandler implements ErrorLogHandler {

    @Override
    public void handle(ILoggingEvent event) {
        System.out.printf(
                "SystemPulse captured ERROR%n" +
                        "  timestamp: %s%n" +
                        "  logger: %s%n" +
                        "  message: %s%n" +
                        "  mdc: %s%n",
                Instant.ofEpochMilli(event.getTimeStamp()),
                event.getLoggerName(),
                event.getFormattedMessage(),
                event.getMDCPropertyMap()
        );

        if (event.getThrowableProxy() != null) {
            System.out.println(ThrowableProxyUtil.asString(event.getThrowableProxy()));
        }
    }
}

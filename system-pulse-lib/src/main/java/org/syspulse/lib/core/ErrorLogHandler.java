package org.syspulse.lib.core;

import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * Принимает события Logback уровня {@code ERROR}.
 */
@FunctionalInterface
public interface ErrorLogHandler {

    void handle(ILoggingEvent event);
}

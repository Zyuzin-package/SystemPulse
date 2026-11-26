package org.syspulse.lib.core;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

import java.util.Objects;

/**
 * Принимает записи {@code ERROR} из SLF4J и передаёт их обработчику SystemPulse.
 */
public class SystemPulseLogbackAppender extends AppenderBase<ILoggingEvent> {

    public static final String APPENDER_NAME = "SYSTEM_PULSE";

    private ErrorLogHandler errorLogHandler;

    public void setErrorLogHandler(ErrorLogHandler errorLogHandler) {
        this.errorLogHandler = Objects.requireNonNull(errorLogHandler, "errorLogHandler must not be null");
    }

    @Override
    public void start() {
        if (errorLogHandler == null) {
            throw new IllegalStateException("errorLogHandler must be configured before the appender starts");
        }
        super.start();
    }

    @Override
    protected void append(ILoggingEvent event) {
        if (Level.ERROR.equals(event.getLevel())) {
            errorLogHandler.handle(event);
        }
    }
}

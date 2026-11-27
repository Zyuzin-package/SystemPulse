package org.syspulse.lib.core;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import org.syspulse.model.RawLogEvent;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

/**
 * Выводит перехваченные ошибки в стандартный поток вывода до появления удалённого приёмника SystemPulse.
 */
public class ConsoleErrorLogHandler implements ErrorLogHandler {

    private final SystemPulseLogClient systemPulseLogClient;
    private final String serviceName;
    private final String environment;
    private final String serviceVersion;

    public ConsoleErrorLogHandler(SystemPulseLogClient systemPulseLogClient, String serviceName, String environment, String serviceVersion) {
        this.systemPulseLogClient = systemPulseLogClient;
        this.serviceName = serviceName;
        this.environment = environment;
        this.serviceVersion = serviceVersion;
    }

    @Override
    public void handle(ILoggingEvent event) {
        String stackTrace = event.getThrowableProxy() == null
                ? null
                : ThrowableProxyUtil.asString(event.getThrowableProxy());

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

        if (stackTrace != null) {
            System.out.println(stackTrace);
        }

        systemPulseLogClient.send(RawLogEvent.builder()
                .eventId(UUID.randomUUID())
                .occurredAt(OffsetDateTime.ofInstant(Instant.ofEpochMilli(event.getTimeStamp()), ZoneOffset.UTC))
                .level(event.getLevel().toString())
                .serviceName(serviceName)
                .environment(environment)
                .serviceVersion(serviceVersion)
                .logger(event.getLoggerName())
                .message(event.getFormattedMessage())
                .exceptionClass(event.getThrowableProxy() == null ? null : event.getThrowableProxy().getClassName())
                .stackTrace(stackTrace)
                .traceId(event.getMDCPropertyMap().get("traceId"))
                .errorCode(event.getMDCPropertyMap().get("errorCode"))
                .build());
    }
}

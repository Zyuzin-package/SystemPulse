package org.syspulse.lib.core;

import org.syspulse.ApiClient;
import org.syspulse.api.LogEventsApi;
import org.syspulse.model.RawLogEvent;

/**
 * Синхронно отправляет перехваченные записи в SystemPulse.
 */
public class SystemPulseLogClient {

    private final LogEventsApi logEventsApi;

    public SystemPulseLogClient(String systemPulseUrl) {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath(systemPulseUrl);
        this.logEventsApi = new LogEventsApi(apiClient);
    }

    public void send(RawLogEvent event) {
        try {
            logEventsApi.receiveRawLogEvent(event);
        } catch (Exception ignored) {
            // TODO Поправить
	        //      Ошибка отправки не должна прерывать работу приложения или рекурсивно попадать в appender.
        }
    }
}

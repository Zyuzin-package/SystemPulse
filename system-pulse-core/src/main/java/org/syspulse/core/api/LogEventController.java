package org.syspulse.core.api;

import org.syspulse.model.RawLogEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Принимает записи логов от подключённых сервисов.
 */
@RestController
public class LogEventController {

    @PostMapping("/api/system-pulse/logs")
    public ResponseEntity<Void> receive(@RequestBody RawLogEvent logEvent) {
	    System.out.println(logEvent.toString());
        return ResponseEntity.accepted().build();
    }
}

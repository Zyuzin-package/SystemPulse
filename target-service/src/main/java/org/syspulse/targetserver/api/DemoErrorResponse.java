package org.syspulse.targetserver.api;

import java.time.Instant;

record DemoErrorResponse(Instant timestamp, int status, String path, String message) {
}

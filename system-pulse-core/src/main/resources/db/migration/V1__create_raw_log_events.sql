CREATE TABLE raw_log_events (
    event_id UUID PRIMARY KEY,
    occurred_at TIMESTAMP WITH TIME ZONE NOT NULL,
    log_level VARCHAR(16) NOT NULL,
    service_name VARCHAR(255) NOT NULL,
    environment VARCHAR(255) NOT NULL,
    service_version VARCHAR(255) NOT NULL,
    logger VARCHAR(1024) NOT NULL,
    message TEXT NOT NULL,
    exception_class VARCHAR(1024),
    stack_trace TEXT,
    trace_id VARCHAR(255),
    error_code VARCHAR(255)
);

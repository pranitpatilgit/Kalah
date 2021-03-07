package com.pranitpatil.kalah.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "application.kalah")
public class KalahProperties {

    private int initialPitCount;

    public int getInitialPitCount() {
        return initialPitCount;
    }

    public void setInitialPitCount(int initialPitCount) {
        this.initialPitCount = initialPitCount;
    }
}

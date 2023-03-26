package dev.softice.slice.config.properties;

import org.springframework.boot.context.properties.bind.ConstructorBinding;

public record AwsCredentials(String accessKey, String secretKey) {
    @ConstructorBinding
    public AwsCredentials {
    }
}

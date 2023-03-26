package dev.softice.slice.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@OverrideAutoConfiguration(enabled = false)
@ImportAutoConfiguration(dev.softice.slice.config.DynamoDbConfig.class)
@TypeExcludeFilters(DynamoDbTypeExcludeFilter.class)
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DynamoDbTest {

    Class<?>[] repositories() default {};
}

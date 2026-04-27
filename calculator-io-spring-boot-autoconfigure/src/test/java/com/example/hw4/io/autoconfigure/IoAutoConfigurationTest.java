package com.example.hw4.io.autoconfigure;

import com.example.hw4.io.IoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class IoAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(IoAutoConfiguration.class));

    @Test
    void shouldCreateConsoleIoServiceByDefault() {
        contextRunner.run(context -> assertThat(context).hasSingleBean(IoService.class));
    }

    @Test
    void shouldBackOffWhenUserProvidesIoService() {
        contextRunner
                .withBean(IoService.class, StubIoService::new)
                .run(context -> assertThat(context.getBean(IoService.class)).isInstanceOf(StubIoService.class));
    }

    @Test
    void shouldNotCreateIoServiceWhenStarterIsDisabled() {
        contextRunner
                .withPropertyValues("calculator.io.enabled=false")
                .run(context -> assertThat(context).doesNotHaveBean(IoService.class));
    }

    private static class StubIoService implements IoService {
        @Override
        public String readLine() {
            return "";
        }

        @Override
        public void print(String text) {
        }

        @Override
        public void println(String text) {
        }
    }
}

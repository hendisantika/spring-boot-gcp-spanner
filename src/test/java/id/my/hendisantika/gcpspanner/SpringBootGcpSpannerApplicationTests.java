package id.my.hendisantika.gcpspanner;

import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.NoCredentials;
import com.google.cloud.spanner.DatabaseAdminClient;
import com.google.cloud.spanner.Spanner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@SpringBootTest
class SpringBootGcpSpannerApplicationTests {

    @MockitoBean
    Spanner spanner;

    @MockitoBean
    DatabaseAdminClient databaseAdminClient;

    @Test
    void contextLoads() {
    }

    @TestConfiguration
    static class NoGcpCredentialsConfig {
        @Bean
        CredentialsProvider googleCredentials() {
            return NoCredentials::getInstance;
        }
    }

}

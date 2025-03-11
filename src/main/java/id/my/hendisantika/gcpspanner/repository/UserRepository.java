package id.my.hendisantika.gcpspanner.repository;

import com.google.cloud.spring.data.spanner.repository.SpannerRepository;
import com.google.cloud.spring.data.spanner.repository.query.Query;
import id.my.hendisantika.gcpspanner.entity.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-gcp-spanner
 * User: hendisantika
 * Link: s.id/hendisantika
 * Email: hendisantika@yahoo.co.id
 * Telegram : @hendisantika34
 * Date: 11/03/25
 * Time: 07.12
 * To change this template use File | Settings | File Templates.
 */
public interface UserRepository extends SpannerRepository<User, String> {

    List<User> findByCountry(String country);

    @Query("SELECT * FROM users WHERE name = @name")
    List<User> findByName(@Param("name") String userName);
}

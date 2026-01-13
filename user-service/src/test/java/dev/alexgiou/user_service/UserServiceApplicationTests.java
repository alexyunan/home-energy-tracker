package dev.alexgiou.user_service;

import dev.alexgiou.user_service.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceApplicationTests {

  public static final int NUMBER_OF_USERS = 10;
  private static final Logger log = LoggerFactory.getLogger(UserServiceApplicationTests.class);
  @Autowired
  private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

  @Disabled
  @Test
  void createUsers() {
    // create random users
    for (int i = 0; i < NUMBER_OF_USERS; i++) {
      var user = dev.alexgiou.user_service.entity.User.builder()
          .name("User" + i)
          .surname("Surname" + i)
          .email("user" + i + "@example.com")
          .address("Address" + i)
          .alerting(i % 2 == 0)
          .energyAlertingThreshold(100 + (i * 10))
          .build();
      userRepository.save(user);
    }
    log.info("Users created successfully");
  }
}

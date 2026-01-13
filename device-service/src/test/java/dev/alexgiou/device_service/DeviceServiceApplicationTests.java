package dev.alexgiou.device_service;

import dev.alexgiou.device_service.entity.Device;
import dev.alexgiou.device_service.model.DeviceType;
import dev.alexgiou.device_service.repository.DeviceRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeviceServiceApplicationTests {

  public static final int NUMBER_OF_DEVICES = 10;
  public static final int USERS = 10;
  private static final Logger log = LoggerFactory.getLogger(DeviceServiceApplicationTests.class);
  @Autowired
  private DeviceRepository deviceRepository;

	@Test
	void contextLoads() {
	}


  @Disabled
  @Test
  void createDevices() {
    //create random devices
    for (int i = 0; i < NUMBER_OF_DEVICES; i++) {
      var device = Device.builder()
          .name("Device" + i)
          .type(DeviceType.values()[i % DeviceType.values().length])
          .location("Location" + (i % 3) + 1)
          .userId((long) (i % USERS) + 1)
          .build();
      deviceRepository.save(device);
    }
    log.info("Devices created successfully");
  }
}

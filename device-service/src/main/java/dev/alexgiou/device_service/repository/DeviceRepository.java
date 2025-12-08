package dev.alexgiou.device_service.repository;

import dev.alexgiou.device_service.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long> {

}

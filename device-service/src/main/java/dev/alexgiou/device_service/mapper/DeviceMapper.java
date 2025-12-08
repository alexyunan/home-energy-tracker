package dev.alexgiou.device_service.mapper;

import dev.alexgiou.device_service.dto.DeviceDto;
import dev.alexgiou.device_service.entity.Device;
import org.springframework.stereotype.Component;

@Component
public class DeviceMapper {

  public DeviceDto toDto(Device device) {
    return DeviceDto.builder()
        .id(device.getId())
        .name(device.getName())
        .type(device.getType())
        .location(device.getLocation())
        .userId(device.getUserId())
        .build();
  }

  public Device toEntity(DeviceDto dto) {
    return Device.builder()
        .id(dto.id())
        .name(dto.name())
        .type(dto.type())
        .location(dto.location())
        .userId(dto.userId())
        .build();
  }

}

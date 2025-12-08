package dev.alexgiou.device_service.service;

import dev.alexgiou.device_service.dto.DeviceDto;
import dev.alexgiou.device_service.entity.Device;
import dev.alexgiou.device_service.exception.DeviceNotFoundException;
import dev.alexgiou.device_service.mapper.DeviceMapper;
import dev.alexgiou.device_service.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeviceService {

  private final DeviceRepository deviceRepository;
  private final DeviceMapper deviceMapper;

  public DeviceDto getDeviceById(Long id) {
    return deviceRepository.findById(id)
        .map(deviceMapper::toDto)
        .orElseThrow(() -> new DeviceNotFoundException("Device not found with id: " + id));
  }

  public DeviceDto createDevice(DeviceDto deviceDto) {
    Device device = deviceMapper.toEntity(deviceDto);
    Device savedDevice = deviceRepository.save(device);
    return deviceMapper.toDto(savedDevice);
  }

  public void updateDevice(Long id, DeviceDto deviceDto) {
    Device existingDevice = deviceRepository.findById(id)
        .orElseThrow(() -> new DeviceNotFoundException("Device not found with id: " + id));
    Device updatedDevice = deviceMapper.toEntity(deviceDto);

    updatedDevice.setType(existingDevice.getType());
    updatedDevice.setName(existingDevice.getName());
    updatedDevice.setLocation(existingDevice.getLocation());
    updatedDevice.setUserId(existingDevice.getUserId());
    deviceRepository.save(updatedDevice);
  }

  public void deleteDevice(Long id) {
    Device device = deviceRepository.findById(id)
        .orElseThrow(() -> new DeviceNotFoundException("Device not found with id: " + id));
    deviceRepository.delete(device);
  }
}

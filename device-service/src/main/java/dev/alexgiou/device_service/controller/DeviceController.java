package dev.alexgiou.device_service.controller;

import dev.alexgiou.device_service.dto.DeviceDto;
import dev.alexgiou.device_service.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/device")
@RequiredArgsConstructor
public class DeviceController {

  private final DeviceService deviceService;

  @GetMapping("/{id}")
  public ResponseEntity<DeviceDto> getDeviceById(@PathVariable Long id) {
    DeviceDto device = deviceService.getDeviceById(id);
    if (device != null) {
      return ResponseEntity.ok(device);
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<DeviceDto> createDevice(@RequestBody  DeviceDto deviceDto) {
    DeviceDto createdDevice = deviceService.createDevice(deviceDto);
    return new ResponseEntity<>(createdDevice,HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateDevice(@PathVariable Long id, @RequestBody DeviceDto deviceDto){
    try {
       deviceService.updateDevice(id, deviceDto);
      return new ResponseEntity<>("Device updated successfully",HttpStatus.OK);
    }catch (Exception e){
      return new ResponseEntity<>("Error updating device: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDevice(@PathVariable Long id){
    try {
      deviceService.deleteDevice(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}

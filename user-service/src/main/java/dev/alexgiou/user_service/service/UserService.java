package dev.alexgiou.user_service.service;

import dev.alexgiou.user_service.dto.UserDto;
import dev.alexgiou.user_service.entity.User;
import dev.alexgiou.user_service.mapper.UserMapper;
import dev.alexgiou.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserDto createUser(UserDto userDto) {
    User user = userMapper.toEntity(userDto);
    User savedUser = userRepository.save(user);
    return userMapper.toDto(savedUser);
  }

  public UserDto getUserById(Long id) {
    return userRepository.findById(id)
        .map(userMapper::toDto)
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
  }

  public void updateUser(Long id, UserDto userDto) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

    user.setName(userDto.name());
    user.setSurname(userDto.surname());
    user.setEmail(userDto.email());
    user.setAddress(userDto.address());
    user.setAlerting(userDto.alerting());
    user.setEnergyAlertingThreshold(userDto.energyAlertingThreshold());

    userRepository.save(user);
  }

  public void deleteUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));
    userRepository.delete(user);
  }
}

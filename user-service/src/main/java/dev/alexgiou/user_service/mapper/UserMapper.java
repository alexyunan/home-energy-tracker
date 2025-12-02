package dev.alexgiou.user_service.mapper;

import dev.alexgiou.user_service.dto.UserDto;
import dev.alexgiou.user_service.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserDto toDto(User user) {

    return UserDto.builder()
        .id(user.getId())
        .surname(user.getSurname())
        .name(user.getName())
        .email(user.getEmail())
        .address(user.getAddress())
        .alerting(user.isAlerting())
        .energyAlertingThreshold(user.getEnergyAlertingThreshold())
        .build();
  }

  public User toEntity(UserDto userDto) {

    return User.builder()
        .id(userDto.id())
        .surname(userDto.surname())
        .name(userDto.name())
        .email(userDto.email())
        .address(userDto.address())
        .alerting(userDto.alerting())
        .energyAlertingThreshold(userDto.energyAlertingThreshold())
        .build();
  }

}

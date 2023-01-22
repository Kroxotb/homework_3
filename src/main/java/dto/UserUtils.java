package dto;

import com.github.javafaker.Faker;

public class UserUtils {

  static Faker faker = new Faker();

  public static UserDTO userDTO() {
    return UserDTO.builder()
        .username(faker.name().username())
        .firstName(faker.name().firstName())
        .lastName(faker.name().lastName())
        .email(faker.internet().emailAddress())
        .phone(faker.phoneNumber().phoneNumber())
        .password(faker.internet().password())
        .id(Math.abs(faker.random().nextLong()))
        .userStatus(Math.abs(faker.random().nextLong(1000L)))
        .build();
  }

}
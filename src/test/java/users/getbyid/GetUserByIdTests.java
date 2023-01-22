package users.getbyid;

import static org.hamcrest.Matchers.equalTo;

import com.google.inject.Guice;
import com.google.inject.Inject;
import dto.UserDTO;
import dto.UserUtils;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.UserApi;
import support.UserModule;

public class GetUserByIdTests {


  @Inject
  public UserApi userApi;
  UserDTO userGettingResponse;
  private UserDTO user;

  @BeforeEach
  public void createUserForTest() {

    Guice.createInjector(new UserModule()).injectMembers(this);
    user = UserUtils.userDTO();
    userApi.createUser(user).body("code", equalTo(200));
  }

  @Test
  public void getExistedUserByIdUserTest() {
    getUserById();
    checkEqualsData();
    printOnSuccessfulTest("Успешно!");
  }

  @Step("Найти пользователя по уникальному id")
  public void getUserById() {
    userGettingResponse = userApi.getUserByName(user.getUsername())
        .then()
        .statusCode(200)
        .log().all()
        .extract().body().as(UserDTO.class);
  }

  @Step("Проверка, что данные совпадают")
  public void checkEqualsData() {
    Assertions.assertEquals(user.getId(), userGettingResponse.getId(),
        "Неверное значение id");
    Assertions.assertEquals(user.getUserStatus(), userGettingResponse.getUserStatus(),
        "Неверное содержимое userStatus");
    Assertions.assertEquals(user.getUsername(), userGettingResponse.getUsername(),
        "Неверное содержимое username");
    Assertions.assertEquals(user.getFirstName(), userGettingResponse.getFirstName(),
        "Неверное содержимое firstName");
    Assertions.assertEquals(user.getLastName(), userGettingResponse.getLastName(),
        "Неверное содержимое lastName");
    Assertions.assertEquals(user.getPhone(), userGettingResponse.getPhone(),
        "Неверное содержимое phone");
    Assertions.assertEquals(user.getEmail(), userGettingResponse.getEmail(),
        "Неверное содержимое email");
  }

  @Step(" {0}")
  public static void printOnSuccessfulTest(String testName) {
  }
}

package users.getbyid;

import com.google.inject.Guice;
import com.google.inject.Inject;
import dto.UserOut;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.UserApi;
import support.UserModule;

public class GetUserWithErrorTests {

  @Inject
  private UserApi userApi;
  UserOut userCreationResponse;

  @BeforeEach
  public void setup() {
    Guice.createInjector(new UserModule()).injectMembers(this);
  }

  @Test
  public void getExistedUserByIdUserTest() {
    getUserByIdWithError();
    checkEqualsData();
    printOnSuccessfulTest("Успешно!");
  }

  @Step("Найти пользователя по уникальному id")
  public void getUserByIdWithError() {
    final String notExistedUserName = "not_existed_user_" + System.currentTimeMillis();

    userCreationResponse = userApi.getUserByName(notExistedUserName)
        .then()
        .statusCode(404)
        .log().all()
        .extract().body().as(UserOut.class);
  }

  @Step("Проверка, что данные совпадают")
  public void checkEqualsData() {
    Assertions.assertEquals(1L, userCreationResponse.getCode(),
        "Неверное значение code");
    Assertions.assertEquals("error", userCreationResponse.getType(),
        "Неверное содержимое type");
    Assertions.assertEquals("User not found", userCreationResponse.getMessage(),
        "Неверное содержимое message");
  }

  @Step(" {0}")
  public static void printOnSuccessfulTest(String testName) {
  }

}
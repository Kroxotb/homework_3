package users.create;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import com.google.inject.Guice;
import com.google.inject.Inject;
import dto.UserOut;
import io.qameta.allure.Step;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.UserApi;
import support.UserModule;

public class CreateUserWithInvalidIdTests {

  @Inject
  public UserApi userApi;
  private UserOut userCreationResponse;

  @BeforeEach
  public void setup() {
    Guice.createInjector(new UserModule()).injectMembers(this);
  }

  @Test
  public void createUserWithInvalidIdTest() {

    createUserWithInvalidId();
    checkEqualsData();
    printOnSuccessfulTest("Успешно!");
  }

  @Step("Создание нового пользователя с ошибкой")
  public void createUserWithInvalidId() {
    userCreationResponse = userApi
        .createUserWithError("{ \"id\": \"Пишем String вместо Long\"}")
        .statusCode(500)
        .time(lessThan(5000L))
        .body("message", equalTo("something bad happened"))
        .body("code", equalTo(500))
        .body("type", equalTo("unknown"))
        .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema/CreateUser.json"))
        .extract().body().as(UserOut.class);
  }

  @Step("Проверка, что данные совпадают")
  public void checkEqualsData() {
    Assertions.assertEquals(500L, userCreationResponse.getCode(), "Неверное значение code");
    Assertions.assertEquals("unknown", userCreationResponse.getType(), "Неверное содержимое type");
    Assertions.assertEquals("something bad happened", userCreationResponse.getMessage(),
        "Неверное содержимое message");
  }

  @Step(" {0}")
  public static void printOnSuccessfulTest(String testName) {
  }
}

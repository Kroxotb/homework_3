package dto;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {
  private String email;
  private String firstName;
  private Long id;
  private String lastName;
  private String password;
  private String phone;
  private Long userStatus;
  private String username;
}

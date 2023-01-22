package dto;

import lombok.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@Builder
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserOut {

  private Long code;
  private String message;
  private String type;
}

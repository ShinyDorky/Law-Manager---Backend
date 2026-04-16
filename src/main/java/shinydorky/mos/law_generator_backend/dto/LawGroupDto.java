package shinydorky.mos.law_generator_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawGroupDto{
    private Long id;

    @NotBlank
    @NotNull
    private String signature;
    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String desc;
}

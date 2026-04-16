package shinydorky.mos.law_generator_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawTypeDto {
    private Long id;

    @NotBlank
    @NotNull
    private String signature;
    @NotBlank
    @NotNull
    private String name;
}

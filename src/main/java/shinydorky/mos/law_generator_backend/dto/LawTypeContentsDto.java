package shinydorky.mos.law_generator_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shinydorky.mos.law_generator_backend.model.LawGroup;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LawTypeContentsDto {
    private Long id;

    @NotBlank
    @NotNull
    private String signature;
    @NotBlank
    @NotNull
    private String name;
    private Set<LawGroupDto> lawGroups;
}

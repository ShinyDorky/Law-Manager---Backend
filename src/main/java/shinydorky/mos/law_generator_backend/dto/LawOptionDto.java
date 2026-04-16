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
public class LawOptionDto {
    private Long id;
    @NotBlank
    private String signature;
    @NotBlank
    private String name;
    @NotBlank
    private String desc;
    @NotBlank
    private String canKeep;
    @NotBlank
    private String canPass;
    @NotNull
    private Integer placeInOrder;
    private String passCost;
    private String onPass;
    @NotNull
    private boolean isDefault;
}

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
    @NotNull
    @NotBlank
    private String signature;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String desc;
    @NotNull
    private String canKeep;
    @NotNull
    private String canPass;
    @NotNull
    private String effects;
    @NotNull
    private Integer placeInOrder;
    private String passCost;
    private String onPass;
    @NotNull
    private boolean isDefault;

    private Integer statePowerOpinion;
    private Integer militaryOpinion;
    private Integer religiousUnityOpinion;
    private Integer culturalToleranceOpinion;
    private Integer populismOpinion;
}

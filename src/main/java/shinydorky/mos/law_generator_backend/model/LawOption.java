package shinydorky.mos.law_generator_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LawOption {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @NotNull
    private String name;
    @NotBlank
    @NotNull
    private String signature;
    @NotBlank
    @NotNull
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
//    private boolean isDefault;


    private Integer statePowerOpinion;
    private Integer militaryOpinion;
    private Integer religiousUnityOpinion;
    private Integer culturalToleranceOpinion;
    private Integer populismOpinion;

    @ManyToOne
    @JoinColumn(name="law_group_id")
    private LawGroup lawGroup;
}

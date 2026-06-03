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
    private String name; // how will it appear in display
    @NotBlank
    @NotNull
    private String signature; // how will it appear in code
    @NotBlank
    @NotNull
    private String desc; // how will it be described
    @NotNull
    private String canKeep; // conditions for keeping this law
    @NotNull
    private String canPass; // conditions for passing this law
    @NotNull
    private String effects; // what modifiers will this law apply
    @NotNull
    private Integer placeInOrder; // order in law group, used to determine from which laws you can pass this law
    private String passCost; // price of passing law
    private String onPass; // what will happen when this law is passed
//    private boolean isDefault;


    //Law opinion multipliers - how much the followers of particular ideas will like or dislike this law
    private Integer statePowerOpinion;
    private Integer militaryOpinion;
    private Integer religiousUnityOpinion;
    private Integer culturalToleranceOpinion;
    private Integer populismOpinion;

    @ManyToOne
    @JoinColumn(name="law_group_id")
    private LawGroup lawGroup;
}

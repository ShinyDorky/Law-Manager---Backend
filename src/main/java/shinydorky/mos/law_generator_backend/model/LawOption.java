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
    private String desc;
    @NotBlank
    @NotNull
    private String canKeep;
    @NotBlank
    @NotNull
    private String canPass;
    private String passCost;
    private String onPass;
    @NotNull
    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name="law_group_id")
    private LawGroup lawGroup;
}

package shinydorky.mos.law_generator_backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LawGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToMany(mappedBy = "lawGroup", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<LawOption> lawOptions;

    @ManyToOne
    @JoinColumn(name="law_type_id")
    private LawType lawType;
}

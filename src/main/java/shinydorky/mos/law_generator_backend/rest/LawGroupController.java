package shinydorky.mos.law_generator_backend.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shinydorky.mos.law_generator_backend.dto.LawGroupContentsDto;
import shinydorky.mos.law_generator_backend.dto.LawGroupDto;
import shinydorky.mos.law_generator_backend.dto.LawOptionDto;
import shinydorky.mos.law_generator_backend.model.LawGroup;
import shinydorky.mos.law_generator_backend.model.LawOption;
import shinydorky.mos.law_generator_backend.model.LawType;
import shinydorky.mos.law_generator_backend.repository.LawGroupRepository;
import shinydorky.mos.law_generator_backend.repository.LawOptionRepository;
import shinydorky.mos.law_generator_backend.repository.LawTypeRepository;

import java.net.URI;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lawGroup")
public class LawGroupController {
    private final ModelMapper modelMapper;
    private final LawGroupRepository lawGroupRepository;
    private final LawTypeRepository lawTypeRepository;
    private final LawOptionRepository lawOptionRepository;



    @GetMapping
    public ResponseEntity<Collection<LawGroupDto>> getLawGroups(){
        List<LawGroup> allLawGroups = lawGroupRepository.findAll();
        List<LawGroupDto> result = allLawGroups.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{lawGroupId}")
    public ResponseEntity<LawGroupContentsDto> getLawGroupById(@PathVariable Long lawGroupId){
        Optional<LawGroup> lawGroup = lawGroupRepository.findById(lawGroupId);
        if (lawGroup.isPresent()){
            LawGroupContentsDto lawGroupDto = convertToContentsDto(lawGroup.get());
            return new ResponseEntity<>(lawGroupDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{lawGroupId}/lawOptions")
    public ResponseEntity<Collection<LawOptionDto>> getLawGroupsByLawType(@PathVariable long lawGroupId){
        List<LawOption> allLawOptions = lawOptionRepository.getLawOptionByGroupId(lawGroupId);
        List<LawOptionDto> result = allLawOptions.stream()
                .map(e -> {return modelMapper.map(e, LawOptionDto.class);})
                .sorted(new Comparator<LawOptionDto>() {
                    @Override
                    public int compare(LawOptionDto o1, LawOptionDto o2) {
                        return o1.getPlaceInOrder() - o2.getPlaceInOrder();
                    }
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{lawTypeId}")
    public ResponseEntity saveNewLawGroup(@Valid @RequestBody LawGroupDto lawGroupDto, @PathVariable Long lawTypeId){
        LawGroup entity = convertToEntity(lawGroupDto);

        Optional<LawType> lawType = lawTypeRepository.findById(lawTypeId);
        if (lawType.isEmpty()){
            return new ResponseEntity<>("There is no LawType with such ID.", HttpStatus.NOT_FOUND);
        }

        lawType.get().getLawGroups().add(entity);
        entity.setLawType(lawType.get());
        lawGroupRepository.save(entity);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        headers.add("Location", location.toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{lawGroupId}")
    public ResponseEntity updateLawGroup(@PathVariable Long lawGroupId, @Valid @RequestBody LawGroupDto lawGroupDto){
        Optional<LawGroup> currentDish = lawGroupRepository.findById(lawGroupId);
        if (currentDish.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        lawGroupDto.setId(lawGroupId);
        LawGroup entity = convertToEntity(lawGroupDto);
        entity.setLawType(currentDish.get().getLawType());
        lawGroupRepository.save(entity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{lawGroupId}")
    public ResponseEntity deleteLawGroup(@PathVariable Long lawGroupId){
        boolean found = lawGroupRepository.existsById(lawGroupId);
        if (found){
            lawGroupRepository.deleteById(lawGroupId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private LawGroupDto convertToDto(LawGroup e) {
        return modelMapper.map(e, LawGroupDto.class);
    }
    private LawGroupContentsDto convertToContentsDto(LawGroup e) {
        return modelMapper.map(e, LawGroupContentsDto.class);
    }
    private LawGroup convertToEntity(LawGroupDto dto) {
        return modelMapper.map(dto, LawGroup.class);
    }
}

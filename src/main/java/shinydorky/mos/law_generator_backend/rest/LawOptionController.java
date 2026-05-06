package shinydorky.mos.law_generator_backend.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shinydorky.mos.law_generator_backend.dto.LawOptionDto;
import shinydorky.mos.law_generator_backend.model.LawGroup;
import shinydorky.mos.law_generator_backend.model.LawOption;
import shinydorky.mos.law_generator_backend.repository.LawGroupRepository;
import shinydorky.mos.law_generator_backend.repository.LawOptionRepository;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lawOption")
public class LawOptionController {
    private final ModelMapper modelMapper;
    private final LawOptionRepository lawOptionRepository;
    private final LawGroupRepository lawGroupRepository;



    @GetMapping
    public ResponseEntity<Collection<LawOptionDto>> getLawOptions(){
        List<LawOption> allLawGroups = lawOptionRepository.findAll();
        List<LawOptionDto> result = allLawGroups.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{lawOptionId}")
    public ResponseEntity<LawOptionDto> getLawOptionById(@PathVariable Long lawOptionId){
        Optional<LawOption> lawGroup = lawOptionRepository.findById(lawOptionId);
        if (lawGroup.isPresent()){
            LawOptionDto lawGroupDto = convertToDto(lawGroup.get());
            return new ResponseEntity<>(lawGroupDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{lawGroupId}")
    public ResponseEntity saveNewLawOption(@Valid @RequestBody LawOptionDto lawOptionDto, @PathVariable Long lawGroupId){
        LawOption entity = convertToEntity(lawOptionDto);
        Optional<LawGroup> lawGroup = lawGroupRepository.findById(lawGroupId);
        if (lawGroup.isEmpty()){
            return new ResponseEntity<>("There is no LawGroup with such ID.", HttpStatus.NOT_FOUND);
        }
        entity.setLawGroup(lawGroup.get());
        lawGroup.get().getLawOptions().add(entity);

        lawOptionRepository.save(entity);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        headers.add("Location", location.toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{lawOptionId}")
    public ResponseEntity updateLawOption(@PathVariable Long lawOptionId, @Valid @RequestBody LawOptionDto lawOptionDto){
        Optional<LawOption> currentDish = lawOptionRepository.findById(lawOptionId);
        if (currentDish.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lawOptionDto.setId(lawOptionId);
        LawOption entity = convertToEntity(lawOptionDto);
        entity.setLawGroup(currentDish.get().getLawGroup());
        lawOptionRepository.save(entity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{lawOptionId}")
    public ResponseEntity deleteLawOption(@PathVariable Long lawOptionId){
        boolean found = lawOptionRepository.existsById(lawOptionId);
        if (!found){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lawOptionRepository.deleteById(lawOptionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private LawOptionDto convertToDto(LawOption e) {
        return modelMapper.map(e, LawOptionDto.class);
    }
    private LawOption convertToEntity(LawOptionDto dto) {
        return modelMapper.map(dto, LawOption.class);
    }
}

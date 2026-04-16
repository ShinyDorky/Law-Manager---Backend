package shinydorky.mos.law_generator_backend.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import shinydorky.mos.law_generator_backend.dto.LawTypeContentsDto;
import shinydorky.mos.law_generator_backend.dto.LawTypeDto;
import shinydorky.mos.law_generator_backend.model.LawType;
import shinydorky.mos.law_generator_backend.repository.LawTypeRepository;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lawType")
public class LawTypeController {
    private final ModelMapper modelMapper;
    private final LawTypeRepository lawTypeRepository;



    @GetMapping
    public ResponseEntity<Collection<LawTypeDto>> getLawTypes(){
        List<LawType> allLawTypes = lawTypeRepository.findAll();
        List<LawTypeDto> result = allLawTypes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{lawTypeId}")
    public ResponseEntity<LawTypeContentsDto> getLawTypeById(@PathVariable Long lawTypeId){
        Optional<LawType> lawType = lawTypeRepository.findById(lawTypeId);
        if (lawType.isPresent()){
            LawTypeContentsDto lawTypeDto = convertToContentsDto(lawType.get());
            return new ResponseEntity<>(lawTypeDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity saveNewLawType(@Valid @RequestBody LawTypeDto lawTypeDto){
        LawType entity = convertToEntity(lawTypeDto);

        lawTypeRepository.save(entity);
        HttpHeaders headers = new HttpHeaders();
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();
        headers.add("Location", location.toString());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{lawTypeId}")
    public ResponseEntity updateLawType(@PathVariable Long lawTypeId, @Valid @RequestBody LawTypeDto lawTypeDto){
        Optional<LawType> currentDish = lawTypeRepository.findById(lawTypeId);
        if (currentDish.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        lawTypeDto.setId(lawTypeId);
        LawType entity = convertToEntity(lawTypeDto);
        lawTypeRepository.save(entity);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{lawTypeId}")
    public ResponseEntity deleteLawType(@PathVariable Long lawTypeId){
        boolean found = lawTypeRepository.existsById(lawTypeId);
        if (found){
            lawTypeRepository.deleteById(lawTypeId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    private LawTypeDto convertToDto(LawType e) {
        return modelMapper.map(e, LawTypeDto.class);
    }
    private LawTypeContentsDto convertToContentsDto(LawType e) {
        return modelMapper.map(e, LawTypeContentsDto.class);
    }
    private LawType convertToEntity(LawTypeDto dto) {
        return modelMapper.map(dto, LawType.class);
    }
}

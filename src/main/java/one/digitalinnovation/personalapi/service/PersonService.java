package one.digitalinnovation.personalapi.service;

import lombok.Builder;
import one.digitalinnovation.personalapi.dto.request.PersonDTO;
import one.digitalinnovation.personalapi.dto.response.MessageResponseDTO;
import one.digitalinnovation.personalapi.entity.Person;
import one.digitalinnovation.personalapi.mapper.PersonMapper;
import one.digitalinnovation.personalapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Builder
@Service
public class PersonService {

    private PersonRepository personRepository;

    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService (PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + savedPerson.getId())
                .build();
    }
}

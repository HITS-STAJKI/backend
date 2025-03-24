package ru.hits.internship.group.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.exceptions.AlreadyExistsException;
import ru.hits.internship.group.repository.GroupRepository;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GroupValidator {
    private final GroupRepository groupRepository;

    public void checkGroupAlreadyExists(String number) {
        if (groupRepository.existsByNumberIgnoreCase(number)) {
            throw new AlreadyExistsException("Группа с номером %s уже существует".formatted(number));
        }
    }

    public void checkGroupAlreadyExistsNotSame(String number, UUID id) {
        if (groupRepository.existsByNumberIgnoreCaseAndIdNot(number, id)) {
            throw new AlreadyExistsException("Группа с номером %s уже существует".formatted(number));
        }
    }
}

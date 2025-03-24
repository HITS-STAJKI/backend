package ru.hits.internship.partner.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.hits.internship.common.exceptions.AlreadyExistsException;
import ru.hits.internship.partner.repository.CompanyPartnerRepository;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CompanyPartnerValidator {
    private final CompanyPartnerRepository companyPartnerRepository;

    public void checkCompanyPartnerAlreadyExists(String name) {
        if (companyPartnerRepository.existsByNameIgnoreCase(name)) {
            throw new AlreadyExistsException("Компания-партнер с названием %s уже существует".formatted(name));
        }
    }

    public void checkCompanyPartnerAlreadyExistsNotSame(String name, UUID id) {
        if (companyPartnerRepository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new AlreadyExistsException("Компания-партнер с названием %s уже существует".formatted(name));
        }
    }
}

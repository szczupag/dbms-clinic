package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.find.LocalizationResponse;

import java.util.Set;
import java.util.stream.Collectors;

class LocalizationService {
  private LocalizationRepository localizationRepository;

  LocalizationService(LocalizationRepository localizationRepository) {
    this.localizationRepository = localizationRepository;
  }

  Set<LocalizationResponse> getAllLocalizations(boolean withClinic) {
    return localizationRepository.findAll().stream()
            .map(localization -> LocalizationResponse.from(localization, withClinic))
            .collect(Collectors.toSet());
  }
}

package pl.moscicki.clinicbackend.clinic.domain;

import pl.moscicki.clinicbackend.clinic.domain.dto.creation.CreationLocalization;
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

  Localization getLocalizationById(Long localizationId) {
    return localizationRepository.findById(localizationId).orElse(null);
  }

  void createLocalization(CreationLocalization creationLocalization, Clinic clinic) {
    Localization localization = Localization.from(creationLocalization, clinic);
    localizationRepository.save(localization);
  }

  void updateLocalization(CreationLocalization creationLocalization, Clinic clinic, long localizationId) {
    Localization localization = Localization.from(creationLocalization, clinic);
    localization.setLocalizationId(localizationId);

    localizationRepository.save(localization);
  }

  void deleteLocalization(Long localizationId) {
    Localization localization = getLocalizationById(localizationId);
    localization.getClinic().setLocalization(null);
    localizationRepository.deleteById(localizationId);
  }
}

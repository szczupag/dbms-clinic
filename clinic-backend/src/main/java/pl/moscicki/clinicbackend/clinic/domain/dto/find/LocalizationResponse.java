package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Localization;

import java.util.Objects;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocalizationResponse {

  private Long id;
  private String city;
  private String street;
  private String postalCode;
  private Long buildingNo;
  private String clinic;

  public static LocalizationResponse from(Localization localization, boolean withClinic) {
    return LocalizationResponse.builder()
            .id(localization.getLocalizationId())
            .city(localization.getCity())
            .street(localization.getStreet())
            .postalCode(localization.getPostalCode())
            .buildingNo(localization.getBuildingNo())
            .clinic(withClinic ? mapClinic(localization) : null)
            .build();
  }

  private static String mapClinic(Localization localization) {
    return Objects.nonNull(localization.getClinic()) ? localization.getClinic().getName() : null;
  }
}

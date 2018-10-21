package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import pl.moscicki.clinicbackend.clinic.domain.Localization;

@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocalizationResponse {

  private String city;
  private String street;
  private String postalCode;
  private Long buildingNo;
  private String clinic;

  public static LocalizationResponse from(Localization localization, boolean withClinic) {
    return LocalizationResponse.builder()
            .city(localization.getCity())
            .street(localization.getStreet())
            .postalCode(localization.getPostalCode())
            .buildingNo(localization.getBuildingNo())
            .clinic(mapClinic(localization, withClinic))
            .build();
  }

  private static String mapClinic(Localization localization, boolean withClinic) {
    return withClinic ? localization.getClinic().getName() : null;
  }
}

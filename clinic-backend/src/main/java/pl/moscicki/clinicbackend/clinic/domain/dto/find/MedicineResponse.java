package pl.moscicki.clinicbackend.clinic.domain.dto.find;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;


@Builder
@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MedicineResponse {

  private Long medicineId;
  private String name;
  private Long recommendedDose;
  private Long cost;
}

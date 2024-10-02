package ma.srmanager.srmouvementv.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PerformanceOverTimeRequestDTO {
    private LocalDate startDate;
    private LocalDate endDate;
}

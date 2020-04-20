package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.report;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
/**
 * Exposes a list of bad records with their violations
 */
public class Report {
    private List<BadRecord> badRecords;
}

package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.analysis.CSPValidityAnalyzer;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.customerstatementrecords.Record;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.report.BadRecord;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.report.Report;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.fileprocessing.CSVReader;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.fileprocessing.XMLReader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CSPAnalyzerOrchestration {
    private final CSVReader csvReader;
    private final XMLReader xmlReader;
    private final CSPValidityAnalyzer validityAnalyzer;

    /**
     * make sure the XML string is transformed into a list of object we can work with, then process the list
     * @param xml the XML string send via the RestController
     * @return A report with constraint violations of the records
     * @throws JsonProcessingException
     */
    public Mono<Report> analyzeXML(String xml) throws JsonProcessingException {
        List<Record> records = xmlReader.readFile(xml);
        return analyzeRecords(records);
    }

    /**
     * make sure the CSV string is transformed into a list of object we can work with, then process the list
     * @param csv the CSV string send via the RestController
     * @return A report with constraint violations of the records
     */
    public Mono<Report> analyzeCSV(String csv) {
        List<Record> records = csvReader.readFile(csv);
        return analyzeRecords(records);
    }

    /**
     * Analyze the records for violations
     * @param records the records to be analyzed
     * @return a report with violations
     */
    private Mono<Report> analyzeRecords(List<Record> records) {
        List<BadRecord> badRecords = validityAnalyzer.analyzeRecords(records);

        Report report = new Report(badRecords);

        return Mono.just(report);
    }
}

package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.Record;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.Report;
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

    public Mono<Report> analyzeXML(String xml) throws JsonProcessingException {
        List<Record> records = xmlReader.readFile(xml);
        return analyzeRecords(records);
    }

    public Mono<Report> analyzeCSV(String csv) {
        List<Record> records = csvReader.readFile(csv);
        return analyzeRecords(records);
    }

    private Mono<Report> analyzeRecords(List<Record> records) {
        return null;
    }
}

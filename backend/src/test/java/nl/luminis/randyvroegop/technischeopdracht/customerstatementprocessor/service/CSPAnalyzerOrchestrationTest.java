package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.analysis.CSPValidityAnalyzer;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.report.Report;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.fileprocessing.CSVReader;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.fileprocessing.XMLReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CSPAnalyzerOrchestrationTest {

    @Spy
    CSPValidityAnalyzer cspValidityAnalyzer;

    @Mock
    CSVReader csvReader;

    @Mock
    XMLReader xmlReader;

    @InjectMocks
    CSPAnalyzerOrchestration cspAnalyzerOrchestration;

    @Test
    void analyzeXML() throws JsonProcessingException {
        when(xmlReader.readFile(anyString())).thenReturn(Collections.EMPTY_LIST);

        cspAnalyzerOrchestration.analyzeXML("<test></test>");

        verify(cspValidityAnalyzer, times(1)).analyzeRecords(Collections.EMPTY_LIST);
    }

    @Test
    void analyzeCSV() {
        when(csvReader.readFile(anyString())).thenReturn(Collections.EMPTY_LIST);

        cspAnalyzerOrchestration.analyzeCSV("<test></test>");

        verify(cspValidityAnalyzer, times(1)).analyzeRecords(Collections.EMPTY_LIST);
    }
}
package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.service.CSPAnalyzerOrchestration;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.Report;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/analyze")
@RequiredArgsConstructor
public class CSPResource {
    private final CSPAnalyzerOrchestration cspService;

    /**
     * @return Mono of the CSV report
     */
    @PostMapping(path = "/csv")
    public Mono<Report> analyzeCSV(@RequestBody JsonNode csv) {
        return cspService.analyzeCSV(csv.get("file").asText());
    }

    /**
     * @return Mono of the XML report
     */
    @PostMapping(path = "/xml")
    public Mono<Report> analyzeXML(@RequestBody JsonNode xml) throws JsonProcessingException {
        return cspService.analyzeXML(xml.get("file").asText());
    }
}

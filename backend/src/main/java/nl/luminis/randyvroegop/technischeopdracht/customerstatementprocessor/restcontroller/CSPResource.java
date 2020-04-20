package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.service.CSPAnalyzerOrchestration;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.report.Report;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@CrossOrigin
@RestController
@RequestMapping("/analyze")
@RequiredArgsConstructor
public class CSPResource {
    private final CSPAnalyzerOrchestration cspService;

    /**
     * @param csv The CSV string in a JsonNode, looking like { "file": "csvString" }
     * @return a Report with bad records
     */
    @PostMapping(path = "/csv")
    public Mono<Report> analyzeCSV(@RequestBody JsonNode csv) {
        return cspService.analyzeCSV(csv.get("file").asText());
    }

    /**
     * @param xml The XML string in a JsonNode, looking like { "file": "xmlString" }
     * @return a Report with bad records
     */
    @PostMapping(path = "/xml")
    public Mono<Report> analyzeXML(@RequestBody JsonNode xml) throws JsonProcessingException {
        return cspService.analyzeXML(xml.get("file").asText());
    }

    /**
     * Redirect / to /index.html
     * @return a redirect request
     */
    @Bean
    RouterFunction<ServerResponse> routerFunction() {
        return route(GET("/"), req ->
                ServerResponse
                        .permanentRedirect(URI.create("/index.html"))
                        .build());
    }
}

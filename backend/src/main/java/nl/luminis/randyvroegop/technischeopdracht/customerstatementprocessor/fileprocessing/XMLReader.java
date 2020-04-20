package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.fileprocessing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.Record;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.Records;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class XMLReader {
    public List<Record> readFile(String xml) throws JsonProcessingException {
        XmlMapper mapper = new XmlMapper();

        Records records = mapper.readValue(xml, Records.class);

        return records.getRecords();
    }
}

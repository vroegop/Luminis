package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.fileprocessing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.slf4j.Slf4j;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.customerstatementrecords.Record;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.customerstatementrecords.Records;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class XMLReader {
    /**
     * Parse the XML file using the Records class as a wrapper to extract a list of Record pojo's.
     * @param xml the XML string representation
     * @return a list of Record Pojo's
     * @throws JsonProcessingException
     */
    public List<Record> readFile(String xml) throws JsonProcessingException {
        XmlMapper mapper = new XmlMapper();

        Records records = mapper.readValue(xml, Records.class);

        return records.getRecords();
    }
}

package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Getter;

import java.util.List;

@Getter
@JacksonXmlRootElement(localName = "records")
public class Records {
    @JacksonXmlProperty(localName = "record")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Record> records;
}

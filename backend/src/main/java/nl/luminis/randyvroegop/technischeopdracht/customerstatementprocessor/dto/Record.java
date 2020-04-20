package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Record {
    @JacksonXmlProperty(isAttribute = true, localName = "reference")
    private Long transactionReference;
    @JacksonXmlProperty(localName = "accountNumber")
    private String accountNumber;
    @JacksonXmlProperty(localName = "startBalance")
    private BigDecimal startBalance;
    @JacksonXmlProperty(localName = "endBalance")
    private BigDecimal endBalance;
    @JacksonXmlProperty(localName = "mutation")
    private BigDecimal mutation;
    @JacksonXmlProperty(localName = "description")
    private String description;
}

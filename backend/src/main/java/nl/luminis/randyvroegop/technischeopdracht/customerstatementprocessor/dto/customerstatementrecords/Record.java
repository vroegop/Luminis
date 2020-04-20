package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.customerstatementrecords;

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
/**
 * One record in a list of transactions.
 */
public class Record {
    @JacksonXmlProperty(isAttribute = true, localName = "reference")
    private Long transactionReference;
    // Currently, accountNumber is not used in any application logic so we could remove it from the parsing
    // but having it in here makes it easier for future change requests to work with it.
    // I would not have included it if it would be a lot of work since it is not required.
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

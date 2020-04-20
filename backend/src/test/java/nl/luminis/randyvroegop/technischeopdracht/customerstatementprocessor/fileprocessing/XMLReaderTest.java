
package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.fileprocessing;

import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class XMLReaderTest {
    private XMLReader xmlReader;
    String xml;

    @BeforeEach
    void setUp() throws IOException {
        xmlReader = new XMLReader();
        xml = new String(getClass().getClassLoader().getResourceAsStream("records.xml").readAllBytes());
    }

    @DisplayName("Get all records")
    @Test
    void getAllRecords() throws IOException {
        List<Record> records = xmlReader.readFile(xml);

        assertThat(records.size()).isEqualTo(10);
    }

    @DisplayName("Get the correct value inside the description of the first record")
    @Test
    void getCorrectDataInDescription() throws IOException {
        List<Record> records = xmlReader.readFile(xml);

        assertThat(records.get(0).getDescription()).isEqualTo("Subscription for Daniël Bakker");
    }

    @DisplayName("Get the correct value inside the mutation of the last record")
    @Test
    void getCorrectDataInMutation() throws IOException {
        List<Record> records = xmlReader.readFile(xml);

        assertThat(records.get(9).getMutation()).isEqualTo(new BigDecimal("-7.26"));
    }

    @DisplayName("Get the correct value inside the Start balance of the last record")
    @Test
    void getCorrectDataInStartBalance() throws IOException {
        List<Record> records = xmlReader.readFile(xml);

        assertThat(records.get(9).getStartBalance()).isEqualTo(new BigDecimal("91.41"));
    }

    @DisplayName("Get the correct value inside the End balance of the last record")
    @Test
    void getCorrectDataInEndBalance() throws IOException {
        List<Record> records = xmlReader.readFile(xml);

        assertThat(records.get(9).getEndBalance()).isEqualTo(new BigDecimal("84.15"));
    }

    @DisplayName("Get the correct value inside the Reference of the last record")
    @Test
    void getCorrectDataInReference() throws IOException {
        List<Record> records = xmlReader.readFile(xml);

        assertThat(records.get(9).getTransactionReference()).isEqualTo(107874);
    }

    @DisplayName("Get the correct value inside the Account number of the last record")
    @Test
    void getCorrectDataInAccountNumber() throws IOException {
        List<Record> records = xmlReader.readFile(xml);

        assertThat(records.get(9).getAccountNumber()).isEqualTo("NL56RABO0149876948");
    }
}

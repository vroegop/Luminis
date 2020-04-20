package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.fileprocessing;

import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.Record;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CSVReaderTest {
    private CSVReader csvReader;
    String csv;

    @BeforeEach
    void setUp() throws IOException {
        csvReader = new CSVReader();
        csv = new String(getClass().getClassLoader().getResourceAsStream("records.csv").readAllBytes());
    }

    @DisplayName("Get all records")
    @Test
    void getAllRecords() throws IOException {
        List<Record> records = csvReader.readFile(csv);

        assertThat(records.size()).isEqualTo(csv.split("\n").length - 1);
    }

    @DisplayName("Get the correct value inside the description of the first record")
    @Test
    void getCorrectDataInDescription() throws IOException {
        List<Record> records = csvReader.readFile(csv);

        assertThat(records.get(0).getDescription()).isEqualTo("Flowers for Peter Bakker");
    }

    @DisplayName("Get the correct value inside the mutation of the last record")
    @Test
    void getCorrectDataInMutation() throws IOException {
        List<Record> records = csvReader.readFile(csv);

        assertThat(records.get(9).getMutation()).isEqualTo(new BigDecimal("+14.97"));
    }

    @DisplayName("Get the correct value inside the Start balance of the last record")
    @Test
    void getCorrectDataInStartBalance() throws IOException {
        List<Record> records = csvReader.readFile(csv);

        assertThat(records.get(9).getStartBalance()).isEqualTo(new BigDecimal("57.51"));
    }

    @DisplayName("Get the correct value inside the End balance of the last record")
    @Test
    void getCorrectDataInEndBalance() throws IOException {
        List<Record> records = csvReader.readFile(csv);

        assertThat(records.get(9).getEndBalance()).isEqualTo(new BigDecimal("72.48"));
    }

    @DisplayName("Get the correct value inside the Reference of the last record")
    @Test
    void getCorrectDataInReference() throws IOException {
        List<Record> records = csvReader.readFile(csv);

        assertThat(records.get(9).getTransactionReference()).isEqualTo(105587);
    }

    @DisplayName("Get the correct value inside the Account number of the last record")
    @Test
    void getCorrectDataInAccountNumber() throws IOException {
        List<Record> records = csvReader.readFile(csv);

        assertThat(records.get(9).getAccountNumber()).isEqualTo("NL32RABO0195610843");
    }
}
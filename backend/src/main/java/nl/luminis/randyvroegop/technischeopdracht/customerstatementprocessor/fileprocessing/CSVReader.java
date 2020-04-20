package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.fileprocessing;

import lombok.extern.slf4j.Slf4j;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.customerstatementrecords.Record;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CSVReader {
    /**
     * Read every line in the CSV and create CustomerStatementRecords for all records in the CSV file
     *
     * @param csv A string representation of the CSV file uploaded
     * @return A list of CSR's that can be analysed further
     */
    public List<Record> readFile(String csv) {
        List<Record> processedRecords = new ArrayList<>();

        String[] strings = csv.split("\n");

        String[] dataTypes = strings[0].split(",");

        // skip the first linenumber as those are the dataTags
        for (int lineNumber = 1; lineNumber < strings.length; lineNumber++) {
            Record.RecordBuilder builder = Record.builder();
            String[] csrData = strings[lineNumber].split(",");

            // Here we need the index to calculate what the datatype is
            for (int dataFieldIndex = 0; dataFieldIndex < csrData.length; dataFieldIndex++) {
                String data = csrData[dataFieldIndex];
                String type = dataTypes[dataFieldIndex];

                setDataField(builder, data, type);
            }

            processedRecords.add(builder.build());
        }
        return processedRecords;
    }

    private void setDataField(Record.RecordBuilder builder, String data, String type) {
        // Here we could do type checking when using valueOf, but Guy told me the data would always be valid
        switch (type.toUpperCase()) {
            case "REFERENCE":
                builder.transactionReference(Long.valueOf(data));
                break;
            case "ACCOUNT NUMBER":
                builder.accountNumber(data);
                break;
            case "DESCRIPTION":
                builder.description(data);
                break;
            case "START BALANCE":
                builder.startBalance(new BigDecimal(data));
                break;
            case "MUTATION":
                builder.mutation(new BigDecimal(data));
                break;
            case "END BALANCE":
                builder.endBalance(new BigDecimal(data));
                break;
        }
    }
}

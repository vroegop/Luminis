package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.analysis;

import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.customerstatementrecords.Record;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.report.BadRecord;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CSPValidityAnalyzer {

    /**
     * Analyze the transactions and build a list of violations
     *
     * @param records A list of transactions
     * @return the list of violations, either incorrect balance after mutation or duplicate transaction reference
     */
    public List<BadRecord> analyzeRecords(List<Record> records) {
        Set<Long> transactionReferences = new HashSet<>();
        List<BadRecord> badRecords = new ArrayList<>();

        Set<Record> processedRecords = new HashSet<>();

        for (Record record : records) {
            // Check for duplicate entry of transaction reference
            // add is false if not added because of duplicate entry
            boolean add = transactionReferences.add(record.getTransactionReference());
            if (!add) {
                if (!processedRecords.contains(record)) {
                    // add all duplicates to badRecords and remove them from the original list
                    for (Record r : records) {
                        if (r.getTransactionReference().equals(record.getTransactionReference())) {
                            badRecords.add(BadRecord.duplicateTransactionReferenceFrom(r));
                            processedRecords.add(r);
                        }
                    }
                }
            }

            // Check end balance based on start balance and mutation
            // Mutation is either positive or negative, so we always add it to startbalance, never subtract
            BigDecimal expectedEndBalance = record.getStartBalance().add(record.getMutation());
            if (!record.getEndBalance().equals(expectedEndBalance)) {
                badRecords.add(BadRecord.invalidEndBalanceFrom(record));
            }
        }

        return badRecords;
    }
}

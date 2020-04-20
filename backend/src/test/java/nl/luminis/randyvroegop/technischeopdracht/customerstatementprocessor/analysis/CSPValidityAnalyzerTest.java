package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.analysis;

import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.customerstatementrecords.Record;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.report.BadRecord;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.report.RecordConstraintViolation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class CSPValidityAnalyzerTest {

    CSPValidityAnalyzer cspValidityAnalyzer;

    List<Record> validRecords;

    List<Record> duplicateTransactionReferenceRecords;

    List<Record> invalidEndBalanceRecords;

    Random random = new Random();

    @BeforeEach
    void setUp() {
        cspValidityAnalyzer = new CSPValidityAnalyzer();
        validRecords = new ArrayList<>();
        duplicateTransactionReferenceRecords = new ArrayList<>();
        invalidEndBalanceRecords = new ArrayList<>();

        long validRecordCount = 10L;

        for (int i = 0; i < validRecordCount; i++) {
            validRecords.add(validRecord(i));
            duplicateTransactionReferenceRecords.add(validRecord(i));
            invalidEndBalanceRecords.add(validRecord(i));
        }

        for (int i = 0; i < 2; i++) {
            duplicateTransactionReferenceRecords.add(invalidRecordSetReference());
            invalidEndBalanceRecords.add(invalidRecordSetEndBalance(validRecordCount + i));
        }
    }

    @Test
    void analyzeRecordsUniqueTransactionsValidRecords() {
        List<BadRecord> badRecords = cspValidityAnalyzer.analyzeRecords(validRecords);

        assertThat(badRecords.size()).isEqualTo(0);
    }

    @Test
    void analyzeRecordsUniqueTransactionsInvalidRecordsReferenceDuplicate() {
        List<BadRecord> badRecords = cspValidityAnalyzer.analyzeRecords(duplicateTransactionReferenceRecords);

        // atliest 2 entries should be invalid, might be more
        assertThat(badRecords.size()).isEqualTo(2);
        badRecords.forEach(badRecord ->
                assertThat(badRecord.getValidationConstraintViolation() ==
                        RecordConstraintViolation.NON_UNIQUE_TRANSACTION_REFERENCE));
    }

    @Test
    void analyzeRecordsUniqueTransactionsInvalidRecordsEndBalanceIncorrect() {
        List<BadRecord> badRecords = cspValidityAnalyzer.analyzeRecords(invalidEndBalanceRecords);

        // atliest 2 entries should be invalid, might be more
        assertThat(badRecords.size()).isEqualTo(2);
        badRecords.forEach(badRecord ->
                assertThat(badRecord.getValidationConstraintViolation() ==
                        RecordConstraintViolation.INVALID_END_BALANCE));
    }

    Record validRecord(long reference) {
        BigDecimal startBalance = new BigDecimal(random.nextInt(1000));
        BigDecimal mutation = new BigDecimal("-" + random.nextInt(700));
        BigDecimal endBalance = startBalance.add(mutation);

        Record record = Record.builder()
                .accountNumber("abc123")
                .description("description")
                .startBalance(startBalance)
                .endBalance(endBalance)
                .mutation(mutation)
                .transactionReference(reference)
                .build();

        return record;
    }

    Record invalidRecordSetReference() {
        BigDecimal startBalance = new BigDecimal(random.nextInt(1000));
        BigDecimal mutation = new BigDecimal("-" + random.nextInt(700));
        BigDecimal endBalance = startBalance.add(mutation);

        Record record = Record.builder()
                .accountNumber("abc123")
                .description("description")
                .startBalance(startBalance)
                .endBalance(endBalance)
                .mutation(mutation)
                .transactionReference(1234567L)
                .build();

        return record;
    }

    Record invalidRecordSetEndBalance(long reference) {
        BigDecimal startBalance = new BigDecimal(random.nextInt(1000));
        BigDecimal mutation = new BigDecimal("-" + random.nextInt(700));
        BigDecimal endBalance = startBalance.add(new BigDecimal(1));

        Record record = Record.builder()
                .accountNumber("abc123")
                .description("description")
                .startBalance(startBalance)
                .endBalance(endBalance)
                .mutation(mutation)
                .transactionReference(reference)
                .build();

        return record;
    }

}
package nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.report;

import lombok.Getter;
import nl.luminis.randyvroegop.technischeopdracht.customerstatementprocessor.dto.customerstatementrecords.Record;

@Getter
public class BadRecord {
    private Long transactionReference;
    private String transactionDescription;
    private RecordConstraintViolation validationConstraintViolation;

    /**
     * Creation method that derives a badRecord based on an actual record which has been marked as violation
     * @param record the original record
     * @return A bad record violation object with transaction reference error message
     */
    public static BadRecord duplicateTransactionReferenceFrom(Record record) {
        BadRecord badRecord = new BadRecord();
        badRecord.transactionReference = record.getTransactionReference();
        badRecord.transactionDescription = record.getDescription();
        badRecord.validationConstraintViolation = RecordConstraintViolation.NON_UNIQUE_TRANSACTION_REFERENCE;
        return badRecord;
    }

    /**
     * Creation method that derives a badRecord based on an actual record which has been marked as violation
     * @param record the original record
     * @return A bad record violation object with invalid end balance error message
     */
    public static BadRecord invalidEndBalanceFrom(Record record) {
        BadRecord badRecord = new BadRecord();
        badRecord.transactionReference = record.getTransactionReference();
        badRecord.transactionDescription = record.getDescription();
        badRecord.validationConstraintViolation = RecordConstraintViolation.INVALID_END_BALANCE;
        return badRecord;
    }
}

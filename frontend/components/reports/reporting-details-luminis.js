import { LitElement, html, css } from 'lit-element';
import { Icons } from './icons';

export class ReportingDetailsLuminis extends LitElement {


    static get properties() {
        return {
            report: { type: Object }
        }
    }

    static get styles() {
        return css`
            :host {
                font-size: 14px;
                font-family: arial;
            }

            .bad-record {
                margin: 7px 2px;
                padding: 2px 5px;
                border-left: 4px solid rgba(255, 50, 50, 0.2);
                background-color: rgba(255,255,255,0.7);
                position: relative;
            }

            .bad-record svg {
                width: 30px;
                height: 30px;
                position: absolute;
                right: 5px;
                top: 5px;
            }
        `;
    }

    getViolationText(record) {
        if(record.validationConstraintViolation === "INVALID_END_BALANCE") {
            return "Invalid end balance after mutation.";
        } else if (record.validationConstraintViolation === "NON_UNIQUE_TRANSACTION_REFERENCE") {
            return "Transaction reference is not unique.";
        }
        return "Unknown issue with this transaction, root cause: " + record.validationConstraintViolation;
    }

    getViolationIcon(record) {
        if(record.validationConstraintViolation === "INVALID_END_BALANCE") {
            return Icons.getBadEndBalanceIcon();
        } else if (record.validationConstraintViolation === "NON_UNIQUE_TRANSACTION_REFERENCE") {
            return Icons.getDuplicateIcon();
        }
    }

    render() {
        return html`
            ${console.log(this.report)}
            ${this.report.badRecords.map(record => html`
                <div class="bad-record">
                    ${this.getViolationIcon(record)}
                    <p>Transaction reference: ${record.transactionReference}</p>
                    <p>Description: ${record.transactionDescription}</p>
                    <p>${this.getViolationText(record)}</p>
                </div>
            `)}
        `;
    }
}

customElements.define('reporting-details-luminis', ReportingDetailsLuminis);
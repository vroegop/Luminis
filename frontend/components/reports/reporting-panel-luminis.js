import { LitElement, html } from 'lit-element';
import { ReportingPanelLuminisStyles } from './reporting-panel-luminis.styles';
import { ReportingDetailsLuminis } from './reporting-details-luminis';

export class ReportingPanelLuminis extends LitElement {


    static get properties() {
        return {
            reports: { type: Array }
        }
    }

    static get styles() {
        return ReportingPanelLuminisStyles.getStyles();
    }

    openDetails(report) {
        this.reports = this.reports.map(entry => {
            if(entry === report) {
                entry.detailsOpened = !entry.detailsOpened;
            }
            return entry;
        });
    }

    render() {
        return html`
        ${this.reports.map((report, index) => html`
            <div class="report" ?data-bad-records=${report.badRecords.length > 0}>
                <p>Report ${index + 1}: ${report.filename}</p>

                <br>

                <p>Uploaded:</p>
                <p>${report.timestamp.split("T")[0]} ${report.timestamp.split("T")[1].substring(0, 8)}</p>

                <br>

                ${report.badRecords.length
                ? html`<p>Bad entries: ${report.badRecords.length}</p>`
                : html`<p>No bad entries found.</p>`}

                <div class="button" @click=${() => this.openDetails(report)}>${report.detailsOpened ? 'Close' : 'Open'} details</div>
                
                <reporting-details-luminis .report=${report} ?opened=${report.detailsOpened}></reporting-details-luminis>
            </div>
        `)}
    `;
    }
}

customElements.define('reporting-panel-luminis', ReportingPanelLuminis);
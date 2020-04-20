import { LitElement, html } from 'lit-element';
import { ReportingPanelLuminis } from '../reports/reporting-panel-luminis';
import { UploadPanelLuminis } from '../fileupload/upload-panel-luminis';

export class DashboardLuminis extends LitElement {

  connectedCallback() {
    super.connectedCallback();
    this.reports = [];
  }

  static get properties() {
    return {
      reports: { type: Array }
    }
  }

  async handleFileUpload({ file, extension, filename }) {
    const url = `http://localhost:8080/analyze/${extension}`;
    const response = await fetch(url, {
      method: 'POST',
      mode: 'cors',
      cache: 'no-cache',
      credentials: 'same-origin',
      headers: {
        'Content-Type': 'application/json'
      },
      referrerPolicy: 'no-referrer',
      body: JSON.stringify({ file })
    });

    const report = await response.json();

    report.timestamp = new Date().toISOString();
    report.filename = filename;

    this.reports = [...this.reports, report];
  }

  render() {
    return html`
      <upload-panel-luminis @file-uploaded="${e => this.handleFileUpload(e.detail.message)}"></upload-panel-luminis>
      <reporting-panel-luminis .reports="${this.reports}"></reporting-panel-luminis>
    `;
  }
}

customElements.define('dashboard-luminis', DashboardLuminis);
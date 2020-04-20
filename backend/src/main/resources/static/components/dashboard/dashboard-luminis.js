import { LitElement, html } from "../../node_modules/lit-element/lit-element.js";
import { ReportingPanelLuminis } from "../reports/reporting-panel-luminis.js";
import { UploadPanelLuminis } from "../fileupload/upload-panel-luminis.js";
export class DashboardLuminis extends LitElement {
  static get properties() {
    return {
      reports: {
        type: Array
      }
    };
  }

  async handleFileUpload({
    file,
    extension
  }) {
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
      body: JSON.stringify({
        file
      })
    });
    console.log(response.json());
  }

  render() {
    return html`
      <upload-panel-luminis @file-uploaded="${e => this.handleFileUpload(e.detail.message)}"></upload-panel-luminis>
      <reporting-panel-luminis></reporting-panel-luminis>
    `;
  }

}
customElements.define('dashboard-luminis', DashboardLuminis);
import { LitElement, html, css } from 'lit-element';

export class UploadPanelLuminis extends LitElement {

    connectedCallback() {
        super.connectedCallback();
    }

    static get styles() {
        return css`
        form {
            display:flex;
            height: 100px;
            width: 100%;
            background-color: #f39200;
            align-items: center;
            justify-content: center;
        }
        `;
    }

    // Reads every file separately and calls emitFileContent with the file contents
    // Only triggers if file extension is XML or CSV
    async readFile(files) {

        for (let i = 0; i < files.length; i++) {
            const file = files[i];
            const extension = file.name.substr((file.name.lastIndexOf('.') + 1));
            const content = await new Response(file).text();
            if (/(csv|xml)$/ig.test(extension)) {
                this.emitFileContent(content, extension);
            }
        }

        this.shadowRoot.getElementById("fileUpload").value = "";
    }

    emitFileContent(file, extension) {
        let event = new CustomEvent('file-uploaded', {
            detail: {
                message: { file, extension }
            }
        });
        this.dispatchEvent(event);
    }

    render() {
        return html`
            <form>
                <input type="file" id="fileUpload" name="filename" multiple @change=${e => this.readFile(e.target.files)}>
            </form>
        `;
    }
}

customElements.define('upload-panel-luminis', UploadPanelLuminis);
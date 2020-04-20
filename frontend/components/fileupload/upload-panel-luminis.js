import { LitElement, html, css } from 'lit-element';

export class UploadPanelLuminis extends LitElement {

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
            const filename = file.name;
            const content = await new Response(file).text();
            if (/(csv|xml)$/ig.test(extension)) {
                this.emitFileContent(content, extension, filename);
            } else {
                alert("This alertbox could use some work if it comes down to styling, but the important thing is you uploaded a file that is not CSV or XML.");
            }
        }

        this.shadowRoot.getElementById("fileUpload").value = "";
    }

    emitFileContent(file, extension, filename) {
        let event = new CustomEvent('file-uploaded', {
            detail: {
                message: { file, extension, filename }
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
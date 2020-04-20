import { css } from 'lit-element';

export class ReportingPanelLuminisStyles {

    static getStyles() {
        return css`
        :host {
            display:flex;
            align-content:space-around;
            justify-content:center;
            flex-wrap:wrap;
        }

        .report {
            display: inline-block;
            margin: 30px;
            width: 400px;
            border-radius: 4px;
            background-color: rgba(100, 255, 130, 0.1);
            border-bottom: 4px solid rgba(50, 180, 50, 0.5);
        }

        .report[data-bad-records] {
            background-color: rgba(255, 140, 170, 0.1);
            border-color: rgba(255, 50, 50, 0.5);
        }

        .report p {
            font-size: 20px;
            font-family: arial;
            text-align: center;
            margin: 3px 0;
        }

        .report[data-bad-records] .button {
            display: block;
        }

        .report .button {
            display:none;
            animation: bouncy 5s infinite linear;
            padding: 0.35em 1.2em;
            border: 0.1em solid rgba(255, 50, 50, 0.5);
            margin: 10px 0 12px 0;
            box-sizing: border-box;
            text-decoration: none;
            color: rgba(255, 50, 50, 0.5);
            text-align: center;
            transition: all 0.2s;
            height: 32px;
        }

        .report .button:hover {
            color: #000000;
            background-color: #FFFFFF;
        }

        .report reporting-details-luminis {
            display:none;
        }

        .report reporting-details-luminis[opened] {
            display:block;
        }

        @keyframes bouncy {
            0% {
                height: 32px;
            }
            41% {
                height: 32px;
                margin-bottom: 12px;
            }
            43% {
                height: 34px;
                margin-bottom: 10px;
            }
            45% {
                height: 30px;
                margin-bottom: 14px;
            }
            47% {
                height: 34px;
                margin-bottom: 10px;
            }
            48% {
                height: 32px;
                margin-bottom: 12px;
            }
            100% {
                height: 32px;
            }
        }
        `;
    }
}
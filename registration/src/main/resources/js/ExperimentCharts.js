/*
 * Copyright (C) 2022
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

/**
 * @since 09 August 2022 11:20 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */

function generateChart(chartData) {
    // console.log(chartData);
    const ctx = document.getElementById(chartData.canvas).getContext('2d');
    var options = {
        scales: {
            y: {
                beginAtZero: true
            }
        },
        plugins: {
            title: {
                display: true,
                text: chartData.label
            },
            subtitle: {
                display: true,
                text: 'Frinex @project.version@',
                position: 'bottom',
                align: 'end',
                font: {
                    size: 8
                }
            }
        }
    };
    var data = {
        labels: [],
        datasets: []
    };
    const adminChart = new Chart(ctx, {
        type: chartData.type,
        options: options,
        data: data
    });
    if (chartData.type === "bar" || chartData.type === "pie") {
        data.datasets.push({
            label: chartData.label,
            data: [],
            backgroundColor: [],
            borderColor: [],
            borderWidth: 1
        });
        for (const metadata of chartData.metadata) {
            data.labels.push(metadata.label);
            const metadataIndex = data.labels.length - 1;
            //data.datasets[0].data.push(metadata.matching);
            data.datasets[0].backgroundColor.push(metadata.colour + '20');
            data.datasets[0].borderColor.push(metadata.colour + 'ff');
            $.getJSON('participants/search/countByStaleCopyAnd' + metadata.fieldname + 'Like?staleCopy=false&matchingLike=' + metadata.matching, function (responseData) {
                // console.log(responseData);
                data.datasets[0].data[metadataIndex] = responseData;
                adminChart.update();
            });
        }
        for (const stimuli of chartData.stimuli) {
            data.labels.push(stimuli.label);
            //data.datasets[0].data.push(stimuli.matching);
            data.datasets[0].backgroundColor.push(stimuli.colour + '20');
            data.datasets[0].borderColor.push(stimuli.colour + 'ff');
        }
    } else {
        for (const metadata of chartData.metadata) {
            dataset = {
                label: metadata.label,
                data: [12, 19, 3, 5, 2, 3],
                backgroundColor: metadata.colour + '20',
                borderColor: metadata.colour + 'ff',
                borderWidth: 1
            };
            //dataset.data.push(metadata.matching);
            data.datasets.push(dataset);
        }
        for (const stimuli of chartData.stimuli) {
            dataset = {
                label: stimuli.label,
                data: [12, 19, 3, 5, 2, 3],
                fill: false,
                borderColor: stimuli.colour + 'ff',
                borderWidth: 1,
                tension: 0.1
            };
            data.labels = [12, 19, 3, 5, 2, 3];
            //dataset.data.push(stimuli.matching);
            data.datasets.push(dataset);
        }
    }
}

//        {canvas:"d1e54", label: "Opleidingsniveau", type: "pie", metadata: [
//                                {label: "universiteit", fieldname: "Opleidingsniveau", matching: "universiteit", colour: "#770000"}, 
//                                {label: "basisonderwijs", fieldname: "Opleidingsniveau", matching: "basisonderwijs", colour: "#077000"}, 
//                                {label: "anders", fieldname: "Opleidingsniveau", matching: " anders", colour: "#007700"}, 
//                                {label: "MBO", fieldname: "Opleidingsniveau", matching: "MBO", colour: "#000770"}, 
//                                {label: "voortgezet onderwijs", fieldname: "Opleidingsniveau", matching: "voortgezet onderwijs", colour: "#000077"}, 
//                                {label: "HBO", fieldname: "Opleidingsniveau", matching: "HBO", colour: "#707000"}], stimuli: []});

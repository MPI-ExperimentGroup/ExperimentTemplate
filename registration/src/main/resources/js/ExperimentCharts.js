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
            legend: {
                display: (chartData.type === "bar") ? false : true
            },
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
                    size: 8,
                    fontColor: '#d3d3d3'
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
        for (const stimulusResponse of chartData.stimulusResponse) {
            data.labels.push(stimulusResponse.label);
            const responseIndex = data.labels.length - 1;
            //data.datasets[0].data.push(stimulusResponse.matching);
            data.datasets[0].backgroundColor.push(stimulusResponse.colour + '20');
            data.datasets[0].borderColor.push(stimulusResponse.colour + 'ff');
            $.getJSON('stimulusresponses/search/countByScreenNameLikeAndScoreGroupLikeAndResponseGroupLikeAndStimulusIdLikeAndResponseLike'
                + ((stimulusResponse.isCorrect)? 'AndIsCorrect?isCorrect=' + encodeURIComponent(stimulusResponse.isCorrect) + '&screenName=' : '?screenName=')
                 + encodeURIComponent(stimulusResponse.screenName)
                + '&scoreGroup=' + encodeURIComponent(stimulusResponse.scoreGroup)
                + '&responseGroup=' + encodeURIComponent(stimulusResponse.responseGroup)
                + '&stimulusId=' + encodeURIComponent(stimulusResponse.stimulusId)
                + '&response=' + encodeURIComponent(stimulusResponse.response), function (responseData) {
                    // console.log(responseData);
                    data.datasets[0].data[responseIndex] = responseData;
                    adminChart.update();
            });

            // $.getJSON('stimulusresponses/search/countBy' + stimulusResponse.columnName + 'Like?matchingLike=' + stimulusResponse.matching, function (responseData) {
            //     // console.log(responseData);
            //     data.datasets[0].data[responseIndex] = responseData;
            //     adminChart.update();
            // });
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
        for (const stimulusResponse of chartData.stimulusResponse) {
            dataset = {
                label: stimulusResponse.label,
                data: [12, 19, 3, 5, 2, 3],
                fill: false,
                borderColor: stimulusResponse.colour + 'ff',
                borderWidth: 1,
                tension: 0.1
            };
            data.labels = [12, 19, 3, 5, 2, 3];
            //dataset.data.push(stimulusResponse.matching);
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
//                                {label: "HBO", fieldname: "Opleidingsniveau", matching: "HBO", colour: "#707000"}], stimulusResponse: []});

function sortBy(tableId, sortColumn) {
    $("#" + tableId + "LoadMoreRow").siblings().remove();
    $("#" + tableId + "LoadMoreRow").attr('pageNumber', 0)
    $("#" + tableId + "LoadMoreRow").attr('sortColumn', sortColumn.charAt(0).toLowerCase() + sortColumn.slice(1));
    loadMore(tableId);
}

function loadMore(tableId) {
    const dataUrl = $("#" + tableId + "LoadMoreRow").attr('dataUrl');
    const pageNumber = $("#" + tableId + "LoadMoreRow").attr('pageNumber');
    const sortColumn = $("#" + tableId + "LoadMoreRow").attr('sortColumn');
    $.getJSON(dataUrl + '&page=' + pageNumber + '&sort=' + sortColumn, function (responseData) {
        // console.log(responseData);
        // todo: impliment or remove simple mode parameter
        var touchInputReportCounter = $("#" + tableId + " tbody tr:last").index();
        for (const recordData of responseData._embedded[Object.keys(responseData._embedded)[0]]) {
            var touchInputReport = false;
            var dataRow = "<tr id='clickablerow' userid='" + recordData.userId + "' onclick=\"window.location = 'participantdetail?id=' + this.getAttribute('userId') + '&amp;simple=true';\">";
            for (const columnHeader of $("#" + tableId + " thead tr th")) {
                const columnLabel = columnHeader.innerText;
                const columnName = columnLabel.charAt(0).toLowerCase() + columnLabel.slice(1);
                if (columnName === "tagValue2" && recordData.eventTag === "touchInputReport") {
                    touchInputReport = true;
                    touchInputReportCounter++;
                    dataRow += "<td class='popupOuter'>";
                    dataRow += "<svg xmlns='http://www.w3.org/2000/svg' id='svg_" + tableId + '_' + touchInputReportCounter + "' version='1.1' width='100' height='100'></svg>";
                    dataRow += "<table id='table_" + tableId + '_' + touchInputReportCounter + "' class='popupTable'><tr><td>ms</td><td>X</td><td>Y</td><td>Interaction</td></tr></table>";
                    dataRow += "</td>";
                } else {
                    dataRow += "<td>" + recordData[columnName] + "</td>";
                }
            }
            dataRow += "</tr>";
            $(dataRow).insertBefore("#" + tableId + "LoadMoreRow");
            if (touchInputReport) {
                var touchData = recordData.tagValue2;
                var svgId = '#svg_' + tableId + '_' + touchInputReportCounter;
                var reportTableId = '#table_' + tableId + '_' + touchInputReportCounter;
                touchInputSVG(touchData, svgId, reportTableId);
            }
        }
        $("#" + tableId + "LoadMoreRow > td > span").text($("#" + tableId + " tbody tr:last").index() + ' of ' + responseData.page.totalElements);
        $("#" + tableId + "LoadMoreRow").attr('pageNumber', responseData.page.number + 1);
    });
}

function generateTable(tableData) {
    $("#" + tableData.divId).append("<h3>" + tableData.label + "</h3>");
    const tableId = tableData.divId + "Table";
    const columnCount = tableData.columnNames.split(",").length;
    const dataUrl = (tableData.source === "tagpair") ? (
        'tagpairevents/search/findByScreenNameLikeAndEventTagLikeAndTagValue1LikeAndTagValue2Like'
        + '?screenName=' + encodeURIComponent(tableData.screenName)
        + '&eventTag=' + encodeURIComponent(tableData.eventTag)
        + '&tagValue1=' + encodeURIComponent(tableData.tagValue1)
        + '&tagValue2=' + encodeURIComponent(tableData.tagValue2)
    ) : (tableData.source === "stimulusResponse") ? (
        'stimulusresponses/search/findByScreenNameLikeAndScoreGroupLikeAndResponseGroupLikeAndStimulusIdLikeAndResponseLike'
        + ((stimulusResponse.isCorrect)? 'AndIsCorrect?isCorrect=' + encodeURIComponent(stimulusResponse.isCorrect) + '&screenName=' : '?screenName=')
        + encodeURIComponent(tableData.screenName)
        + '&scoreGroup=' + encodeURIComponent(tableData.scoreGroup)
        + '&responseGroup=' + encodeURIComponent(tableData.responseGroup)
        + '&stimulusId=' + encodeURIComponent(tableData.stimulusId)
        + '&response=' + encodeURIComponent(tableData.response)
    ) : "";
    if (dataUrl === "") {
        $("#" + tableData.divId).append("unsupported source: " + tableData.source);
    } else {
        $("#" + tableData.divId).append("<table id=\"" + tableId + "\" class='datatable'><thead><tr></tr></thead><tbody><tr id=\"" + tableId + "LoadMoreRow\" dataUrl='" + dataUrl + "' pageNumber='0' sortColumn='tagDate'><td colspan='" + columnCount + "'><span></span>&nbsp;<button onclick=\"loadMore('" + tableId + "');\">Load More</button></td></tr></tbody></table>");
        for (const columnName of tableData.columnNames.split(",")) {
            $("#" + tableId + " thead tr").append("<th><a href='#' onclick=\"sortBy('" + tableId + "', '" + encodeURIComponent(columnName) + "');return false;\">" + columnName + "</a></th>");
        }
        loadMore(tableId);
    }
}

function touchInputSVG(touchData, svgId, tableId) {
    //                            $(svgId).mouseover(function () {
    var scaleFactor = -1;
    var maxMs = -1;
    $.each(touchData.split(";"), function (lineIndex, lineString) {
        var lineParts = lineString.split(",");
        if (scaleFactor === -1) {
            var maxWidth = parseFloat(lineParts[1]);
            var maxHeight = parseFloat(lineParts[2]);
            maxMs = lineParts[0];
            scaleFactor = (maxWidth > maxHeight) ? maxWidth : maxHeight;
            $(svgId).attr({
                width: (maxWidth / scaleFactor * 100),
                height: (maxHeight / scaleFactor * 100)
            });
            $(svgId).append($(document.createElementNS('http://www.w3.org/2000/svg', 'rect'))
                .attr({
                    width: (maxWidth / scaleFactor * 100),
                    height: (maxHeight / scaleFactor * 100),
                    r: 1,
                    fill: 'lightgrey'
                }));
        } else if (lineParts.length > 2) {
            var dotColour = 'green';
            var partIndex;
            for (partIndex = 3; partIndex < lineParts.length; partIndex++) {
                if (lineParts[partIndex].length > 0) {
                    dotColour = 'blue';
                }
            }
            $(svgId).append($(document.createElementNS('http://www.w3.org/2000/svg', 'circle'))
                .attr({
                    cx: (lineParts[1] / scaleFactor * 100),
                    cy: (lineParts[2] / scaleFactor * 100),
                    r: 1,
                    fill: dotColour
                }));
        }
        if (lineParts.length > 2) {
            var partIndex;
            var stimulusIntersections = "";
            for (partIndex = 3; partIndex < lineParts.length; partIndex++) {
                if (lineParts[partIndex].length > 0) {
                    if (stimulusIntersections.length > 0) stimulusIntersections += ',';
                    stimulusIntersections += lineParts[partIndex];
                }
            }
            $(tableId + ' tr:last').after('<tr><td>' + lineParts[0] + '</td><td>' + lineParts[1] + '</td><td>' + lineParts[2] + '</td><td>' + stimulusIntersections + '</td></tr>');
        }
    });
}

/* 
 * Copyright (C) 2024 Max Planck Institute for Psycholinguistics, Nijmegen
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


/* 
 Created on : 06 August 2024, 11:44 AM
 Author     : Peter Withers <peter.withers@mpi.nl>
 */

function populateListing(repository, username) {
    $.getJSON('buildhistory.json?' + new Date().getTime(), function (data) {
        document.getElementById('repositoryDiv').innerText = repository;
        document.getElementById('usernameDiv').innerText = username;
        for (var keyStringRaw in data.table) {
            var keyString = keyStringRaw.replace(/[^A-z0-9_-]/g, "");
            var experimentRow = document.getElementById(keyString + '_row');
            if (!experimentRow) {
                var tableRow = document.createElement('tr');
                experimentRow = tableRow;
                tableRow.id = keyString + '_row';
                for (var cellString of ['_repository', '_clone', '_committer', '_blank', '_experiment', '_edit', '_preview', '_static', '_date']) {
                    var tableCell = document.createElement('td');
                    tableCell.id = keyString + cellString;
                    tableRow.appendChild(tableCell);
                }
                document.getElementById('repositoryListing').appendChild(tableRow);
            }
            for (var cellString in data.table[keyStringRaw]) {
                if (cellString === '_date') {
                    var currentBuildDate = new Date(data.table[keyStringRaw][cellString].value);
                    document.getElementById(keyString + cellString).innerHTML = currentBuildDate.getFullYear() + '-' + ((currentBuildDate.getMonth() + 1 < 10) ? '0' : '') + (currentBuildDate.getMonth() + 1) + '-' + ((currentBuildDate.getDate() < 10) ? '0' : '') + currentBuildDate.getDate() + 'T' + ((currentBuildDate.getHours() < 10) ? '0' : '') + currentBuildDate.getHours() + ':' + ((currentBuildDate.getMinutes() < 10) ? '0' : '') + currentBuildDate.getMinutes() + ':' + ((currentBuildDate.getSeconds() < 10) ? '0' : '') + currentBuildDate.getSeconds();
                    document.getElementById(keyString + '_preview').innerHTML =
                        '<a href=\'/blocks/' + data.table[keyStringRaw]['_experiment'].value + '\'>preview</a>';
                    if (data.table[keyStringRaw]['_repository']) {
                        const repositoryName = /\/git\/([A-z0-9_]*).git/.exec(data.table[keyStringRaw]['_repository'].value);
                        if (repositoryName) {
                            if (repository === data.table[keyStringRaw]['_repository'].value || username === data.table[keyStringRaw]['_committer'].value) {
                                document.getElementById(keyString + '_edit').innerHTML =
                                    '<a href=\'/blocks/' + repositoryName[1] + '/' + data.table[keyStringRaw]['_experiment'].value + '\'>edit</a>';
                                document.getElementById(keyString + '_static').innerHTML =
                                    '<a href=\'/repository/' + repositoryName[1] + '/' + data.table[keyStringRaw]['_experiment'].value + '\'>media</a>' +
                                    '<a href=\'/experiment/' + repositoryName[1] + '/' + data.table[keyStringRaw]['_experiment'].value + '\'>experiment</a>';
                                document.getElementById(keyString + '_clone').innerHTML =
                                    ((repositoryName.length > 1) ? '<a href=\'/git/clone/' + repositoryName[1] + '\'>clone</a>' : '');
                                document.getElementById(keyString + '_preview').innerHTML = '';
                            }
                        }
                    }
                } else if (cellString === '_committer' || cellString === '_repository') {
                    var buildTimeSting = (typeof data.table[keyStringRaw][cellString].ms !== 'undefined' && data.table[keyStringRaw][cellString].built) ? '&nbsp;(' + parseInt(data.table[keyStringRaw][cellString].ms / 60000) + ':' + ((data.table[keyStringRaw][cellString].ms / 1000 % 60 < 10) ? '0' : '') + parseInt(data.table[keyStringRaw][cellString].ms / 1000 % 60) + ')' : '';
                    document.getElementById(keyString + cellString).innerHTML = (data.table[keyStringRaw][cellString].value + buildTimeSting);
                } else if (cellString === '_experiment') {
                    var buildTimeSting = (typeof data.table[keyStringRaw][cellString].ms !== 'undefined' && data.table[keyStringRaw][cellString].built) ? '&nbsp;(' + parseInt(data.table[keyStringRaw][cellString].ms / 60000) + ':' + ((data.table[keyStringRaw][cellString].ms / 1000 % 60 < 10) ? '0' : '') + parseInt(data.table[keyStringRaw][cellString].ms / 1000 % 60) + ')' : '';
                    document.getElementById(keyString + cellString).innerHTML = (data.table[keyStringRaw][cellString].value + buildTimeSting).replace(/[^A-z0-9_-]/g, "");
                }
            }
        }
        doFilter();
        doSort();
    });
}

var cloneIndicator = "-";

function populateMedia(repository, experiment, username) {
    var repositoryShort = repository.replace(/^\/git\/|\.git$/g, "");
    if ("*" === experiment) {
        $("#experimentName").html(repository);
    } else {
        $("#experimentName").html(repository + "&nbsp;" + experiment + "&nbsp;<a href=\"/git/status/" + repositoryShort + "\">status</a>" + "&nbsp;<a href=\"/git/diff/" + repositoryShort + "\">diff</a>" + "&nbsp;<a href=\"/blocks/" + repositoryShort + "/" + experiment + "\">blocks</a>" + "&nbsp;<a href=\"/experiment/" + repositoryShort + "/" + experiment + "\">form</a>");
    }
    $.get('/git/clone/' + repositoryShort, function (cloneData) {
        // using innerText because it preserves linebreaks
        $("#cloneLog")[0].innerText = cloneData + cloneIndicator;
        cloneIndicator = (cloneIndicator === "|") ? "/" : (cloneIndicator === "/") ? "-" : (cloneIndicator === "-") ? "\\" : "|";
        if ($("#cloneLog:contains(', done.')").length < 1) {
            $("#repositoryListing").hide();
            setTimeout(populateMedia, 1000, repository, experiment);
        } else {
            $.getJSON('/repository/list/' + repositoryShort + "/" + experiment, function (listingData) {
                if (listingData.listing) {
                    for (var keyStringRaw of listingData.listing) {
                        var keyString = keyStringRaw.replace(/[^A-z0-9_-]/g, "");
                        var listingRow = document.getElementById(keyString + '_row');
                        if (!listingRow) {
                            var tableRow = document.createElement('tr');
                            listingRow = tableRow;
                            tableRow.id = keyString + '_row';
                            for (var cellString of ['_folder', '_file', '_preview']) {
                                var tableCell = document.createElement('td');
                                tableCell.id = keyString + cellString;
                                tableRow.appendChild(tableCell);
                            }
                            document.getElementById('repositoryListing').appendChild(tableRow);
                        }
                        var lastSlash = keyStringRaw.lastIndexOf("/");
                        if (lastSlash < 0) {
                            $("#" + keyString + "_folder").html("/");
                            if (/\.[Xx][Mm][Ll]$/.exec(keyStringRaw) != null) {
                                $("#" + keyString + "_file").html('<a href="/repository/' + repositoryShort + '/' + keyStringRaw.replace(/\.[Xx][Mm][Ll]$/, "") + '">' + keyStringRaw + '</a>');
                            } else {
                                $("#" + keyString + "_file").html(keyStringRaw);
                            }
                        } else {
                            $("#" + keyString + "_folder").html(keyStringRaw.slice(0, lastSlash) + "/");
                            // $("#" + keyString + "_file").html(keyStringRaw.slice(lastSlash + 1));
                            $("#" + keyString + "_file").html('<a href="/repository/' + repositoryShort + '/' + keyStringRaw + '">' + keyStringRaw + '</a>');
                        }
                        if (/\.[Jj][Pp][Gg]$|\.[Pp][Nn][Gg]$|\.[Gg][Ii][Ff]$/.exec(keyStringRaw) != null) {
                            $("#" + keyString + "_preview").html("<img style=\"max-width: 100px;\" src=\"" + '/clone/' + repositoryShort + keyStringRaw + "\"/>");
                        }
                        if (/^\./.exec(keyStringRaw) != null) {
                            // skipping hidden files
                            $("#" + keyString + "_row").hide();
                        }
                    }
                } if (listingData.error) {
                    $("#errorMessage").html(listingData.error);
                } else {
                    $("#errorMessage").empty();
                }
                $("#cloneLog").hide();
                $("#repositoryListing").show();
            }).fail(function () {
                $("#errorMessage").html("Listing error");
            });
        }
        // http://frinexbuild.mpi.nl:7070/git/clone/experiments
        // http://frinexbuild.mpi.nl:7070/git/pull/experiments
        // http://frinexbuild.mpi.nl:7070/repository/experiments/electron_wrapper_test
    }).fail(function () {
        $("#errorMessage").html("Clone error");
    });
}

function doFilter() {
    var repository = document.getElementById('repositoryDiv').innerText;
    var username = document.getElementById('usernameDiv').innerText;
    var repositoryAll = !$("#repositoryAll").prop('checked');
    var userAll = !$("#userAll").prop('checked');
    $('#repositoryListing tr[id]').each(function () {
        if ((repositoryAll || //(data.table[keyString]['_repository'] !== undefined // older listings might not have the _repository and _committer
            $("#" + this.id.replace(/_row$/g, "_repository"))[0].innerHTML === repository)
            && (userAll || //(data.table[keyString]['_committer'] !== undefined // older listings might not have the _repository and _committer
                $("#" + this.id.replace(/_row$/g, "_committer"))[0].innerHTML === username)) {
            $(this).show();
        } else {
            $(this).hide();
        }
    });
}

function doSort() {
    var sortData = location.href.split('#')[1];
    var sortItem = (sortData) ? sortData.split('_')[0] : '9';
    var sortDirection = (sortData) ? sortData.split('_')[1] : 'd';
    if ($.isNumeric(sortItem)) {
        if (sortDirection === 'd') {
            $('#repositoryListing tr[id]').each(function () { }).sort(function (b, a) { return $('td:nth-of-type(' + sortItem + ')', a).text().localeCompare($('td:nth-of-type(' + sortItem + ')', b).text()); }).appendTo('#repositoryListing tbody');
            $('#repositoryListing tr:first').children('td').each(function (index) { $(this).children('a').attr('href', '#' + (index + 1) + '_a') });
        } else {
            $('#repositoryListing tr[id]').each(function () { }).sort(function (a, b) { return $('td:nth-of-type(' + sortItem + ')', a).text().localeCompare($('td:nth-of-type(' + sortItem + ')', b).text()); }).appendTo('#repositoryListing tbody');
            $('#repositoryListing tr:first').children('td').each(function (index) { $(this).children('a').attr('href', '#' + (index + 1) + '_d') });
        }
    }
}

$(window).on('hashchange', function (e) {
    doSort();
});

function enableFileDragDrop() {
    $("#experimentName").on("dragover", e => {
        $("#experimentName").addClass("fileDragDrop"); 
        e.preventDefault();
        e.stopPropagation();
    });
    $("#experimentName").on("dragenter", e => {
        $("#experimentName").addClass("fileDragDrop"); 
        e.preventDefault();
        e.stopPropagation();
    });
    $("#experimentName").on("dragleave", e => {
        $("#experimentName").removeClass("fileDragDrop");
    });
    $("#experimentName").on("drop", e => {
        $("#experimentName").removeClass("fileDragDrop");
        if(e.originalEvent.dataTransfer && e.originalEvent.dataTransfer.files.length) {
            e.preventDefault();
            e.stopPropagation();
            e.originalEvent.dataTransfer.files.forEach(dropFile => {
                console.log(dropFile);
            });
        }
    });
}

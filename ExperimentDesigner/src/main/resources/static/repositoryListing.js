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
        var repositoryAll = !$("#repositoryAll").prop('checked');
        var userAll = !$("#userAll").prop('checked');
        for (var keyString in data.table) {
            var experimentRow = document.getElementById(keyString + '_row');
            if ((repositoryAll || (data.table[keyString]['_repository'] !== undefined // older listings might not have the _repository and _committer
                && data.table[keyString]['_repository'].value === repository))
                && (userAll || (data.table[keyString]['_committer'] !== undefined // older listings might not have the _repository and _committer
                    && data.table[keyString]['_committer'].value === username))) {
                if (!experimentRow) {
                    var tableRow = document.createElement('tr');
                    experimentRow = tableRow;
                    tableRow.id = keyString + '_row';
                    for (var cellString of ['_repository', '_clone', '_committer', '_experiment', '_date', '_edit']) {
                        var tableCell = document.createElement('td');
                        tableCell.id = keyString + cellString;
                        tableRow.appendChild(tableCell);
                    }
                    document.getElementById('repositoryListing').appendChild(tableRow);
                }
                for (var cellString in data.table[keyString]) {
                    if (cellString === '_date') {
                        var currentBuildDate = new Date(data.table[keyString][cellString].value);
                        document.getElementById(keyString + cellString).innerHTML = currentBuildDate.getFullYear() + '-' + ((currentBuildDate.getMonth() + 1 < 10) ? '0' : '') + (currentBuildDate.getMonth() + 1) + '-' + ((currentBuildDate.getDate() < 10) ? '0' : '') + currentBuildDate.getDate() + 'T' + ((currentBuildDate.getHours() < 10) ? '0' : '') + currentBuildDate.getHours() + ':' + ((currentBuildDate.getMinutes() < 10) ? '0' : '') + currentBuildDate.getMinutes() + ':' + ((currentBuildDate.getSeconds() < 10) ? '0' : '') + currentBuildDate.getSeconds();
                        document.getElementById(keyString + '_edit').innerHTML =
                            '<a href=\'/blocks/' + data.table[keyString]['_experiment'].value + '\'>preview</a>';
                        if (data.table[keyString]['_repository']) {
                            const repositoryName = /\/git\/([A-z0-9_]*).git/.exec(data.table[keyString]['_repository'].value);
                            document.getElementById(keyString + '_repository').innerHTML =
                                ((repositoryName.length > 1) ? '<a href=\'/repository/clone/' + repositoryName[1] + '\'>clone</a>' : '');
                        }
                    } else if (cellString === '_repository' || cellString === '_committer' || cellString === '_experiment') {
                        var buildTimeSting = (typeof data.table[keyString][cellString].ms !== 'undefined' && data.table[keyString][cellString].built) ? '&nbsp;(' + parseInt(data.table[keyString][cellString].ms / 60000) + ':' + ((data.table[keyString][cellString].ms / 1000 % 60 < 10) ? '0' : '') + parseInt(data.table[keyString][cellString].ms / 1000 % 60) + ')' : '';
                        document.getElementById(keyString + cellString).innerHTML = data.table[keyString][cellString].value + buildTimeSting;
                    }
                }
            } else if (experimentRow) {
                experimentRow.remove();
            }
        }
        doSort();
    });
}

function doSort() {
    var sortData = location.href.split('#')[1];
    var sortItem = (sortData) ? sortData.split('_')[0] : '4';
    var sortDirection = (sortData) ? sortData.split('_')[1] : 'd';
    if ($.isNumeric(sortItem)) {
        if (sortDirection === 'd') {
            $('#repositoryListing tr:gt(0)').each(function () { }).sort(function (b, a) { return $('td:nth-of-type(' + sortItem + ')', a).text().localeCompare($('td:nth-of-type(' + sortItem + ')', b).text()); }).appendTo('#repositoryListing tbody');
            $('#repositoryListing tr:first').children('td').children('a').each(function (index) { $(this).attr('href', '#' + (index + 1) + '_a') });
        } else {
            $('#repositoryListing tr:gt(0)').each(function () { }).sort(function (a, b) { return $('td:nth-of-type(' + sortItem + ')', a).text().localeCompare($('td:nth-of-type(' + sortItem + ')', b).text()); }).appendTo('#repositoryListing tbody');
            $('#repositoryListing tr:first').children('td').children('a').each(function (index) { $(this).attr('href', '#' + (index + 1) + '_d') });
        }
    }
}

$(window).on('hashchange', function (e) {
    doSort();
});

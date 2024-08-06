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

 function populateListing(username) {
    $.getJSON('buildhistory.json?' + new Date().getTime(), function (data) {
        document.getElementById('usernameDiv').innerText = username;        
        for (var keyString in data.table) {
            var experimentRow = document.getElementById(keyString + '_row');
            if (!experimentRow) {
                var tableRow = document.createElement('tr');
                experimentRow = tableRow;
                tableRow.id = keyString + '_row';
                for (var cellString of ['_repository', '_committer', '_experiment', '_date']) {
                    var tableCell = document.createElement('td');
                    tableCell.id = keyString + cellString;
                    tableRow.appendChild(tableCell);
                }
                document.getElementById('experimentTable').appendChild(tableRow);
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
            $('#experimentTable tr:gt(0)').each(function () { }).sort(function (b, a) { return $('td:nth-of-type(' + sortItem + ')', a).text().localeCompare($('td:nth-of-type(' + sortItem + ')', b).text()); }).appendTo('#experimentTable tbody');
            $('#experimentTable tr:first').children('td').children('a').each(function (index) { $(this).attr('href', '#' + (index + 1) + '_a') });
        } else {
            $('#experimentTable tr:gt(0)').each(function () { }).sort(function (a, b) { return $('td:nth-of-type(' + sortItem + ')', a).text().localeCompare($('td:nth-of-type(' + sortItem + ')', b).text()); }).appendTo('#experimentTable tbody');
            $('#experimentTable tr:first').children('td').children('a').each(function (index) { $(this).attr('href', '#' + (index + 1) + '_d') });
        }
    }
}

$(window).on('hashchange', function (e) {
    doSort();
});

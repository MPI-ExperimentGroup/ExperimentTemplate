/* 
 * Copyright (C) 2025 Max Planck Institute for Psycholinguistics, Nijmegen
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
 * @since 22 May 2025 11:11 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */

$(document).ready(function () {
    var tableFloatingHeaderTop = $('#tableFloatingHeader').offset().top;
    $("<tr id=\"tablePaddingHeader\" style=\"position: static; top: 0px; left: 0px;\"><th>&nbsp;</th></tr>").insertBefore("#tableFloatingHeader");
    $('#tablePaddingHeader').hide();
    setColumnWidths();
    $(window).scroll(function () {
        if ($(window).scrollTop() > tableFloatingHeaderTop) {
            $('#tablePaddingHeader').height($('#tableFloatingHeader').height());
            $('#tableFloatingHeader').css({position: 'fixed', top: '0px', left: (-$(window).scrollLeft()) + $('#tableFloatingHeader').parents("table").find("td:first").offset().left + 'px'});
            $('#tablePaddingHeader').show();
        } else {
            $('#tableFloatingHeader').css({position: 'static', top: '0px', left: '0px'});
            $('#tablePaddingHeader').hide();
        }
        //                    $('#headerDiv').css({marginLeft: $('window').scrollLeft() + 'px', width: '100%'});
    });
});
function setColumnWidths() {
    for (index = 1; index <= $('#tableFloatingHeader').find('th').length; index++) {
        var cellWidth = $('#tableFloatingHeader').parents("table").find('td:nth-child(' + index + ')').width();
        // set the computed header cell width from the row
        $('#tableFloatingHeader').find('th:nth-child(' + index + ')').css('min-width', cellWidth);
        $('#tableFloatingHeader').find('th:nth-child(' + index + ')').css('max-width', cellWidth);
        // also set the first row cell width from the row for cases where the cell width was determined by the cell header
        $('#tableFloatingHeader').parents("table").find('td:nth-child(' + index + ')').css('min-width', cellWidth);
        $('#tableFloatingHeader').parents("table").find('td:nth-child(' + index + ')').css('max-width', cellWidth);
    }
}

#!/usr/bin/env node
/*
 * Copyright (C) 2015 Max Planck Institute for Psycholinguistics
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
 * Created on : Sep 1, 2015, 14:38 AM
 * Author : Peter Withers <peter.withers@mpi.nl>
 */

// this hook requires ncp which can be installed globally with: npm install -g ncp. Or into the directory of this script with npm install -g ncp
// also requires rmdir which can be installed with: npm install rmdir

var rmdir = require('rmdir');

console.log('Starting to remove the old GTW output from the www directory');

var relevantEntries = ["ExperimentTemplate/", "images/", "static/", "img/", "js/", "css/"];
relevantEntries.forEach(function (entry) {
    console.log('removing www/' + entry);
    rmdir('www/' + entry, function (err, dirs, files) {
        console.log(dirs);
        console.log(files);
        console.log(err);
    });
});


console.log('Starting to copy GTW output into the www directory');

var ncp = require('ncp').ncp;

ncp.limit = 16;
targetBuild = "configuration-frinex-gui-0.1.543-testing";
relevantEntries.forEach(function (entry) {
    ncp("../gwt-cordova/target/" + targetBuild + "/" + entry + "/", "www/" + entry + "/", function (err) {
        if (err) {
            return console.error(err);
        }
        console.log('GTW copy competed: ' + entry);
    });
});
ncp("../gwt-cordova/target/" + targetBuild + "/ExperimentTemplate.html", "www/index.html", function (err) {
    if (err) {
        return console.error(err);
    }
    console.log('copy index.html complete');
});

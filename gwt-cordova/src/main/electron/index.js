// import { makeUniversalApp } from '@electron/universal';
// await makeUniversalApp({
//     x64AppPath: 'path/to/App_x64.app',
//     arm64AppPath: 'path/to/App_arm64.app',
//     outAppPath: 'path/to/App_universal.app',
//   });
  
const {app, BrowserWindow, Menu, systemPreferences} = require('electron')

//ONLINE_OPTION /*
const express = require('express');
const path = require('path');
//ONLINE_OPTION */

let mainWindow;

const dataSubmitUrl = '@experiment.destinationServerUrl@/@experiment.configuration.name@-admin/';

const isDebugMode = (dataSubmitUrl.includes('staging.mpi.nl')) && app.commandLine.hasSwitch('debug-mode');

// TODO: based on the experiment XML request microphone and or camera permissions
const microphone = systemPreferences.askForMediaAccess('microphone');
// const camera = systemPreferences.askForMediaAccess('camera');

app.setAboutPanelOptions({
    applicationName: "Frinex",
    applicationVersion: "@project.version@"
});

if (!isDebugMode) {
    var template = [{
        label: "Application",
        submenu: [
            { label: "About Application", selector: "orderFrontStandardAboutPanel:" },
            { type: "separator" },
            { label: "Quit", accelerator: "Command+Q", click: function() { app.quit(); }}
        ]}, {
        label: "Edit",
        submenu: [
            { label: "Undo", accelerator: "CmdOrCtrl+Z", selector: "undo:" },
            { label: "Redo", accelerator: "Shift+CmdOrCtrl+Z", selector: "redo:" },
            { type: "separator" },
            //{ label: "Cut", accelerator: "CmdOrCtrl+X", selector: "cut:" },
            //{ label: "Copy", accelerator: "CmdOrCtrl+C", selector: "copy:" },
            { label: "Paste", accelerator: "CmdOrCtrl+V", selector: "paste:" },
            //{ label: "Select All", accelerator: "CmdOrCtrl+A", selector: "selectAll:" }
        ]}
    ];
    
    Menu.setApplicationMenu(Menu.buildFromTemplate(template));
}

const createWindow = () => {
//ONLINE_OPTION /*
    const app = express();
//ONLINE_OPTION */
    mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        fullscreen: !isDebugMode
    });

//ONLINE_OPTION /*
    app.use('/', express.static(path.join(__dirname, 'src', 'renderer')));
    //app.use('/webjars', express.static(path.join(__dirname, 'www', 'webjars')));
    app.listen(5000);
    mainWindow.loadURL(`http://localhost:5000/index.html`);
//ONLINE_OPTION */

//ONLINE_OPTION mainWindow.loadURL('@experiment.destinationServerUrl@/@experiment.configuration.name@/index.html');

    if (isDebugMode) {
        mainWindow.webContents.openDevTools()
    }

    mainWindow.on('closed', () => {
        mainWindow = null;
    });
};

app.on('ready', createWindow);

app.on('window-all-closed', () => {
    app.quit();
});

app.on('activate', () => {
    if (mainWindow === null) {
        createWindow();
    }
});

const {app, BrowserWindow, Menu} = require('electron')
const express = require('express');
const path = require('path');

let mainWindow;

const dataSubmitUrl = '@experiment.destinationServerUrl@/@experiment.configuration.name@-admin/';

const isDebugMode = (dataSubmitUrl.includes('staging.mpi.nl')) && app.commandLine.hasSwitch('debug-mode');

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
    const app = express();
    mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        fullscreen: !isDebugMode
    });

    app.use('/', express.static(path.join(__dirname, 'src', 'renderer')));
    //app.use('/webjars', express.static(path.join(__dirname, 'www', 'webjars')));
    app.listen(5000);
    mainWindow.loadURL(`http://localhost:5000/index.html`);

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

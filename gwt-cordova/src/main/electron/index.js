const {app, BrowserWindow, Menu} = require('electron')
const express = require('express');
const path = require('path');

let mainWindow;

const dataSubmitUrl = '@experiment.destinationServerUrl@/@experiment.configuration.name@-admin/';

const isDebugMode = (dataSubmitUrl.includes('staging.mpi.nl')) && app.commandLine.hasSwitch('debug-mode');

if (!isDebugMode) {
    Menu.setApplicationMenu(null);
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

const {app, BrowserWindow} = require('electron')
const express = require('express');
const path = require('path');

let mainWindow;

const createWindow = () => {
    const app = express();
    mainWindow = new BrowserWindow({
        width: 800,
        height: 600,
        fullscreen: true
    });

    app.use('/', express.static(path.join(__dirname, '..', 'renderer')));
    app.listen(5000);
    mainWindow.loadURL(`http://localhost:5000/index.html`);

//    mainWindow.webContents.openDevTools();
//mainWindow.setFullScreen(true);
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

#!/bin/bash

# Copyright (C) 2020 Max Planck Institute for Psycholinguistics
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation; either version 2
# of the License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program; if not, write to the Free Software
# Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.


# @since 07-09-2020 10:35 AM (creation date)
# @author Peter Withers <peter.withers@mpi.nl>

# This script generates a distributable DMG file for OSX. This script can only be successfully run on an OSX machine.
# 
# Prerequisites for this script:
#        yarn
#        OSX

yarn
touch src/renderer/index.js
cp src/renderer/static/splash.png ./background.png
convert -resize 512x512^ -gravity center -extent 512x512 -quality 100 src/renderer/static/icon.png ./icon.png
mkdir dist
cp background.png dist/
yarn dist --mac dmg
ls dist/*.dmg
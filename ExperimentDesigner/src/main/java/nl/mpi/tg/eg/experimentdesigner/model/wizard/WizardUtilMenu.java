/*
 * Copyright (C) 2018 Max Planck Institute for Psycholinguistics, Nijmegen
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
package nl.mpi.tg.eg.experimentdesigner.model.wizard;

/**
 * @since May 4, 2018 11:31:10 AM (creation date)
 * @author Peter Withers <peter.withers@mpi.nl>
 */
public class WizardUtilMenu {

    protected String title;
    protected String text;
    protected String[] menuItems;
    protected boolean isMainMenu;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isIsMainMenu() {
        return isMainMenu;
    }

    public void setIsMainMenu(boolean isMainMenu) {
        this.isMainMenu = isMainMenu;
    }

    public String[] getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(String[] menuItems) {
        this.menuItems = menuItems;
    }

}

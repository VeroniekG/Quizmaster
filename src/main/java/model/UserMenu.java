package model;

import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * UserMenu Model Object. Represents an application menu for the current user, which is created
 * based on the user-role. Menu-items are described by class {@link UserMenuItem}.
 *
 * @author Daniel Leertouwer
 * @version 1.0.0
 * @see UserMenuItem
 * @see Role
 * @since 1.0
 */
public class UserMenu {

    private List<MenuItem> menuItems;

    public UserMenu() {
        menuItems = new ArrayList<>();
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    /**
     * Sets the menu-items for the supplied User, based on the user-role.
     * UserMenuItem[] userMenuItems is set to contain all possible menu-items, which is then
     * filtered by comparing the user role to the role a specific item is applicable ror. If an item
     * is applicable for the currently logged in user, it is added to ArrayList menuItems after a
     * corresponding eventhandler is attached.
     */
    public void setMenuItemsForUser(User user) {
        Role applicationUserRole = user.getRole();
        UserMenuItem[] userMenuItems = UserMenuItem.class.getEnumConstants();
        for (UserMenuItem item : userMenuItems) {
            if (applicationUserRole.equals(item.getMenuItemForRole())) {
                MenuItem menuItem = item.getMenuItem();
                menuItem.setOnAction(item.getOnActionHandler());
                menuItems.add(menuItem);
            }
        }
    }

}
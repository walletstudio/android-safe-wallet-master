package de.schildbach.wallet.util;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;

import java.lang.reflect.Field;

public class NavigationViewUtils {

    public static void disableShiftMode(BottomNavigationView navigationView) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
        try { 
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                //noinspection RestrictedApi 
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated 
                //noinspection RestrictedApi 
                item.setChecked(item.getItemData().isChecked());
            } 
        } catch (Exception e) {
        } 
    }

    public static BottomNavigationItemView getBottomNavigationItemView(BottomNavigationView navigationView, int position) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigationView.getChildAt(0);
        return (BottomNavigationItemView) menuView.getChildAt(position);
    }

}
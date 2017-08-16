#确保移除BottomNavigationView的动画效果不被混淆
-keepclassmembers class android.support.design.internal.BottomNavigationMenuView {
    boolean mShiftingMode;
}
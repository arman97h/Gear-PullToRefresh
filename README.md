# Gear-PullToRefresh

[![](https://jitpack.io/v/arman97h/Gear-PullToRefresh.svg)](https://jitpack.io/#arman97h/Gear-PullToRefresh)

[GearRefreshLayout](https://github.com/arman97h/Gear-PullToRefresh/blob/master/gearpulltorefresh/src/main/java/com/arman97h/gearpulltorefresh/refreshview/GearRefreshLayout.java) is based on the [SwipeRefreshLayout](https://developer.android.com/reference/android/support/v4/widget/SwipeRefreshLayout).
 The `GearRefreshLayout` should be used whenever the user can refresh 
the contents of a `view` via a vertical swipe gesture. 
[BaseGear](https://github.com/arman97h/Gear-PullToRefresh/blob/master/gearpulltorefresh/src/main/java/com/arman97h/gearpulltorefresh/gear/BaseGear.java) & [ConnectibleGear](https://github.com/arman97h/Gear-PullToRefresh/blob/master/gearpulltorefresh/src/main/java/com/arman97h/gearpulltorefresh/gear/ConnectibleGear.java) classes can be used for customization.

Inspired by : [Gear-Powered Pull-to-Refresh UI animation](https://www.behance.net/gallery/24541501/Gear-Powered-Pull-to-Refresh-UI-animation)

> Note: The `GearRefreshLayout` supports all of the views: `ListView`, 
`GridView`, `ScrollView`, `FrameLayout`, or Even a single `TextView`

![](https://raw.githubusercontent.com/arman97h/Gear-PullToRefresh/master/gearrefresh.gif)

## Installation

Add it in your root build.gradle at the end of repositories:

```
  allprojects {
	  repositories {
		 ...
		 maven { url 'https://jitpack.io' }
	  }
  }
```

Add the dependency
```
  dependencies {
	 implementation 'com.github.arman97h:Gear-PullToRefresh:0.1.6'
  }
```

## Usage

#### Config in xml
```xml
<?xml version="1.0" encoding="utf-8"?>
<com.arman97h.gearpulltorefresh.refreshview.GearRefreshLayout
        android:id="@+id/refresh_layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        <android.support.v7.widget.RecyclerView
            android:id="@+id/recycler_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent"/>
</com.arman97h.gearpulltorefresh.refreshview.GearRefreshLayout>
```

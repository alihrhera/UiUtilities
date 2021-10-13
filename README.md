[![](https://jitpack.io/v/alihrhera/UiUtilities.svg)](https://jitpack.io/#alihrhera/UiUtilities)
![Android Studio v4.0](https://img.shields.io/badge/Android%20Studio-v4.2.1-green)

# UiUtilities
### a custom android libe help to show or hide view as expande row 

<img src="https://raw.githubusercontent.com/alihrhera/UiUtilities/master/images/ExpandRow.gif" alt="ExpandRow Image">


## Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
## Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.alihrhera:UiUtilities:0.1.0'
	}

## Step 3. Use The Lib 
    <com.hrhera.expandrowview.ExpandRow
        app:showDivider="true"
        app:dividerColor="@color/black"
        
        app:clickAnyPositionToOpen="true"
        app:defaultOpen="true"
        
        app:headerBackground="@color/black"
        app:child_id="@layout/row_to_hide"

        
        app:endIconColor="@color/white"
        app:startIconColor="@color/white"
        app:startIcon="@android:drawable/ic_menu_week"
        
        app:titleTextColor="@color/white"
        app:titleTextSize="12sp"
        app:title="@string/app_name"

        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_constraintTop_toTopOf="parent" />

### And it's inherits CardView, so you can use all the properties of the CardView and modify it as you wish.
Like cardElevation, cardCornerRadius, cardBackgroundColor and so on . 

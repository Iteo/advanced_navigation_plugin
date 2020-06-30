# Advanced Navigation Plugin

Advanced Nav Plugin is a library project which brings several improvements to navigation component from Android Jetpack.
It's inspired by [Advanced Navigation Sample by Google](https://github.com/android/architecture-components-samples/tree/master/NavigationAdvancedSample)

# Installation
Add the following dependency to your `build.gradle` file:

```
dependencies {
    implementation 'com.iteo.android_navigation:core:0.0.1'
}
```

# Usage

## BottomNavBar with separate back stacks

```kotlin
val navGraphIds = listOf(
    R.navigation.list_nav_graph,
    R.navigation.notifications_nav_graph,
    R.navigation.filtered_list_nav_graph
)

bottomNav.setupWithNavController(
    navGraphIds,
    childFragmentManager,
    R.id.fragmentContainer,
    requireActivity().intent
)
```

## Navigation from Notifications

```kotlin
val nestedDeepLink = NestedDeepLink(
    mainGraphId = R.navigation.main_nav_graph,
    mainEntryDestination = R.id.entryFragment,
    nestedGraphId = R.navigation.filtered_list_nav_graph,
    nestedDestination = R.id.detailsFragment,
    nestedDestinationBundle = ItemDetailsFragmentArgs(1).toBundle()
)

val not = NotificationCompat.Builder(requireContext(), mChannel.id)
  .setSmallIcon(R.drawable.ic_airplane)
  .setContentTitle("Title")
  .setContentText("Test")
  .setPriority(NotificationCompat.PRIORITY_DEFAULT)
  .setContentIntent(nestedDeepLink.toPendingIntent(requireContext()))
  .build()

with(NotificationManagerCompat.from(requireContext())) {
    notify(1, not)
}

```

Remember to pass intent from your activity to your bottom bar extension function `handleIntent`

## Safe navigate

Avoid double click crashes by using navigate extensions function. Exceptions will be printed in logcat.

```kotlin
val adapter = ItemAdapter { clickedItem ->
    navigate(ListFragmentDirections.openDetails(clickedItem.id))
}
```

## Global deep navigation actions

1. Create global action in one of your graphs
```xml
<action android:id="@+id/openDetails"
        app:destination="@id/detailsFragment">
    <argument android:name="itemId"
              app:argType="integer" />
</action>
```
2. Implement `BottomGraphContainer` interface in your bottom bar container fragment(s)

```kotlin
private val navGraphIds = listOf(
    R.navigation.list_nav_graph,
    R.navigation.notifications_nav_graph,
    R.navigation.filtered_list_nav_graph
)

class DashboardFragment @Inject constructor() : Fragment(), BottomGraphContainer {
    {...}

    override fun bottomGraphContainerSettings(): BottomBarContainerSetting {
        return BottomBarContainerSetting(navGraphIds, R.id.fragmentContainer, bottomNav)
    }
}
```
3. Use `navigateGlobally` function
```kotlin
navigateToDifferentGraph.setOnClickListener {
    val direction = ListNavGraphDirections.openDetails(itemId = 2)
    navigateGlobally(direction)
}
```

## Deeplink `deep` navigation

Navigate to your nested bottom bar graphs by passing intent to your BottomNavBar container fragment.

1. Register your deeplink in your main graph AND in your bottom bar graph.

```xml
<fragment android:id="@+id/dashboardFragment"
          android:name="com.iteo.android_navigation.dashboard.DashboardFragment"
          tools:layout="@layout/fragment_dashboard"
          android:label="DashboardFragment" >
    ...

    <deepLink android:id="@+id/deepLink" app:uri="www.example.com/item/{itemId}" />
</fragment>

```

```xml
    <fragment android:id="@+id/detailsFragment"
              android:name="com.iteo.android_navigation.item_details.ItemDetailsFragment"
              android:label="DetailsFragment"
              tools:layout="@layout/fragment_item_details">

        <argument android:name="itemId"
                  app:argType="integer" />

        <deepLink android:id="@+id/deepLink" app:uri="www.example.com/item/{itemId}" />
    </fragment>
```
2. Pass your intent from activity to bottom bar extension function `handleIntent`. In our example we are using RxRelay (publishsubject) to emit new intent globally in application.

```kotlin
observeNewIntentDisposable = newIntentObservable
    .subscribeBy(
        onNext = { intent ->
            bottomNav.handleDeepLink(
                navGraphIds,
                childFragmentManager,
                R.id.fragmentContainer,
                intent
            )
        }
    )
```

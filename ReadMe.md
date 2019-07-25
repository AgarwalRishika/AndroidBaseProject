Dagger

AndroidInjector<T>
Performs members-injection for a concrete subtype of a core Android type (e.g., Activity or Fragment).

AndroidInjector.Factory<T>
Creates AndroidInjectors for a concrete subtype of a core Android type.

HasActivityInjector
Provides an AndroidInjector of Activitys.

HasBroadcastReceiverInjector
Provides an AndroidInjector of BroadcastReceivers.

HasContentProviderInjector
Provides an AndroidInjector of ContentProviders.

HasFragmentInjector
Provides an AndroidInjector of Fragments.

HasServiceInjector
Provides an AndroidInjector of Services.

DispatchingAndroidInjector<Activity>
Performs members-injection on instances of core Android types (e.g. Activity, Fragment)
that are constructed by the Android framework and not by Dagger.
This class relies on an injected mapping from each concrete class to an AndroidInjector.
Factory for an AndroidInjector of that class. Each concrete class must have its own entry in the
 map, even if it extends another class which is already present in the map. Calls Object.getClass()
 on the instance in order to find the appropriate AndroidInjector.Factory.
 
 AndroidInjection Module
 Contains bindings to ensure the usability of dagger.android framework classes. 
 This module should be installed in the component that is used to inject the Application class.
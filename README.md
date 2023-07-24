# Stonks

This is a simple stonks app that displays a list of stonks and their current prices.
## Libraries Used
Koin - For dependency injection and managing app modules
Ktor - For implementing the network layer and API calls
Jetpack Compose - Modern toolkit for building native UI

## Architecture
The app is built using MVVM architecture with separation of concerns in mind. 
ViewModels handle the presentation logic and Flows are used to update the UI declaratively.

Dependencies are injected using Koin to keep classes loosely coupled. 
I'm actually more familiar with Dagger/Hilt but I got to like using Koin for it's simplicity and speed of set-up
in some KMP side projects I've been working on.

Ktor implements the networking using suspend functions for async API calls. Coroutines are used to simplify async logic.
I'm also a big fan of Retrofit because it's more full-featured for REST APIs but I chose Ktor since it's lightweight,
kotlin-centric, and made sense for the size and scope of the project. 

Jetpack Compose UI components are kept reactive with state hoisting. This avoids passing callbacks down and improves composability.
## Screenshots

Some key architectural decisions:

## Tradeoffs
 - I definitely would have modularized a real app, but didn't want to spend the extra set up time.
 - Error handling is pretty minimal, which would normally deserve more time.
 - I deleted the proguard rules for the sake of time, but would normally keep them.
 - I would also spend the extra time to migrate to Convention Gradle plugins for Kotlin DSL to clean up all those esoteric gradle files.
## Running the App

## External Libraries

## Final Thoughts
# Stonks

Stonks is a simple, yet effective Android application that provides users with a list of stocks and their current prices. The application is built with a focus on modern Android development practices and libraries.

## Libraries Used

The application leverages several popular libraries and tools in the Android ecosystem:

- **Koin**: A pragmatic lightweight dependency injection framework for Kotlin. It's used for managing app modules and providing dependencies, which helps in keeping the code clean and the components loosely coupled. While Koin offers simplicity and speed of setup, it does trade off compile-time safety which frameworks like Dagger provide. This means that dependency issues might not be caught until runtime. For me though, the ease of use and reduced boilerplate code often make Koin a preferred choice for smaller projects or projects with less complex dependency graphs.

- **Ktor**: A modern multiplatform asynchronous HTTP client used for implementing the network layer and making API calls. It uses suspend functions for asynchronous API calls, which simplifies the async logic.

- **Jetpack Compose**: A modern toolkit for building native UI. It's used to build the UI components of the app in a declarative way, which improves the readability and maintainability of the UI code.

- **Spotless**: A code formatting tool that integrates with ktlint to ensure that the code follows the same style guidelines, which improves the readability and maintainability of the code.

- **Detekt**: A static code analysis tool for Kotlin that helps to detect potential issues in the code, such as code smells, bugs, and deviations from the coding standards.

## Architecture

The application is built using the Model-View-ViewModel (MVVM) architecture, which promotes a clear separation of concerns and an easier testing strategy. The ViewModels handle the presentation logic, while Flows are used to update the UI in a declarative and reactive way.

The dependencies are injected using Koin, which allows for a more flexible and less boilerplate code than traditional dependency injection frameworks. While I have more experience with Dagger/Hilt, I've found Koin to be simpler and quicker to set up.

The networking layer is implemented using Ktor, which provides a lightweight, Kotlin-centric approach to making asynchronous API calls. While Retrofit might be more full-featured for REST APIs, Ktor was chosen for its simplicity and suitability for the size and scope of this project.

The UI components are built using Jetpack Compose and are kept reactive with state hoisting. This approach avoids passing callbacks down the component tree and improves the composability and reusability of the components.

## Screenshots

| Stonk List Content                                                                                          | Error Dialog                                                                                                | Empty Content                                                                                               |
|-------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------|
| <img src=https://github.com/JJSwigut/Stonks/assets/62301576/326f9046-b2e3-4cd7-9746-52f5d04e3c21 width=350> | <img src=https://github.com/JJSwigut/Stonks/assets/62301576/2ca37d16-8546-42f7-b8cc-0de464cbe960 width=350> | <img src=https://github.com/JJSwigut/Stonks/assets/62301576/81484f4e-262e-46ca-911a-aa13533c49e1 width=350> |

## Tradeoffs

- **Modularization**: While modularization would be beneficial in a larger, real-world application to improve build times and enforce separation of concerns, it was not implemented in this project to save on initial setup time.

- **Error Handling**: The error handling in the current version of the app is minimal. In a production app, a more robust error handling strategy would be implemented.

- **Proguard Rules**: The Proguard rules were removed for the sake of time. However, in a production app, they would be included to reduce the APK size and obfuscate the code.

## Running the App

The application can be run directly from Android Studio by clicking on the 'Run' button.

## Final Thoughts

This project was a great opportunity to explore and implement modern Android development practices and libraries. It's a testament to the power and flexibility of these tools and how they can be used to build a simple, yet effective application. I look forward to receiving any feedback and making improvements.

## Future Improvements

- **Testing**: Implement screenshot testing to verify the UI remains consistent across different screen sizes and resolutions.

- **Continuous Integration**: Set up a CI/CD pipeline to automate the build, test, and release process.

- **Accessibility**: Ensure the app is accessible to all users by following best practices for accessibility.

- **Localization**: Add support for multiple languages to reach a wider audience.

# GoustoApp
An simple GoustoApp utilizing Gousto Products API for showing products lists.

<div align="center">
  <sub>Built with ❤︎ by
  <a>Muhammad Usama Yasin</a>
</div>
<br/>

## Features
* Home Screen showing list of products
* Offline Support.
* Detail Screen to show more information about a product.

## Architecture
* Built with Modern Android Development practices.
* Utilized Usecase, Repository pattern for data.
* Includes valid Unit tests for Repository and Usecases.

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying data changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
- [Dependency Injection](https://developer.android.com/training/dependency-injection)
  - [Hilt](https://dagger.dev/hilt) - Easier way to incorporate Dagger DI into Android apps.
- [Room](https://developer.android.com/reference/androidx/room/package-summary) - For saving Data in local DB and to provide offline support.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android.
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - For writing Gradle build scripts using Kotlin.
- [MockK](https://mockk.io) - For Mocking and Unit Testing.
- [Espresso](https://developer.android.com/training/testing/espresso) - Espresso to write concise, beautiful, and reliable Android UI tests.
- [Glide](https://github.com/bumptech/glide) - Glide is a fast and efficient open source media management and image loading framework for Android.

## Observations:
- API does not support Pagination. No params to utilize for pagination with API. But as a work-around ,we can save all the data in our LocalDatabase and then implement pagination on the room to fetch records form the localDatabase. But it will take more time to do. So, I left it as an improvement.

## Improvements:
 - Multi Module
 - Add more Unit/UI Tests
 - Build UI with Compose

## 👨 Developed By
**Muhammad Usama Yasin**

[![Linkedin](https://img.shields.io/badge/-linkedin-grey?logo=linkedin)](www.linkedin.com/in/-usama-yasin)

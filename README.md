## 🛠 Tech Stacks

- Kotlin
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- Android Jetpack
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel?gclid=CjwKCAjwvNaYBhA3EiwACgndgjUoa6vdlcCNKAF9x1TQVGibh7eKA2BieACmNWUhWw2Sr-Fo37glTRoCH20QAvD_BwE&gclsrc=aw.ds)
- [Retrofit](https://github.com/square/retrofit)
- [Retrofit Adapters](https://github.com/skydoves/retrofit-adapters)
- [OkHttp](https://github.com/square/okhttp)
- [Gson](https://github.com/google/gson)
- [Hilt](https://dagger.dev/hilt/)
- [Mockito](https://site.mockito.org/)

## 🏛️ Project Structure

**`common`**:

* `feature`
  - `data`
      - `entity`
      - `source`
        - `remote`
  - `domain`
      - `entity`
      - `repository`
      - `usecase`
  - `presentation`
      - `entity`
      - `[ClassName]Screen`
      - `[ClassName]ViewModel`

Clean Architecture
------
In this project I am using Clean Architecture approach to make separation of concern that could be match into SOLID Principle. It will give :
- Test your data layer, domain layer and also presentation layer on a different test cases, then you can always update or change it based on your needs and also based on your problem on each layers
- Maintainable 
- Isolate business logic without disrupting UI
- Can implement any of android arch pattern on presentation layer without making any changes on business logic(usecase) every time change arch pattern
- Team easy manage their own arch pattern on each presentation layer based on what their needs and problems without changes business logic(usecase)
- Avoid refactor code in the feature and engineers can be focus implement feature

How to use it?
------
- Clone this project using `git clone [url]`
- build the project by using `./gradlew clean :app:assembleDebug`
- You can run the Unit test by using `./gradlew clean test`

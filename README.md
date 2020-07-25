# [WIP] Spatz🐦

Spatz is sample application.  
The concept of this sample is my development practice.

## Architecture

This app is based on the MVVM architecture and has a layered architecture.  
The dependency of the application is like this.  
`presentation` -> `domain` <- `data`

Spatz uses Dagger-Hilt(alpha) for dpendency inject.

## Libraries

- Kotlin
    - Coroutines
- Android Architecture Components
    - ViewModel
    - LiveData
    - Navigation
- Dagger Hilt (2.28.3-alpha)
- Material Components for Android
- Groupie
- Coil
- Insetter
- Timber

## Todo

- [ ] call Twitter API
- [ ] unit testing
- [ ] apply theming

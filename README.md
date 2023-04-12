# Weather App

Weather app is a small app to search a star war character and see details of the character.

## API

(Swah-pee) is the world's first quantified and programmatically accessible data source for all the
data from the Star Wars canon universe!

  ```bash
  https://swapi.dev/
  ```

## Tech Stack Used

* MVVM
* Live Data
* Navigation
* Coroutines
* Dagger Hilt
* Data Binding
* Mockito
* Clean Architecture

## Why this tech stack?

MVVM is easy to code, works like charm with UI bindings, easily testable, does not hold a reference
to the view, lifecycle aware.LiveData works well with view models and is lifecycle aware, easy to
update UI with bindings. Navigation is a newly introduced stack that makes life easier to maintain
backstacks and UI navigations with the use of nav graphs. Coroutines are Kotlin's way of using
threads which are lightweight and a great match for asynchronous programming. Hilt is a new and good
dependency injection framework. Clean architecture gives us a clear separation of concerns, follows
the SOLID principle, and helps write good maintainable code.

## Control Flow

Fragment -> ViewModel -> Usecase -> Repository ->
Remote Data Source -> API Service

Here layers are as below

* Fragment & ViewModel - PRESENTATION
* Usecase - DOMAIN
* Repository & Remote Data Source & API Service - DATA

Mappers are used to convert data whenever it travels from one layer to other.

## How do I add <something>?

Fragment -> in package /presentation/ui create your Fragment and add the corresponding viewmodel to
it.Add dependency for your view model in /presentation/di/AppModule.kt. Do not forget to add your
fragment to the nav_graph under src/main/res/navigation along with arguments and actions.

UseCase -> add your usecase in /domain/usecase which extends BaseUseCase and make a call to the
repository in executeusecase

Repository -> add an interface of your repository in /domain/repository and then add implementation
of this interface in /data/repository

New API -> add new service endpoint in /data/source/remote/service/ApiService.kt

***Do not forget to add tests in their respective folders under src/test.

## What can be improved?

* Does not support local storage.
* UI can be improved.
* API service response can be wrapped around a proper sealed Response class that checks for HTTP
  errors.
* UI is not tested.
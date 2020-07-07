# Simple-Word-Game
Simple 2Player word game to demonstrate use of  Clean-Architecture pattern along with Android Architecture Components and use of Kotlin Coroutines

The game logic is as simple as possible : each player writes a word and the other player should write another word starting with the last character of that word.

The game is offline and has no database for the sake of simplicity, and an InMemmoryDataSource is the only data source,
but thanks to the clean architecture and repository pattern used, database and remote data sources can be easily added by implementing abstract class GameDataSource.

LiveData is used to update binded view directly from GameViewModel, and kotlin coroutines are used to perform other async operations.

As recommended by clean-architecture pattern, bussiness logic is seprated completely from framework tools in core module.
App module contains presentation package including views and viewModels , and framework package which includes framework-dependant data-source implementations and use-cases(interactors)

Kotlin Koin is used  for dependency injection


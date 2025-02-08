The project is ready to run in Android Studio.

please enter your Tmdb Api-Key in local.properties file
in the following format:
`TMDB_API_KEY=YOUR_API_KEY`

The main screen of the project is the Genres screen.
I created the Home screen to show the usage of Navigation in Jetpack Compose,
And to have some fun with the search bar :)

The Repository contains branches to show the progress of writing this project.

The general architecture is based on the Google best practice Sunflower App:
https://github.com/android/sunflower

## Some personal notes I wrote along the way that are mentioned worthy:
#### Key differences between flows:

| Feature                     | `Flow`                                  | `StateFlow`                               | `SharedFlow`                             |
|-----------------------------|-----------------------------------------|------------------------------------------|------------------------------------------|
| **Type of Flow**             | Cold (lazy) stream of asynchronous values | Hot stream that holds a single value at a time | Hot stream that broadcasts values to multiple collectors |
| **Value Emission**           | Values are emitted when collected, each collector gets its own emission | Always has a value, collectors get the most recent one immediately | Multiple collectors can receive the same value simultaneously |
| **Initial Value**            | None (no initial value)                | Has an initial value                     | No initial value (can be empty at the start) |
| **State Retention**          | Does not retain any value after emission | Retains the last emitted value, even if there are no collectors | Does not retain the last value (unless configured) |

#### LaunchedEffect:
triggers recomposition based on the state of the parameter it was launched on.
a safe-guard for responding to side effects - e.g. network call that needs to change the UI when finished.

#### Why I didn't use the TMDB SDK library:
1) I assumed it wasn't the idea behind the assignment.
2) It uses XML views and not Compose.

#### Why I didn't use M3 dynamic colors pallet:
1) Colorful posters look best on dark backgrounds regardless of the environment light.
2) Light attracts bugs.

#### Auto Genered code:
To create the API models I used a tool that converts JSON to Kotlin POJO:
https://json2kt.com/
I have used this tool for a couple of years, and that's why some model classes in this project look 'Auto Generated'.

#### Coil VS Glide
Honestly, I chose Coil because I wanted to try it out.
I didn't do any research on which one is better.

#### hard-coded strings
along the project, I've left some hard-coded strings. 
Obviously, this is a very bad practice, especially in multilingual projects.
I wanted to focus on the Compose part and data flow instead of Context management.

#### Gradle Hell
the reason I didn't fully test and added more complex features 
is because I encountered a version synchronization nightmare that took me a full day to resolve before I even started writing the project itself.
(apparently, some Hilt versions support kapt only and not ksp)

# Thank you so much for the opportunity and your time,
### I had a lot of fun regardless of the outcome.



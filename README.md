# Room Booker

This is an example Android app that displays a list of rooms and allows users to book them. It's somewhat unrealistic but demonstrates what can be built within a few hours with Android Jetpack libraries, Retrofit, and Kotlin flows using MVI architecture. 

Featured dependencies:
- [Compose](https://developer.android.com/jetpack/compose)
- [Hilt](https://developer.android.com/jetpack/androidx/releases/hilt)
- [Room](https://developer.android.com/jetpack/androidx/releases/room) 
- [Retrofit](https://square.github.io/retrofit/) with [Moshi](https://github.com/square/moshi/)
- [Coil](https://github.com/coil-kt/coil)

Key features:
- Room data from API requests is backed up to a database so that the list of rooms can be displayed when users are offline. This includes the images, which are cached to memory and disk. Fresh data is fetched after each button tap and when users swipe down at the top of the list. 
- The UI looks nice in light or dark mode and in portrait or landscape, has a collapsing app bar, uses snackbars for button feedback, and has loading spinners for images and swipe-to-refresh.  

With more time, I would have addressed the following:
- Unit/UI testing is needed!
- Although the UI is adaptive for different screen sizes and orientations, it would be better to alter the layout for larger screen sizes and landscape orientations. I held off on using it because it's still experimental, but the [LazyVerticalGrid](https://developer.android.com/jetpack/compose/lists#grids) composable looks promising and seems to work well in my testing as a scrollable grid of cells that adapts to the screen size.
- There should be improved handling of errors and exceptions to provide a better UX. Why did a booking fail? When is there a problem with the internet connection?
- To actually serve as a usable app, at a minimum, there needs to be a way when booking to indicate who the users are and what date and time they want among those available.

## Installation

1. Install or update to (at least) Android Studio Arctic Fox | 2020.3.1.
2. Clone this repository and import it into Android Studio.

## License

MIT License

Copyright (c) [2021] [Greg Turek]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

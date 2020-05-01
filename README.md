<h2 style="margin-bottom: 0;" align="center">Easy Task</h2>
<p align="center">Easy Task Management Android App with online data syncing.</p>

----------```Sign Up```--------------------```Login```----------------------```Update Profile```--------

<img src="https://user-images.githubusercontent.com/15268903/80799678-4aedb080-8bc9-11ea-81b6-bd216559ae07.gif" height="400" width="200" title = "Sign Up">   <img src="https://user-images.githubusercontent.com/15268903/80799983-20e8be00-8bca-11ea-82d8-4d58da82255c.gif" height="400" width="200" title = "Login">   <img src="https://user-images.githubusercontent.com/15268903/80800022-32ca6100-8bca-11ea-8f10-c1b257a77b7f.gif" height="400" width="200" title = "Update Profile">

----------```Add Task```--------------------```Update Task```----------------```Delete Task```--------

<img src="https://user-images.githubusercontent.com/15268903/80799992-247c4500-8bca-11ea-83f4-99ac9c30e6ff.gif" height="400" width="200" title = "Add Task">   <img src="https://user-images.githubusercontent.com/15268903/80800007-29d98f80-8bca-11ea-9f48-fb94d7a8d724.gif" height="400" width="200" title = "Update Task">   <img src="https://user-images.githubusercontent.com/15268903/80800012-2c3be980-8bca-11ea-8040-203829cdaa8a.gif" height="400" width="200" title = "Delete Task">

## Architecture Used

<img src="https://user-images.githubusercontent.com/15268903/80802176-70ca8380-8bd0-11ea-8a4d-770895f2b9ee.png" alt="https://developer.android.com/jetpack/docs/guide" style="width:200;height:200">

## Features

* Add - Update - Delete Task
* 3 Status for Tasks - 1. Pending 2. Started 3. Completed
* Update Profile Info and Profile Picture
* Offline Data Caching for Tasks.
* Online Data Syncing for all data.

### Built With

* Android Studio 3.6.3. The latest version can be downloaded from [here](https://developer.android.com/studio/)
* Build gradle 3.6.3
* Android SDK 29
* Kotlin Version 1.3.71

### Backend

* Laravel [Source](https://github.com/ShakilAhmedShaj/laravel_todo_api)

### Install the apk

<a href="http://shajt3ch.com/EasyTask.apk"><img alt="Get it on Google Play" src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" width="185" height="70"/></a>

Go to the following [link](http://shajt3ch.com/EasyTask.apk) to download the app.

### Directory Structure

The following is a high level overview of relevant files and folders.

```
└───Easy Task
    └───app
        └───src
            └───main
                └───java
                    └───com
                        └───shajt3ch
                            └───easytask
                                ├───model
                                │   ├───local
                                │   │   │   AppPreferences.kt
                                │   │   │
                                │   │   ├───dao
                                │   │   │       TaskDao.kt
                                │   │   │
                                │   │   ├───db
                                │   │   │       AppDatabase.kt
                                │   │   │
                                │   │   └───entity
                                │   │           TaskEntity.kt
                                │   │
                                │   ├───remote
                                │   │   │   EndPoints.kt
                                │   │   │   Networking.kt
                                │   │   │   NetworkService.kt
                                │   │   │
                                │   │   ├───request
                                │   │   │   ├───auth
                                │   │   │   │       LoginRequest.kt
                                │   │   │   │       RegisterRequest.kt
                                │   │   │   │
                                │   │   │   └───task
                                │   │   │           AddTaskRequest.kt
                                │   │   │           DeleteTaskRequest.kt
                                │   │   │           EditTaskRequest.kt
                                │   │   │
                                │   │   └───response
                                │   │       ├───auth
                                │   │       │       LoginResponse.kt
                                │   │       │       RegisterResponse.kt
                                │   │       │       ValidateResponse.kt
                                │   │       │
                                │   │       ├───profile
                                │   │       │       EditProfileResponse.kt
                                │   │       │       UserProfileResponse.kt
                                │   │       │
                                │   │       └───todo
                                │   │               AddTaskResponse.kt
                                │   │               EditTaskResponse.kt
                                │   │               TaskResponse.kt
                                │   │
                                │   └───repository
                                │           AddTaskRepository.kt
                                │           DeleteTaskRepository.kt
                                │           EditTaskRepository.kt
                                │           LoginRepository.kt
                                │           RegisterRepository.kt
                                │           TaskRepository.kt
                                │           UserProfileRepository.kt
                                │           ValidateTokenRepository.kt
                                │
                                ├───util
                                │   │   GeneralHelper.kt
                                │   │   Validator.kt
                                │   │
                                │   └───network
                                │           NetworkError.kt
                                │           NetworkHelper.kt
                                │
                                ├───view
                                │   ├───adaptor
                                │   │       TaskAdapter.kt
                                │   │       TaskCallBack.kt
                                │   │
                                │   └───ui
                                │       ├───auth
                                │       │       LoginActivity.kt
                                │       │       SignUpActivity.kt
                                │       │
                                │       ├───home
                                │       │       HomeFragment.kt
                                │       │
                                │       ├───main
                                │       │       MainActivity.kt
                                │       │
                                │       ├───profile
                                │       │   │   ProfileFragment.kt
                                │       │   │
                                │       │   └───edit
                                │       │           EditProfileFragment.kt
                                │       │
                                │       ├───splash
                                │       │       SplashActivity.kt
                                │       │
                                │       └───task
                                │           │   TaskFragment.kt
                                │           │
                                │           ├───detail
                                │           │       TaskDetailFragment.kt
                                │           │
                                │           └───edit
                                │                   EditTaskFragment.kt
                                │
                                └───viewmodel
                                    ├───auth
                                    │       LoginViewModel.kt
                                    │       SignUpViewModel.kt
                                    │
                                    ├───edit
                                    │       EditProfileViewModel.kt
                                    │
                                    ├───home
                                    │       HomeViewModel.kt
                                    │
                                    ├───profile
                                    │       ProfileViewModel.kt
                                    │
                                    ├───splash
                                    │       SplashViewModel.kt
                                    │
                                    └───task
                                            EditTaskViewModel.kt
                                            TaskDetailViewModel.kt
                                            TaskViewModel.kt
```
## Libraries Used
* [Retrofit](https://square.github.io/retrofit/)
* [Glide](https://github.com/bumptech/glide)
* [Image Picker](https://github.com/Dhaval2404/ImagePicker)
* [Lottie](https://github.com/airbnb/lottie-android)
* [MaterialSpinner](https://github.com/jaredrummler/MaterialSpinner)
* [Anko](https://github.com/Kotlin/anko)
* [android-gif-drawable](https://github.com/koral--/android-gif-drawable)

## License
```
MIT License

Copyright (c) 2020 Easy Task

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
```

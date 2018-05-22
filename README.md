# FabFit CodeZilla hackathon 2018

## Installation

Download the [apk](https://github.com/codezilla2018/FabFit/blob/master/demo%20apk/app.apk) 
## Screenshots
<p float="left">
  <img src="https://github.com/kekayan/FabFit/raw/master/screenshots/steps.png" width="250" />
  <img src="https://github.com/kekayan/FabFit/raw/master/screenshots/tweets.png" width="250" /> 
  <img src="https://github.com/kekayan/FabFit/raw/master/screenshots/histroy.png" width="250" />
</p>
<p float="left">
  <img src="https://github.com/kekayan/FabFit/raw/master/screenshots/editprofile.png" width="250" />
  <img src="https://github.com/kekayan/FabFit/raw/master/screenshots/profile.png" width="250" /> 
  <img src="https://github.com/kekayan/FabFit/raw/master/screenshots/settings.png" width="250" />
</p>

* some screen may be not the same due to version downgrading of the desgin support library
## About 
Simple 	**FabFit** app using the 	**step-sensor**  which introuduced in KitKat version of android for 	**minimal** **battery** consumption which runs in hardware level.

more info about step counter sensor: 

[![youtube video](https://img.youtube.com/vi/yv9jskPvLUc/mqdefault.jpg)](http://www.youtube.com/watch?v=yv9jskPvLUc)
[link](https://www.youtube.com/watch?v=yv9jskPvLUc)
>We can implement  own step-detection algorithm or  Sensor Fusion and other Algorithms using the acceleration data from Accelerometer sensor.But the battery use is extremely high as you have to keep the system awake.Since most the devices now have sensor for step dector better and recommended to use to save battery which crucial in mobile devices.
## Accelometer based app
bagillevi devloped one [follow link](https://github.com/bagilevi/android-pedometer)

##Inspiration
Github user name [j4vlin](https://github.com/j4velin) created a app based on sensor counter method

So FabFit uses the hardware step detection sensor of the Samsung S7, which is already running even when not using any stepcounting app. Therefore the app does not drain any additional battery. Unlike other apps, this app does not track your movement or your location so it doesn't need to turn on your GPS sensor aslo.

## Usage

<p><img src="https://github.com/kekayan/FabFit/raw/master/screenshots/login.png" width="250" /></p>
  * Sign in with your Google account!
beacuase currently only google sign in enabled

* setup your profile first by navigate to profile then edit info.
* In Profile Editinfo Section
 * Set your Weight Default is 52Kg

 * Set your Height Default is 173cm
 * You can update profile photo and name also.by taping photo in editinfo section

* Set your goal! in settings, Default is 10000 It is recommended that you take 10000 steps a day.

* This app will keep track of how much and how far you walked, all day, every day. History is presented neatly.
* It retirve tweets from twitter for hashtag "fitness"

## FAQ
* calories calculation formula used from [this link](https://fitness.stackexchange.com/a/25500)

## Helps
* [Firebase tuts](https://www.firebase.com/docs/android/guide/saving-data.html)

* It uses the 
   [EazeGraphLibrary](https://github.com/blackfizz/EazeGraph)  pie and bar charts

   [ColorPickerPreference](https://github.com/attenzione/android-ColorPickerPreference)

   [CircularImageVie](https://github.com/lopspower/CircularImageView).

   Twitter Kit for Android 

   [Lottie for Android](https://github.com/airbnb/lottie-android) for the animations of walking person and loading 

   Also uses Glide,Picasso,Firebase.


### Declaring sensors requirement in the manifest
If your app requires support for the step counter you will have to declare it in the app's AndroidManifest.xml file:
```xml
<uses-feature android:name="android.hardware.sensor.stepcounter " android:required="true"/>

## first Contributers
* codezilla 
* notkekayan@gmail.com Kekayan kekayan IA9 

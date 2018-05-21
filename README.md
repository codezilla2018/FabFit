# FabFit
notkekayan@gmail.com Kekayan kekayan IA9
***

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



Simple 	**FabFit** app using the 	**step-sensor**  which introuduced in KitKat version of android for 	**minimal** **battery** consumption which runs in hardware level.

more info : 

[![youtube video](https://img.youtube.com/vi/yv9jskPvLUc/mqdefault.jpg)](http://www.youtube.com/watch?v=yv9jskPvLUc)
[link](https://www.youtube.com/watch?v=yv9jskPvLUc)
>We can implement  own step-detection algorithm or  Sensor Fusion and other Algorithms using the acceleration data from Accelerometer sensor.But the battery use is extremely high as you have to keep the system awake.Since most the devices now have sensor for step dector better and recommended to use to save battery which crucial in mobile devices.


So FabFit uses the hardware step detection sensor of the Samsung S7, which is already running even when not using any stepcounting app. Therefore the app does not drain any additional battery. Unlike other apps, this app does not track your movement or your location so it doesn't need to turn on your GPS sensor aslo.



<p><img src="https://github.com/kekayan/FabFit/raw/master/screenshots/login.png" width="250" /></p>
Sign in with your Google account!

currently only google sign in enabled

Set your goal! Default is 10000 It is recommended that you take 10000 steps a day.



In Profile Editinfo Section



Set your Weight Default is 52Kg

Set your Height Default is 173cm

for tuning the accuracy for you.

You can update profile photo and name also


This app will keep track of how much and how far you walked, all day, every day. History is presented neatly.
It retirve tweets from twitter for hashtag "fitness"


calories calculation formula used from [this link](https://fitness.stackexchange.com/a/25500)


It uses the 
[EazeGraphLibrary](https://github.com/blackfizz/EazeGraph)  pie and bar charts

[ColorPickerPreference](https://github.com/attenzione/android-ColorPickerPreference)

[CircularImageVie](https://github.com/lopspower/CircularImageView).

Twitter Kit for Android 

[Lottie for Android](https://github.com/airbnb/lottie-android) for the animations of walking person and loading 

Also uses Glide,Picasso,Firebase,Support desgin latest version


Declaring sensors requirement in the manifest
If your app requires support for the step counter you will have to declare it in the app's AndroidManifest.xml file:
```xml
<uses-feature android:name="android.hardware.sensor.stepcounter " android:required="true"/>```

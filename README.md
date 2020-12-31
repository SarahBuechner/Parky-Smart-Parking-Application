# Parky: a Smart Parking Application

Project by Sarah Bücher <br>
As part of Master thesis: *Parky, an Attempt to make Cities Smarter* <br>
UNIL, January 2021

## Abstract
Smart Cities aim at providing more sustainable lives to its citizens by using ICT technologies. One of the main burdens of urban areas are parking availabilities. The smart parking application Parky was developed to provide the possibility to provide visibility about available parking lots. Parking lot provider can declare their lots as available and rent them. Drivers looking for a parking lot are able to choose suitable parking spaces and book them.   

## Technologies
### Programming languages
- kotlin 1.3.72
- Java 7

### Program for development
Android Studio 4.0
Android SKD 30.0.2 (targer version: 30, min. version: 15)

### Infrastructure
The solution uses the Matchmore service. Therefore, it is necessary to create a Matchmore account: <br>
https://matchmore.io/account/register/

### Dependencies: 
For the project it is necessary to install libraries and frameworks for kotlin programming language, kotlin extensions, usage of certain APIs, UI, Matchmore publish/subscribe model, to check permissions, for annotations and testing. Please find here the detail below: 

- for Implementation: 
```
fileTree(dir: "libs", include: ["*.jar"])
org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version
androidx.core:core-ktx:1.3.1
androidx.appcompat:appcompat:1.2.0
androidx.constraintlayout:constraintlayout:2.0.1
com.github.matchmore.alps-android-sdk:sdk:0.7.2
com.github.matchmore.alps-android-sdk:rx:0.7.2
gun0912.ted:tedpermission:2.2.3
androidx.annotation:annotation:1.1.0
```
- Testing Frameworks:
```
junit:junit:4.12
androidx.test.ext:junit:1.1.2
androidx.test.espresso:espresso-core:3.3.0
```
## Set up
To run this project the necessary technologies need to be downloaded and installed (see above). <br>
Download the code to a local machine. <br>
The project needs then to be run in Android studio. <br>

For real testing you can run the app on: <br>
- emulated phones in Android Studio
- real Android devices <br>
/!\ To test all functionalities of the application it is recommended to use two devices for testing.

## Scope of Functionalities
- For parking lot provider: insert parking lot characteristics
- For driver (looking for a parking lot): 
  - insert characteristics of desired parking lot
  - see list of all filtered available parking lots
  - see details of chosen parking lot

## Project Status
The aim of this project was to demonstrate the implementability of a parking reservation application with geomatching. The Application should be seen as a prototype and not a final application. 







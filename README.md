# demomod

demomod is my attempt at making mod that new modders can customize to quickly and easily get started with modding.

The mod currently includes the following:

* A custom biome with custom ore generation
* A custom bush that grows in the custom biome which produces seeds for a food crop
* A custom ingot dropped by the ore
* A custom sword, bow, pickaxe, axe, hoe, and shovel
* A custom set of armor
* A custom hostile mob entity
* A custom tameable mob entity

Preliminary instructions for getting the development environment set up are here:

https://github.com/worldwidewoogie/demomod/blob/master/INSTALL.txt

Once you have a working mod, you can build it with the following command on OSX/Linux:

./gradlew build

or Windows:

gradlew build

demomod has been built and tested on both single player and multi player server.

All the attributes of the mod can be changed by editing the following:

* https://github.com/worldwidewoogie/demomod/blob/master/src/main/java/net/woogie/demomod/Config.java
* https://github.com/worldwidewoogie/demomod/blob/master/src/main/resources/assets/demomod/lang/en_US.lang
* the texture files under https://github.com/worldwidewoogie/demomod/tree/master/src/main/resources/assets/demomod/textures

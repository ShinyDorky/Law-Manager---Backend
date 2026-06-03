# MOS Law Generator - backend
A Spring Boot application functioning as a backend for my
[Law Manager](https://github.com/ShinyDorky/law_generator_backend) - a tool designed in order to help me with
creating and managing laws in my CK3 mod.

## How it works
The application creates a REST API, which allows one to create and manage laws used in my CK3 mod.
Said laws are represented by entities in a locally created H2 database. The database is created using Hibernate ORM.

### Defined entities
- Law Types - broad categories of Law Groups, defined by what area of state they pertain to (army, economy, etc)
  - signature - how the law type will be represented in the mod script
  - name - how the law type will be represented in the mod UI
- Law Groups - groups of mutually exclusive law options (permissions for private armies, levy obligations, etc)
  - signature - how the law group will be represented in the mod script
  - name - how the law group will be represented in the mod UI
  - desc - how the law group will be described (NOT visible to the player)
- Law Options - individual laws
  - signature - how the law option will be represented in the mod script
  - name - how the law option will be represented in the mod UI
  - desc - how the law option will be described (visible to the player)
  - canKeep - conditions that a character needs to satisfy in order to keep this law
  - canPass - conditions that a character needs to satisfy in order to either pass this law or have this law be proposed
  - effects - modifiers that will be applied to everyone who has this law
  - placeInOrder - which place in the law group does this law have, laws can be passed only when having a law that
  either has the same place or is adjacent to the considered law
  - passCost - resources that will be consumed on passing of this law
  - onPass - effects that will be called the moment this law is passed
  - Law opinion multipliers - how much the followers of particular ideas will like or dislike this law

## Usage
Use ``mvn clean package`` to generate a .jar file. You can run it by opening a cmd in the file's location and running 
the following command:

``java -jar .\[FILE_NAME].jar``

Alternatively you can download both files in a package (TODO: INSERT LINK) and use an included .bat file.

## Requirements
As long as you have Java installed, it should run.
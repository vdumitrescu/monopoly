Game Synchronisation in Java
============================

We started with the Ping Pong game (in the Coursera MOOC) having two threads print Ping and Pong alternately by using two semaphores.

Next, I created a [Chess Clock](http://en.wikipedia.org/wiki/Chess_clock) that uses a `ReentrantLock` and two `Condition` objects for the same game.

This project expands the idea of the Chess Clock to N players so that they can play a game where they take turns in a Round-Robin fashion.

This example has up to 10 players throwing dice and going around the Monopoly board. No fancy stuff, just going around.

The output looks like this:

    Wheelbarrow: It's my 1st turn
    Wheelbarrow: I'm currently on Go
    Wheelbarrow: I've rolled: 1-6
    Wheelbarrow: I've landed on Chance (1)

    Battleship: It's my 1st turn
    Battleship: I'm currently on Go
    Battleship: I've rolled: 2-6
    Battleship: I've landed on Vermont Avenue

    Racecar: It's my 1st turn
    Racecar: I'm currently on Go
    Racecar: I've rolled: 2-2
    Racecar: I've landed on Income Tax

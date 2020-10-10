@echo off

@javac Die.java
@javac DiceSet.java
@javac DieAndDiceSetTestHarness.java
@javac HighRoll.java

@del testresults.txt
@java Die > testresults.txt
@java DiceSet >> testresults.txt
@java DieAndDiceSetTestHarness >> testresults.txt
@java HighRoll 5 6 < numInput1.txt >> testresults.txt
@java HighRoll 11 17 < numInput2.txt >> testresults.txt
@java HighRoll 29 4 < numInput3.txt >> testresults.txt


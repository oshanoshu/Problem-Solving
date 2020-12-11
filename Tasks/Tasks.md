**There is a system that runs jobs submitted by users. You have to write some code that will analyze logs to figure out how much processing time each job took.
A job is started manually with a root task, then that task can create other tasks ("sub-tasks"). Those sub-tasks can create more sub-sub-tasks, and so on.**
The log file you have consists of one line per task. It contains:

1. the task's ID
2. the ID of the task that created it (or null if it was a root task)
3. the amount of processing time the job took.

Here is an example log file:
```
E B 400
G null 10
B A 100
F B 600
D A 20
A null 20
C A 250
H G 20
```
Write some code that inputs a log file, and then outputs the total amount of time taken for each job: that is, the total time taken by all of the tasks within a job.

Example, where we have two jobs, the one whose root task is A and the one whose root task is G:
Explanation of log file:
a) Task A created task B, which created tasks E and F. Task a also created tasks C and D.
b) Task G created task H

This should output:
```
A 1390
G 30
```

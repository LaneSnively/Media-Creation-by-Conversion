###### Team Status Update Post #4

A written status summary document [30pts]

* Markdown document should include requirements listed in below in clearly identifiable subsections

* Uploaded to git repo folder /status/status​#​.md ; where # = {1,2,3,4}

* Content

1. Recap of what was planned for the last 3 weeks [5pt]

## Most of the difficult features were completed. We mostly needed to fix bugs. 

2. Tasks Completed What was done during the last 3 weeks (by whom) [5 pt]

* Description of tasks completed

## text input:
-[x] convert canvas to text button

-[x] clear text button

-[x] current canvas viewer

## drawing input:
-[x] clear canvas button

-[x] automatically restore previous canvas

-[x] bristle settings selection indicator

-[x] text brush viewer

-[x] full screen DrawingView button

-[x] double number of characters mapped to colors

-[x] scale and center loaded image canvas

-[x] scale 'text brush'

-[x] paste 'text brush' at (0,0) button

## Google Play Store:
-[x] publish app version 1 through 11

* Your choice of quantifiable metric(s)

(e.g., hours, lines of code, meeting count, doc/test coverage %,...)

## We only care about tasks completed. The number of lines of code is arbitrary. 

3. Successes [5pt]

* What are your accomplishments?

## The app was successfully published on Google Play and everyone we know has downloaded it. 

* What solutions were successful?

## I gained access to GPT4 which is more useful for coding than ChatGPT. GPT4 was, by far, the most useful tool for language syntax help. GPT4 helped me assign colors to keyboard characters. It helped optimize my code for faster execution speed. It was never capable of producing something I could copy and paste directly, but still vastly more useful than Google. 

* Were there other things that you tried that did not work and why?

## There's a problem with a TransactionTooLargeError when the load image button is pressed after convert canvas to text button has been pressed. Whenever the app goes in the background, when switching apps or using a file picker, it crashes. I tried to fix it. The problem is not an issue so I decided to ignore it. 

4. Roadblocks/Challenges [5pts]

* Describe the challenges

## I couldn't figure out how to restore the state of the DrawingView after fragment navigation.

* Describe how you overcame them

## I learned I needed to call the restoreDrawing method in a mechanism that forces the call to wait 10 ms before executing because the binding isn't fully inflated before that. 

* What challenges are still left?

## We have to make and present the project at the University of Wyoming Undergraduate Research and Inquiry Day. 

* What do you need help with? How can your mentor help?

## We need help making the presentation as professional as possible. Our mentors are scheduled to meet with us to review or presentation draft and suggest improvements. 

5. Changes/Deviation from Plan ​(if applicable - if not, say so!) [0 pts]

## So far, everything has been accomplished according to plan. 

6. Details Description of Goals/ Plan for ​Next 3 Weeks [5pts]

* Note in final summary discuss where your project could go from here)

## We don't have much time left before presentation day. We will be spending all of our time working on the presentation at this point. 

7. Confidence on completion from each team member + team average [5 pts]

Scale of 1-5; 1 = not-confident; 3 = toss-up; 5 = confident

## Lane: 5
## Quinton: 5
## Average: (5+5)/2 = 5
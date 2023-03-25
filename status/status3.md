###### Team Status Update Post #3

A written status summary document [30pts]

* Markdown document should include requirements listed in below in clearly identifiable subsections

* Uploaded to git repo folder /status/status​#​.md ; where # = {1,2,3,4}

* Content

1. Recap of what was planned for the last 3 weeks [5pt]

## The majority of the features for the app needed to be developed over this time period. 

2. Tasks Completed What was done during the last 3 weeks (by whom) [5 pt]

* Description of tasks completed

## text input:
-[x] text input box
-[x] save text input button
-[x] load text input button

## drawing input:
-[x] save image button
-[x] save images for gif button
-[x] limited backspace key functionality
-[x] restore previous drawing button
-[x] load image as background button
-[x] square bristle button
-[x] circle bristle button
-[x] character bristle button
-[x] stroke bristle button
-[x] fill bristle button
-[x] bristle size number picker

## format layout so that it works in landscape/portrait orientation and different screen sizes: phone, tablet

* Your choice of quantifiable metric(s)

(e.g., hours, lines of code, meeting count, doc/test coverage %,...)

## We only care about tasks completed. The number of lines of code is arbitrary. 

3. Successes [5pt]

* What are your accomplishments?

## Text input works so well that we were able to convert an image to its ascii text representation which we used to draw recognizable icons such as the Pringles logo, Denver Broncos logo, and a duck. 

* What solutions were successful?

## ChatGPT is often more useful than stackoverflow or language documentation in finding basic syntax for code we needed. Tools we use never give the exact code we need but chatGPT proved to be the best tool to find help when we needed it. 

* Were there other things that you tried that did not work and why?

## Trying to find correct syntax for code we needed was nearly impossible on stackoverflow, language documentation, youtube videos. Most of the example code for android development is in kotlin, which is useless because our app is coded in java. Most of the examples we found online were close to what we needed but never worked because we use fragment bindings in each fragment, not findviewbyID() methods. 

4. Roadblocks/Challenges [5pts]

* Describe the challenges

## Implementing the drawing backspace feature was a big challenge. 

## Restoring the state of fragments after navigating between fragments was slightly challenging. 

## Resizing images when loading them as a canvas was slightly challenging. 

## Choosing which images to save in order to make a GIF of the drawing process was a big challenge. 

## Choosing the layout for each fragment such that the app works in landscape mode along with portrait mode and on different screen sizes was very challenging. 

* Describe how you overcame them

## The first implementation of the drawing backspace feature used a stack to store edit history. There was a problem when the stack became too large, the app would crash. We limited the size of the stack. The stack operations took O(n) time. We replaced the stack with a list and the operations took O(1) time. The drawing backspace feature works elegantly without slowing down the app now. 

## We tried passing information directly between fragments which did not work. We discovered in the android documentation that doing that is prohibited. We chose to use a viewmodel class to store mutable data and live data. We wrote methods that store and retrieve fragment state information in the viewmodel which works perfectly. 

## The logic for resizing images to match the drawingview took a while to figure out but our solution works perfectly. 

## It didn't make sense to save every edit when the edit history became too large. Android GIF making software allows a maximum of 50 images to use to make a GIF so we chose to save up to 50 images of the edit history. 

## Front-end development of the app is not difficult technically but very challenging to make the app visually appealing. This was mostly a trial and error process of changing the layout and testing whether it looked good in landscape/portrait mode and on different screen sizes until it was good enough. 

* What challenges are still left?

## We need to publish the app on the Google play store. We need to make a privacy policy which we have no idea how to do or what that entails. 

* What do you need help with? How can your mentor help?

## We will need help with publishing the app on the play store. 

5. Changes/Deviation from Plan ​(if applicable - if not, say so!) [0 pts]

## So far, everything has been accomplished according to plan. 

6. Details Description of Goals/ Plan for ​Next 3 Weeks [5pts]

* Note in final summary discuss where your project could go from here)

## We don't have much time left before presentation day. We will mostly try to make the app as visually appealing as possible. 

7. Confidence on completion from each team member + team average [5 pts]

Scale of 1-5; 1 = not-confident; 3 = toss-up; 5 = confident

## Lane: 5
## Quinton: 5
## Average: (5+5)/2 = 5
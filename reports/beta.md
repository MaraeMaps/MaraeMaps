# MaraeMaps Beta

By Hugo Phibbs, Kavan Chay, Lucy Sladden and Harry Pirrit

## Improvements from alpha

- Map clustering has been implemented to give a cleaner look on the map fragment.
- A custom icon has been made for the launcher logo and wiki icon in the nav bar.
- Changing to dark mode on the mobile device should not prevent the user from reading
  any of the Wiki entries now- previously was unreadable due to colours.
- Clicking on the Wiki entries now generates a screen displaying information on the 
  specific marae (iwi, hapu, address).

## User testing

- User testing has been discussed with each member looking for people to help
  test out the build. A copy of the questionnaire will be distributed to each user tester.

## Addressing CI feedback

- 

### Code Coverage

- We have just 2 domain classes, that both have simple methods that are easy to test. Since these
  two classes are relatively short, there is not that much to test. Hence our code coverage
  is quite low at just 9%.
- We could increase our code coverage by adding automated UI testing, however we instead decided 
  against this since this is a significant investment of time that could be used more effectively 
  elsewhere.
- Hence our code coverage stands at just 9%.

### Code Quality

- We decided to ignore some distracting issues from the code quality reports, such as line index 
  and private method documentation. 
- Instead we addressed other issues, such as adding documentation where required.
- Thus, we were able to increase code quality from a B to A rating.

### GitHub Pages

- Our Github pages deployment broke just before submitting this assignment. The error message
  provided in the actions make us believe that this may have to do with code that we have no
  control over.

## Profiling our app

- Android Studio has a profiler that was used to profile CPU, memory and energy usage of app during use.
- CPU usage spikes to 50-60% when the Map fragment is called, which is due to the loading of markers and placement.
  CPU usage also increases to 20-30% during the use of the Wiki search function.
  Usage of CPU is in the 30-40% range when zooming in and out on the map. 
- Memory usage rose to about 240MB during usage but never rose over that during use.
- Energy usage was consistently "light" during use, even when generating the Map.

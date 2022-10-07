# COSC345 Final Report

## Addressing User Feedback

### Improving the Maps View

- Users commonly reported that the marae maps view had an overwhelming amount of content to view.
  This made it hard to focus on any one marker.
- To solve this, we implemented a clustering feature that groups marae markers together.
- Clustering also improves the performance of the app, as previously devices were struggling to load
  the 1000 or so marae markers at once.

### Aesthetics

- In the final release, we sought out to create a unique feel and brand for our app.
- This involved tweaking colours and fonts, adding formatting and making generally more
  professional.
- User testing of the new colour theme suggested that the new changes were slick and modern looking.

### General fixes

- Users reported that empty marae detail values were confusing. So we added placeholder values that
  lets users know if a certain marae detail could be found. E.g. if the Iwi for a marae could not be
  found, it will display ' [iwi] unavailable' 
- Fixed language switching, now the settings button actually switches the language


## New Features

### Creating a marae information screen

- We created a information screen, providing users a more detailed and rich view of a chosen Marae.
- Includes more factual information than the Maps page, and adds inline Street and Map Views for a
  particular Marae.
- A user can then click on the map marker of the Marae, with a button popping up for directions via
  Google Maps 

### Adding credits
- We thought it was appropriate to credit the marae data set, app users can now find a link to this in the settings screen.
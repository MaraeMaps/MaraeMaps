# IwiMaps
![Build](https://github.com/MaraeMaps/MaraeMaps/actions/workflows/android.yml/badge.svg)
[![Documentation](https://github.com/MaraeMaps/MaraeMaps/actions/workflows/documentation.yml/badge.svg)](https://maraemaps.github.io/MaraeMaps/)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/341d2fa04a634b609d030c9517c617c9)](https://www.codacy.com/gh/MaraeMaps/MaraeMaps/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=MaraeMaps/MaraeMaps&amp;utm_campaign=Badge_Grade)
[![Coverage](https://codecov.io/gh/MaraeMaps/MaraeMaps/branch/master/graph/badge.svg?token=FLVYXZUJJ4)](https://codecov.io/gh/MaraeMaps/MaraeMaps)
- By Harry Pirit, Kavan Chay, Lucy Sladden and Hugo Phibbs

## Instructions
- Go to apiKey-instructions.md first
- **No pushing directly onto master, always new branch, then pull request. This is so actions can do it's magic!**

## Questionaire
- Please visit [this file](https://github.com/MaraeMaps/MaraeMaps/blob/master/questionaire.md) to view our user testing questionaire


```
// Set the lat/long coordinates for the marker.
double lat = 51.5009;
double lng = -0.122;

// Set the title and snippet strings.
String title = "This is the title";
String snippet = "and this is the snippet.";

// Create a cluster item for the marker and set the title and snippet using the constructor.
MyItem infoWindowItem = new MyItem(lat, lng, title, snippet);

// Add the cluster item (marker) to the cluster manager.
clusterManager.addItem(infoWindowItem);
```

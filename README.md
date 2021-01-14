# FetchRewardsCodingAssignment
 
This Android application uses MVVM architecture to fetch service from the network and populates the UI. I have used 25 as the minimum API version and the        target version is 30. So it should ideally run on most of the modern devices.
 
I have used a basic UI shown below which has a splashscreen, a grid screen that groups the items based on list id. Upon clicking on one of the grid items,
a screen shows the item id and item name corresponding to the list id. I tried to stick with the color palette of Fetch Rewards.
 
I used Retrofit2, LiveData, architecture components, custome views, GSON, View Model for building this application. I also wrote few unit test cases to      validate the sort and the filter logic under the test directory. I tried to code it in the most professional way possible by adding comments, using string resources and the naming conventions. I also ran lint check against the entire project and there are very minimal warnings which can be explained. I have used Java 8 as the programming laguage to code this application. Tehe application files are also separated logically according to the core logic they perform. This application requires only internet permission.
 
 Here is a sample flow.
 
 Splash Screen that runs for 1 second
 <img src="https://github.com/avinashpatnaik/FetchRewardsCodingAssignment/blob/main/splash_screen.png"  align="middle"/> 
 
 List ID Screen where the data is fetched and grouped by list id
 <img src="https://github.com/avinashpatnaik/FetchRewardsCodingAssignment/blob/main/list_screen.png"  align="middle"/> 
  
 Items Screen where the item id's and the item name's corresponding to the List id are shown
 <img src="https://github.com/avinashpatnaik/FetchRewardsCodingAssignment/blob/main/items_screen.png"  align="middle"/> 


 


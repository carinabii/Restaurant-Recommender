# My Personal Project

## Purpose and Usage
This project is a _**restaurant recommendation application**_ that sorts restaurants based on the type of cuisine, price 
and ratings of the restaurant. It will allow any user to select and save the restaurant to into a list of 
recommendations for the future. As well, once selecting a specific restaurant, details will be provided such as the 
address, price and menu. This application is suitable for user's of any age, yet it is targeted towards individual's 
who wish to thoroughly experience the ambiance as well as the flavours of a variety of restaurants. Personally, I have 
found that eating out and dining with friends to be a large part of my social life. More often than not found, I have 
found it difficult to find restaurants that suits my friends price lines, and that the people I interact with often 
have fairly different palate preferences. This project will allow for _**personalization**_ of a list of restaurants 
that is most suitable for the user's tastes.

## User Stories

A List of User Stories:
- As a user, I want to be able to view details of a selected restaurant.
- As a user, I want to be able to view the full list of all restaurants stored in the application.
- As a user, I want to be able to view suggested restaurants based on the type of cuisine.
- As a user, I want to be able to view suggested restaurants based on the price.
- As a user, I want to be able to add a selected restaurant into a list of recommendations.
- As a user, I want to be able to save my recommendations list to file.
- As a user, I want to be able to choose my saved to-do list from file when starting the program.

## Instructions for Grader

Instructions for Interacting with GUI:
1. first requirement (adding x to y): Choose either sort by price or sort by cuisine and select a price range or type of
   cuisine. A panel should pop up containing a dropdown of restaurants meeting the inputted criteria. Select one and 
   click "Add to Recommendations". If not already in recommendations, a message saying "SAVED TO RECOMMENDATIONS!" will 
   appear. Click "Back" and confirm that the restaurant is saved to recommendations through the "View Restaurants 
   Saved to Recommendations" button.
2. second requirement (sorting x): By choosing either the sort by price or sort by cuisine options, you can choose a 
   price point or type of cuisine to organize the restaurants in the system by. The application filters out restaurants 
   with the given criteria.
3. visual component: By choosing either a type of cuisine or price range, a picture associated with the keyword should
   pop up with the dropdown menu.
4. saving to file: After adding one or several restaurants to recommendations, go back to the initial page and click 
   "Save Restaurants to File". The added restaurants should now appear in ./data/recommendations.txt.
5. loading the file: After saving, close the application window. Try rerunning the program. If you click "View 
   Restaurants Saved to Recommendtions", the restaurants saved previously should still be in the recommended list.
   

## Phase 4: Task 2

I completed the task where I had to test and design a class that is robust. Exceptions were added to the RestaurantList 
class to check whether inputted price and cuisine types are valid. These are then tested in the RestaurantListTest 
class. InvalidCuisineException is thrown in the method isValidCuisine() and is later caught in the sortByCuisine() 
method, both in the RestaurantList class. Similarly, InvalidPriceException is also thrown by the isValidPrice() method
and then caught in the sortByPrice() method. The exceptions are tested in the RestaurantListTest class by 
isValidCuisineTest() and isValidPriceTest().

## Phase 4: Task 3

There was poor cohesion in the Gui class. The first change I made was based on the similarity I noticed between 
different actionListeners for my buttons. For instance, ChinSorter, JapaSorter, ThaiSorter and KorSorter essentially all
had the same functions except they inputted different parameters to the method organizeByCuisine().I refactored these
ActionListeners so that they shared one actionPerfomed() method and made them all take in the same ActionListener, which
I named CuisineSorter. I did the same thing with PriceSorter and the various price buttons in the pricePanel. The second 
change I made was that I realized I had low cohesion in my Gui class; all the JButtons, JLabels and setup was all
being done in the same class. I extracted the instantiations and setup of these objects and put them in GenerateButtons, 
GenerateMessages and SetUpRestaurants classes respectively. Now, the Gui class is more cohesive, and I can navigate and
reference classes easily while understanding their functionality.
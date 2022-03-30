# Fitness Tracker

## an application that track daily calories intake 

**Project proposal:**
- **What will the application do?**
  The application is designed to help users realize their fitness goal by tracking daily intakes.(this app will not take
  daily activity into consideration for weight loss, it is simply calories tracker) The app will calculate 
  users daily calories according to users fitness goal and their physical information. Users will need to input 
  the food they have eaten for the day, and the app will provide track daily total sum of calories for users and 
  remaining calories for users to see explicitly.
  ###### 
- **Who will use it?**
  The application is aimed to help users who want to achieve certain body figures but have little knowledge in calories 
   of foods.
  ###### 
- **Why is this project of interest to you?**
  I have been an active gym goer for over five years, however, I still struggle a lot with tracking my intakes to 
  reach my fitness goal, even though there are similar application on the market, but they are either expensive or 
  hard to use, so I want to design my own.

###### 

## User Story:
- As a user, I want to input my weight, height,gender, age into my physical info
- As a user, I want to select weekly fitness goal 
- As a user, I want to know what is my daily maximum calories intake, and how many calories left
- As a user, I want to add a Food item to Daily Consumption
- As a user, I want to save my food list and physical info to file
- As a user, I want to be able to load my food list and physical info from file 

## Phase 4: task 2
Wed Mar 30 00:07:09 PDT 2022  
egg is added to today's food intake list  
Wed Mar 30 00:07:12 PDT 2022  
oats is added to today's food intake list  
Wed Mar 30 00:07:17 PDT 2022  
strawberry is added to today's food intake list  
Wed Mar 30 00:07:18 PDT 2022  
Your food items have been saved to today's food list  

##Phase 4: Task 3
- I designed three classes to track user physical info, daily consumption and food item, but some methods inside each 
class is not strictly about the class, I should spend more time on refactor methods 
- I did not use any interface or abstract class, however, there are many repetitive code inside the three class I 
designed I should extract them out and make it an abstract class to make the code cleaner
- Also, the classes under persistence package can also be extracted out an interface or abstract class
- For my ui package, the name for each class is a bit sus, it should be renamed. Also, some behavior are repetitive 
inside each class, and should be refactored
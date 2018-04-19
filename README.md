# BudgetBuddy
## Introduction:
This monthly budget application is intended for university student. It helps you to manage your personal finances. It allows you to create, view, edit, and delete the budget easily. 

## Main features:
When open this application, it shows four navigation buttons on the bottom. The first button is ‘Budget’, which shows you all the budget have been added. The second button is ‘Add budget’, which allows you to add a new budget. The third button is ‘Budget summary’, uses two pie charts to display the budget detail. The last button is ‘List of budgets’, which shows you only the expense or revenue. For each budget on the list, you can click it, and it will display all the detail of current budget, and it gives you the edit or delete budget options.
For the backend, I used firebase to store all the user’s data. 


## Details about implementation:
 For this project, I used one main father-activity, and several child-fragments. The reason for using fragments, is that it makes the whole UI design more flexible, and the logic easier to manage. When you click the navigation button, it only switches between different fragments. The top toolbar is also changed with different fragments. The toolbar code is from stackoverflow [1, 2].
### Insert data:
I followed the Model-View-Controller pattern. First, I designed a TransactionModel to store each budget information. Controller (activities) gets input from user, then I bound these data with model. After data validation, I stored all models to firebase. If the user wants to cancel ‘add budget’ action, he/she can simply click the cancel image on the left top corner.
### Select data:
On the home page, it shows a list of all budgets ordered by month. The sorting algorithm I used from stackoverflow [3]. I use a ListView to store all budget models. For expense, I used an orange-red background color; for revenue, I used a blue background color. When user clicks each budget item, it goes to another page to display the detail of this budget and give user options to change or delete the budget.
### Delete data:
I used a ‘delete’ image at the top right corner to represent the delete action. When user clicks this image, it pops out a dialog to ask user if he/she confirms to delete the budget.
### Edit data: 
After user inputs all the changed information, he/she clicks the ‘SAVE THE CHANGE’ button, and it will validate the data and store it to firebase if passed validation.
When user clicks the ‘summary’ button, it shows to pie charts of displaying expense/revenue. The pie chart is from GitHub library [4] and watched one tutorial ‘how to create pie chart’ [5].

## References:
[1] “Android toolbar center title and custom font,” Stack Overflow. [Online]. Available: https://stackoverflow.com/questions/26533510/android-toolbar-center-title-and-custom-font.
[2] “Cannot catch toolbar home button click event,” android - Cannot catch toolbar home button click event - Stack Overflow. [Online]. Available: https://stackoverflow.com/questions/26582075/cannot-catch-toolbar-home-button-click-event/31499604. 
[3] “Sort objects in ArrayList by date?,” java - Sort objects in ArrayList by date? - Stack Overflow. [Online]. Available: https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date.
[4] “PhilJay/MPAndroidChart,” GitHub, 24-Feb-2018. [Online]. Available: https://github.com/PhilJay/MPAndroidChart.
[5] sylsau, “[Android] Learn how to create a Pie Chart graph with MPAndroidChart,” YouTube, 07-May-2015. [Online]. Available: https://www.youtube.com/watch?v=VfLop_oLYU0.

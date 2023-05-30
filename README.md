
<h2> Android Todo Application </h2>
A simple app where a user can add, delete, update and read the todos. Each todo has a date associated with it which is set by default. It is the system date and is updated when the user appends the task/ creates it. The tasks on the home screen are ordered by Updated Dates, i.e. the latest tasks are displayed at the top of the list. The app also prompts the user regarding different operations they do like CRUD operation by a Toast message.

<br>
<h3>Architecture followed: MVVM Architectural Design Pattern</h3>

<img width="620" alt="mvvm architecture copy" src="https://user-images.githubusercontent.com/50170332/113397218-6a362480-93bc-11eb-8f63-eb6434057a10.png">
Fig: MVVM Implementation
<br>source: https://ebbi.github.io</br>




<h3>To demonstrate the CRUD operation in the app </h3>
<ul>
  <li>Room Database is used in the app. It is abstraction laye, functioning above SQL lite and <strong>app's main access point to the data<strong>.</li>
  <li>Live data is used to for updating the data as soon as the app is active or STARTED
  <li>Live data updates app components for only active lifecycle states</li>
  <li>The app supports create/update/delete/read operations i.e. CRUD operation</li>
  <li>The user can insert a task by clicking the fab icon (+) floating in the right bottom of the home page</li>
  <li>The user can delete a task by simply swiping the task towards left or right </li>
  <li>The user can delete all tasks by clicking the delete icon on the app bar </li>
  <li>The user can update the task by clicking on it</li>
  </ul>

<br>

![crud2](https://user-images.githubusercontent.com/50170332/113402356-9b1a5780-93c4-11eb-9af5-d5c981a3b222.gif)




<br>
<h3>To demonstrate handles Implicit Intents and handling states during screen rotation</h3>
<ul>
  <li>A combination of constraint, linear, frame layout, and the coding techniques to arrange the view items help develop the layout</li>
  <li>Recycler View reuses the views for new items that have scrolled on screen. In this case, the todo lists</li>
  <li>Recycler View is used for better performance and low power consumption</li>
  <li>In this application, each element in the list is treated as a view holder object. These objects are bound to data by RecyclerView.Adapter class </li>
  <li>Intents (Implicit Intents) - messaging objects are also used to lauch a diglog with choices for sharing texts. (TODO title and details)</li>
  </ul>
  <br>
  
  ![layout1](https://user-images.githubusercontent.com/50170332/113395409-85ebfb80-93b9-11eb-8b06-6c047cd36f18.gif)




<br>

<h3>To Demonstrate the app has error handling features</h3>

<li>The app prevents the user to leave the title field blank. Dialogue boxes (TOAST message) is handy for notifying the user. The app is not stopped, it only pauses until the current entry is received</li>

![errorhandling1](https://user-images.githubusercontent.com/50170332/113395488-a6b45100-93b9-11eb-8d3d-3638815334a2.gif)
<br>


<h3>To Demonstrate the complete Working of the App </h3>
<br>

![overall __app](https://user-images.githubusercontent.com/50170332/113402457-bf763400-93c4-11eb-953a-3650935d08cf.gif)

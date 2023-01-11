# 24 hours exam project - Grocery Delivery System

Programming-2, 3. semester KEA, fall 2022.

In this project you must design a simple full stack application for an upcoming company that wants to join the market for online delivery of groceries (think føtex.dk, mad.coop.dk, nemlig.com, etc.)

For this exercise, focus is on creating Products, creating Deliveries with associated Products and finally assigning Deliveries to a specific Van.

The backend must be implemented with Spring-Boot, JPA and a MySQL database. The frontend must be a separate HTML/JavaScript application.


It is expected that you work on the exercise alone, and don't share any kind of code/ideas with others which, will be considered exam cheating

The assignment must be submitted via two PRIVATE Github Repositories, see the section "details for the hand-in" for details.

The “product owner” has come up with this initial domain model which you may use as inspiration for your design
 
Van is greyed out, to signal that this is only relevant for the last task (task-4), so don't focus on this before/if you get to this part.
To simplify matters, all Products are registered by weight (thas is, beer, milk, müsli etc. are all registered by weight)

The product owner has come up with the following tasks, given in PRIORITISED order

Note: Even if not listed below, make sure to include, at least a few, test cases in your backend

##Task-1 The backend project and the database

Build a Spring Boot project with Database and JPA for the entities outlined above (initially leave out Van)

## Task-2 Products
Add the required code to implement a REST API + a corresponding HTML/CSS/JavaScriptS client that implement the  features listed below:

●   a user can Add a Product
●   a user can List all Products
●   a user can Find a Specific Product
For the next requirements, implement first the endpoints on backend, and only add frontend code, after having given task-3 a try.
●   a user can find a Product by name (REST only)
●   a user can Edit a product (REST only)
●   a user can Delete a Product (REST only)



## Task-3 Deliveries

Add the necessary changes to your back-/frontend to implement the following features
●   A user can create a new Delivery
●   A user can assign (many) ProductOrders to the Delivery
●   While doing the tasks above, users can continuously see the total price and weight for the delivery
●   A user can save a delivery
●   A user can see a list of all deliveries
●   A user can find and see a specific delivery, including the ProductOrders assigned to the delivery

Complete these features, one by one (backend/frontend)

## Task-4 Assign Delivery to a Van

Add the necessary changes to your back-/frontend to implement the following features
●	A user can assign a delivery to a van (assuming the total weight of the delivery does not exceed the max capacity of the Van).
●   A user can get a list of all deliveries assigned to a Van

Hint: Add a few hardcoded Vans to your database, and assume that the user somehow knows the id for the
Van in question.

Additional Tasks

Feel free to add any features you find relevant to showcase your skills, if not present in the above list. This could include additional features, (responsive) styling, security etc

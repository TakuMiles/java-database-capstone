Architecture summary

The system uses a layered architecture that cleanly separates presentation, logic, and data. Users interact through Thymeleaf dashboards or REST clients, with requests routed to controllers for validation and processing. Controllers delegate to the service layer, which applies business rules and coordinates workflows before accessing data via repositories in MySQL and MongoDB. Retrieved data is mapped into Java models and returned as dynamic web pages or JSON responses, completing the request–response cycle.

Numbered flow of data and control

1. User accesses AdminDashboard, DoctorDashboard and PatientDashboard.

2. The user’s request is routed to the appropriate Thymeleaf or REST controller based on the URL and HTTP method.

3. The controller validates the request, processes inputs, and delegates the main logic to the service layer.

4. The service layer applies business rules, manages workflows like appointment scheduling, and ensures proper coordination between components.

5. The service layer interacts with repositories to perform data access operations on MySQL and MongoDB databases.

6. Retrieved data is mapped into Java model classes—JPA entities for relational data and document models for MongoDB.

7. The processed models are returned to Thymeleaf views as dynamic HTML or to API clients as JSON responses, completing the request–response cycle.

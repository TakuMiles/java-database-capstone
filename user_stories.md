# User Story Template

**Title:**
_As a [user role], I want [feature/goal], so that [reason]._

**Acceptance Criteria:**
1. [Criteria 1]
2. [Criteria 2]
3. [Criteria 3]

**Priority:** [High/Medium/Low]
**Story Points:** [Estimated Effort in Points]
**Notes:**
- [Additional information or edge cases]

##Admin Stories
User Story 1: Admin Login

Title: Admin
As an admin, I want to log into the portal using my username and password so that I can securely access and manage the platform.

Acceptance Criteria:

The admin can enter a valid username and password to log in.

The system validates credentials against the database.

Successful login redirects the admin to the dashboard.

Invalid credentials display an appropriate error message.

Priority: High
Story Points: 3
Notes:

Authentication must follow secure password encryption standards.

User Story 2: Admin Logout

Title: Admin
As an admin, I want to log out of the portal so that I can ensure no unauthorized user accesses the system after me.

Acceptance Criteria:

The admin can click a “Logout” button to end the session.

The system invalidates the current session token.

The admin is redirected to the login page after logout.

Priority: High
Story Points: 2
Notes:

Session timeout should also trigger automatic logout for inactivity.

User Story 3: Add Doctor

Title: Admin
As an admin, I want to add new doctors to the portal so that they can access their dashboard and manage appointments.

Acceptance Criteria:

The admin can open the “Add Doctor” form and fill in required details.

The system validates mandatory fields (name, email, specialization, etc.).

A new doctor record is stored in the MySQL database.

The new doctor can log in using the provided credentials.

Priority: High
Story Points: 4
Notes:

Duplicate emails or usernames should not be allowed.

User Story 4: Delete Doctor

Title: Admin
As an admin, I want to delete a doctor’s profile so that I can remove inactive or outdated records from the system.

Acceptance Criteria:

The admin can select a doctor from the list and confirm deletion.

The system removes the doctor’s record from the database.

A confirmation message appears after successful deletion.

Deleted doctors lose access to the platform immediately.

Priority: Medium
Story Points: 3
Notes:

Consider adding soft delete functionality for audit purposes.

User Story 5: Generate Appointment Statistics

Title: Admin
As an admin, I want to run a stored procedure in MySQL to get monthly appointment statistics so that I can track platform usage and doctor activity.

Acceptance Criteria:

The admin can execute a predefined stored procedure from the MySQL CLI or admin panel.

The procedure returns the total number of appointments per month.

Results are displayed or exported in a readable format (e.g., CSV or table).

Priority: Medium
Story Points: 5
Notes:

Consider automating this process for dashboard reporting in future iterations.


##Patient Sories

User Story 1: View Doctors Without Logging In

Title: Patient
As a patient, I want to view a list of available doctors without logging in so that I can explore options before registering.

Acceptance Criteria:

The system displays a public list of doctors with names, specializations, and availability.

Patients can search or filter doctors by specialization.

No login or registration is required to access this information.

Priority: Medium
Story Points: 3
Notes:

The list should exclude sensitive contact details and personal data.

User Story 2: Patient Registration

Title: Patient
As a patient, I want to sign up using my email and password so that I can create an account to book appointments.

Acceptance Criteria:

The system provides a registration form that requires valid email and password.

Duplicate email registrations are not allowed.

Successful registration redirects to the login page or dashboard.

Priority: High
Story Points: 3
Notes:

Passwords should be encrypted and meet security complexity standards.

User Story 3: Patient Login

Title: Patient
As a patient, I want to log into the portal so that I can securely access and manage my bookings.

Acceptance Criteria:

The patient can log in using valid credentials.

The system authenticates and redirects to the patient dashboard.

Invalid credentials trigger a clear error message.

Priority: High
Story Points: 2
Notes:

Support “Remember Me” functionality and session timeout.

User Story 4: Book Appointment

Title: Patient
As a patient, I want to log in and book an hour-long appointment with a doctor so that I can schedule a consultation conveniently.

Acceptance Criteria:

The patient can select a doctor, choose a date and time, and confirm an hour-long slot.

The system checks the doctor’s availability before confirming the booking.

A confirmation message or email is sent after booking.

Appointment details are stored in the database.

Priority: High
Story Points: 5
Notes:

Prevent double booking or overlapping time slots.

User Story 5: View Upcoming Appointments

Title: Patient
As a patient, I want to view my upcoming appointments so that I can prepare for future consultations.

Acceptance Criteria:

The patient can view a list of all confirmed future appointments.

Each entry displays doctor name, date, time, and appointment status.

The list updates automatically if an appointment is canceled or rescheduled.

Priority: Medium
Story Points: 3
Notes:

Optionally include reminders or calendar integration for upcoming appointments.

##Doctor Stories

User Story 1: Doctor Login

Title: Doctor
As a doctor, I want to log into the portal so that I can securely access and manage my appointments.

Acceptance Criteria:

The doctor can log in using valid credentials (email and password).

The system authenticates and redirects to the doctor dashboard.

Invalid credentials display a clear error message.

Priority: High
Story Points: 2
Notes:

Implement secure authentication with encrypted passwords and session management.

User Story 2: Doctor Logout

Title: Doctor
As a doctor, I want to log out of the portal so that I can protect my personal data and prevent unauthorized access.

Acceptance Criteria:

The doctor can click a “Logout” button to end the active session.

The session token is invalidated upon logout.

The doctor is redirected to the login page after successful logout.

Priority: High
Story Points: 2
Notes:

Automatic logout should occur after a period of inactivity.

User Story 3: View Appointment Calendar

Title: Doctor
As a doctor, I want to view my appointment calendar so that I can stay organized and manage my daily schedule effectively.

Acceptance Criteria:

The system displays all confirmed appointments in a calendar view.

Each appointment shows patient name, date, and time.

The calendar updates automatically when appointments are added, canceled, or rescheduled.

Priority: High
Story Points: 4
Notes:

Include both daily and monthly view options for better visibility.

User Story 4: Mark Unavailability

Title: Doctor
As a doctor, I want to mark my unavailable times so that patients can only book appointments during my available slots.

Acceptance Criteria:

The doctor can set unavailable dates and times through the dashboard.

The system prevents patients from booking during marked unavailable slots.

The availability changes reflect immediately in the booking system.

Priority: Medium
Story Points: 3
Notes:

Unavailability settings should allow both recurring and one-time blocks.

User Story 5: Update Doctor Profile

Title: Doctor
As a doctor, I want to update my profile with my specialization and contact details so that patients have accurate and up-to-date information.

Acceptance Criteria:

The doctor can edit fields such as specialization, contact number, and bio.

Changes are validated and saved to the database.

Updated information is visible to patients in the doctor list.

Priority: Medium
Story Points: 3
Notes:

Consider adding profile picture upload functionality in later versions.

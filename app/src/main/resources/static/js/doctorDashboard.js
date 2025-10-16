// ================================
// doctorDashboard.js
// ================================

/*
  This script manages the doctor dashboard, including:
  - Fetching and displaying appointments for a selected date
  - Filtering by patient name
  - Handling "Today" button and date picker functionality
*/

// ✅ Import Required Modules
import { getAllAppointments } from "./services/appointmentRecordService.js";
import { createPatientRow } from "./components/patientRows.js";

// ================================
// Initialize Global Variables
// ================================
const tableBody = document.getElementById("patientTableBody");
let selectedDate = new Date().toISOString().split("T")[0]; // YYYY-MM-DD format
const token = localStorage.getItem("token");
let patientName = null;

// ================================
// Search Bar Event Listener
// ================================
document.getElementById("searchBar").addEventListener("input", (event) => {
  const value = event.target.value.trim();
  patientName = value !== "" ? value : "null";
  loadAppointments();
});

// ================================
// Today Button Event Listener
// ================================
document.getElementById("todayButton").addEventListener("click", () => {
  selectedDate = new Date().toISOString().split("T")[0];
  document.getElementById("datePicker").value = selectedDate;
  loadAppointments();
});

// ================================
// Date Picker Event Listener
// ================================
document.getElementById("datePicker").addEventListener("change", (event) => {
  selectedDate = event.target.value;
  loadAppointments();
});

// ================================
// Function: loadAppointments
// Purpose: Fetch and render appointments in table
// ================================
async function loadAppointments() {
  try {
    const appointments = await getAllAppointments(selectedDate, patientName, token);

    // Clear table before rendering new content
    tableBody.innerHTML = "";

    if (!appointments || appointments.length === 0) {
      const row = document.createElement("tr");
      row.innerHTML = `
        <td colspan="5" class="text-center text-gray-500 py-3">
          No Appointments found for today.
        </td>
      `;
      tableBody.appendChild(row);
      return;
    }

    // Render each appointment as a table row
    appointments.forEach((appointment) => {
      const patient = {
        id: appointment.patientId,
        name: appointment.patientName,
        phone: appointment.patientPhone,
        email: appointment.patientEmail,
        time: appointment.time,
        condition: appointment.condition,
      };

      const row = createPatientRow(patient);
      tableBody.appendChild(row);
    });
  } catch (error) {
    console.error("❌ Error loading appointments:", error);
    const errorRow = document.createElement("tr");
    errorRow.innerHTML = `
      <td colspan="5" class="text-center text-red-500 py-3">
        Error loading appointments. Try again later.
      </td>
    `;
    tableBody.appendChild(errorRow);
  }
}

// ================================
// Initial Render on Page Load
// ================================
window.addEventListener("DOMContentLoaded", () => {
  // Optional UI setup if renderContent() is defined
  if (typeof renderContent === "function") {
    renderContent();
  }

  // Set date picker default to today
  document.getElementById("datePicker").value = selectedDate;

  // Load today's appointments
  loadAppointments();
});

/*
  Import getAllAppointments to fetch appointments from the backend
  Import createPatientRow to generate a table row for each patient appointment


  Get the table body where patient rows will be added
  Initialize selectedDate with today's date in 'YYYY-MM-DD' format
  Get the saved token from localStorage (used for authenticated API calls)
  Initialize patientName to null (used for filtering by name)


  Add an 'input' event listener to the search bar
  On each keystroke:
    - Trim and check the input value
    - If not empty, use it as the patientName for filtering
    - Else, reset patientName to "null" (as expected by backend)
    - Reload the appointments list with the updated filter


  Add a click listener to the "Today" button
  When clicked:
    - Set selectedDate to today's date
    - Update the date picker UI to match
    - Reload the appointments for today


  Add a change event listener to the date picker
  When the date changes:
    - Update selectedDate with the new value
    - Reload the appointments for that specific date


  Function: loadAppointments
  Purpose: Fetch and display appointments based on selected date and optional patient name

  Step 1: Call getAllAppointments with selectedDate, patientName, and token
  Step 2: Clear the table body content before rendering new rows

  Step 3: If no appointments are returned:
    - Display a message row: "No Appointments found for today."

  Step 4: If appointments exist:
    - Loop through each appointment and construct a 'patient' object with id, name, phone, and email
    - Call createPatientRow to generate a table row for the appointment
    - Append each row to the table body

  Step 5: Catch and handle any errors during fetch:
    - Show a message row: "Error loading appointments. Try again later."


  When the page is fully loaded (DOMContentLoaded):
    - Call renderContent() (assumes it sets up the UI layout)
    - Call loadAppointments() to display today's appointments by default
*/

/*
  Import getAllAppointments to fetch appointments from the backend
  Import createPatientRow to generate a table row for each patient appointment


  Get the table body where patient rows will be added
  Initialize selectedDate with today's date in 'YYYY-MM-DD' format
  Get the saved token from localStorage (used for authenticated API calls)
  Initialize patientName to null (used for filtering by name)


  Add an 'input' event listener to the search bar
  On each keystroke:
    - Trim and check the input value
    - If not empty, use it as the patientName for filtering
    - Else, reset patientName to "null" (as expected by backend)
    - Reload the appointments list with the updated filter


  Add a click listener to the "Today" button
  When clicked:
    - Set selectedDate to today's date
    - Update the date picker UI to match
    - Reload the appointments for today


  Add a change event listener to the date picker
  When the date changes:
    - Update selectedDate with the new value
    - Reload the appointments for that specific date


  Function: loadAppointments
  Purpose: Fetch and display appointments based on selected date and optional patient name

  Step 1: Call getAllAppointments with selectedDate, patientName, and token
  Step 2: Clear the table body content before rendering new rows

  Step 3: If no appointments are returned:
    - Display a message row: "No Appointments found for today."

  Step 4: If appointments exist:
    - Loop through each appointment and construct a 'patient' object with id, name, phone, and email
    - Call createPatientRow to generate a table row for the appointment
    - Append each row to the table body

  Step 5: Catch and handle any errors during fetch:
    - Show a message row: "Error loading appointments. Try again later."


  When the page is fully loaded (DOMContentLoaded):
    - Call renderContent() (assumes it sets up the UI layout)
    - Call loadAppointments() to display today's appointments by default
*/

// ================================
// adminDashboard.js
// ================================

/*
  This script handles the admin dashboard functionality for managing doctors:
  - Loads all doctor cards
  - Filters doctors by name, time, or specialty
  - Adds a new doctor via modal form
*/

// ✅ Import Required Modules
import { openModal } from "../components/modals.js";
import { getDoctors, filterDoctors, saveDoctor } from "./services/doctorServices.js";
import { createDoctorCard } from "./components/doctorCard.js";

// ================================
// Event Binding: Add Doctor Button
// ================================
document.getElementById("addDocBtn").addEventListener("click", () => {
  openModal("addDoctor");
});

// ================================
// Load Doctor Cards on Page Load
// ================================
window.addEventListener("DOMContentLoaded", () => {
  loadDoctorCards();
});

// ================================
// Function: loadDoctorCards
// Purpose: Fetch all doctors and display them as cards
// ================================
async function loadDoctorCards() {
  try {
    const doctors = await getDoctors();

    renderDoctorCards(doctors);
  } catch (error) {
    console.error("❌ Error loading doctor cards:", error);
  }
}

// ================================
// Function: renderDoctorCards
// Purpose: Utility function to render doctor cards
// ================================
function renderDoctorCards(doctors) {
  const contentDiv = document.getElementById("content");
  contentDiv.innerHTML = "";

  if (!doctors || doctors.length === 0) {
    contentDiv.innerHTML = "<p class='text-center text-gray-500'>No doctors found.</p>";
    return;
  }

  doctors.forEach((doctor) => {
    const card = createDoctorCard(doctor);
    contentDiv.appendChild(card);
  });
}

// ================================
// Search & Filter Event Binding
// ================================
document.getElementById("searchBar").addEventListener("input", filterDoctorsOnChange);
document.getElementById("filterTime").addEventListener("change", filterDoctorsOnChange);
document.getElementById("filterSpecialty").addEventListener("change", filterDoctorsOnChange);

// ================================
// Function: filterDoctorsOnChange
// Purpose: Filter doctors dynamically based on user input
// ================================
async function filterDoctorsOnChange() {
  try {
    const name = document.getElementById("searchBar").value.trim() || null;
    const time = document.getElementById("filterTime").value || null;
    const specialty = document.getElementById("filterSpecialty").value || null;

    const filteredDoctors = await filterDoctors(name, time, specialty);

    if (filteredDoctors && filteredDoctors.length > 0) {
      renderDoctorCards(filteredDoctors);
    } else {
      const contentDiv = document.getElementById("content");
      contentDiv.innerHTML = "<p class='text-center text-gray-500'>No doctors found with the given filters.</p>";
    }
  } catch (error) {
    console.error("❌ Error filtering doctors:", error);
    alert("An error occurred while filtering doctors.");
  }
}

// ================================
// Function: adminAddDoctor
// Purpose: Collect data and add new doctor
// ================================
export async function adminAddDoctor() {
  try {
    // Collect input values
    const name = document.getElementById("doctorName").value.trim();
    const email = document.getElementById("doctorEmail").value.trim();
    const password = document.getElementById("doctorPassword").value.trim();
    const mobile = document.getElementById("doctorMobile").value.trim();
    const specialty = document.getElementById("doctorSpecialty").value.trim();
    const availability = Array.from(document.querySelectorAll("input[name='availability']:checked")).map(
      (cb) => cb.value
    );

    // Retrieve admin authentication token
    const token = localStorage.getItem("token");
    if (!token) {
      alert("Admin not authenticated. Please log in again.");
      return;
    }

    // Build doctor object
    const doctor = {
      name,
      email,
      password,
      mobile,
      specialty,
      availability,
    };

    // Send save request
    const response = await saveDoctor(doctor, token);

    if (response.success) {
      alert("✅ Doctor added successfully!");
      document.getElementById("addDoctorModal").close();
      loadDoctorCards();
    } else {
      alert(`❌ Failed to add doctor: ${response.message}`);
    }
  } catch (error) {
    console.error("❌ Error adding doctor:", error);
    alert("An unexpected error occurred while adding doctor.");
  }
}

/*
  This script handles the admin dashboard functionality for managing doctors:
  - Loads all doctor cards
  - Filters doctors by name, time, or specialty
  - Adds a new doctor via modal form


  Attach a click listener to the "Add Doctor" button
  When clicked, it opens a modal form using openModal('addDoctor')


  When the DOM is fully loaded:
    - Call loadDoctorCards() to fetch and display all doctors


  Function: loadDoctorCards
  Purpose: Fetch all doctors and display them as cards

    Call getDoctors() from the service layer
    Clear the current content area
    For each doctor returned:
    - Create a doctor card using createDoctorCard()
    - Append it to the content div

    Handle any fetch errors by logging them


  Attach 'input' and 'change' event listeners to the search bar and filter dropdowns
  On any input change, call filterDoctorsOnChange()


  Function: filterDoctorsOnChange
  Purpose: Filter doctors based on name, available time, and specialty

    Read values from the search bar and filters
    Normalize empty values to null
    Call filterDoctors(name, time, specialty) from the service

    If doctors are found:
    - Render them using createDoctorCard()
    If no doctors match the filter:
    - Show a message: "No doctors found with the given filters."

    Catch and display any errors with an alert


  Function: renderDoctorCards
  Purpose: A helper function to render a list of doctors passed to it

    Clear the content area
    Loop through the doctors and append each card to the content area


  Function: adminAddDoctor
  Purpose: Collect form data and add a new doctor to the system

    Collect input values from the modal form
    - Includes name, email, phone, password, specialty, and available times

    Retrieve the authentication token from localStorage
    - If no token is found, show an alert and stop execution

    Build a doctor object with the form values

    Call saveDoctor(doctor, token) from the service

    If save is successful:
    - Show a success message
    - Close the modal and reload the page

    If saving fails, show an error message
*/

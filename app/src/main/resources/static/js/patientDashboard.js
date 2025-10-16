// patientDashboard.js

/*
  ========================================
  IMPORT REQUIRED MODULES
  ========================================
  - createDoctorCard: renders doctor information cards
  - openModal: handles modal opening for login/signup
  - getDoctors, filterDoctors: fetch and filter doctor data from backend
  - patientLogin, patientSignup: manage patient authentication
*/

import { createDoctorCard } from './components/doctorCard.js';
import { openModal } from './components/modals.js';
import { getDoctors, filterDoctors } from './services/doctorServices.js';
import { patientLogin, patientSignup } from './services/patientServices.js';


/*
  ========================================
  LOAD DOCTOR CARDS ON PAGE LOAD
  ========================================
  When the page loads:
    - Call loadDoctorCards() to fetch and render available doctors.
    - This function:
        → Calls getDoctors() to fetch all doctors.
        → Clears previous content inside #content.
        → Renders each doctor using createDoctorCard().
*/
document.addEventListener("DOMContentLoaded", () => {
  loadDoctorCards();
  bindModalTriggers();
  bindFilterEvents();
});


/*
  ========================================
  FUNCTION: loadDoctorCards
  ========================================
  Fetches all available doctors and renders them as cards.
*/
async function loadDoctorCards() {
  try {
    const doctors = await getDoctors();
    const contentDiv = document.getElementById("content");
    contentDiv.innerHTML = "";

    if (doctors.length > 0) {
      doctors.forEach(doctor => {
        const card = createDoctorCard(doctor);
        contentDiv.appendChild(card);
      });
    } else {
      contentDiv.innerHTML = "<p>No doctors found.</p>";
    }
  } catch (error) {
    console.error("❌ Failed to load doctors:", error);
    displayMessage("An error occurred while loading doctors. Please try again.");
  }
}


/*
  ========================================
  BIND MODAL TRIGGERS FOR LOGIN AND SIGNUP
  ========================================
  - #patientSignup → opens signup modal
  - #patientLogin  → opens login modal
*/
function bindModalTriggers() {
  const signupBtn = document.getElementById("patientSignup");
  const loginBtn = document.getElementById("patientLogin");

  if (signupBtn) signupBtn.addEventListener("click", () => openModal("patientSignup"));
  if (loginBtn) loginBtn.addEventListener("click", () => openModal("patientLogin"));
}


/*
  ========================================
  SEARCH AND FILTER LOGIC
  ========================================
  - Listens to changes in:
      → Search bar (#searchBar)
      → Availability time (#filterTime)
      → Specialty filter (#filterSpecialty)
  - Each change triggers filterDoctorsOnChange() to fetch filtered results.
*/
function bindFilterEvents() {
  const searchBar = document.getElementById("searchBar");
  const filterTime = document.getElementById("filterTime");
  const filterSpecialty = document.getElementById("filterSpecialty");

  if (searchBar) searchBar.addEventListener("input", filterDoctorsOnChange);
  if (filterTime) filterTime.addEventListener("change", filterDoctorsOnChange);
  if (filterSpecialty) filterSpecialty.addEventListener("change", filterDoctorsOnChange);
}


/*
  ========================================
  FUNCTION: filterDoctorsOnChange
  ========================================
  - Gathers values from filter inputs.
  - Calls filterDoctors(name, time, specialty) to fetch filtered doctors.
  - Clears existing content before rendering new results.
  - If no matches, displays a fallback message.
*/
async function filterDoctorsOnChange() {
  const name = document.getElementById("searchBar")?.value.trim() || null;
  const time = document.getElementById("filterTime")?.value || null;
  const specialty = document.getElementById("filterSpecialty")?.value || null;

  try {
    const response = await filterDoctors(name, time, specialty);
    const doctors = response?.doctors || [];

    const contentDiv = document.getElementById("content");
    contentDiv.innerHTML = "";

    if (doctors.length > 0) {
      doctors.forEach(doctor => {
        const card = createDoctorCard(doctor);
        contentDiv.appendChild(card);
      });
    } else {
      displayMessage("No doctors found with the given filters.");
    }
  } catch (error) {
    console.error("❌ Failed to filter doctors:", error);
    displayMessage("An error occurred while filtering doctors.");
  }
}


/*
  ========================================
  RENDER UTILITY FUNCTION
  ========================================
  Used to show fallback messages or generic information in the content area.
*/
function displayMessage(message) {
  const contentDiv = document.getElementById("content");
  contentDiv.innerHTML = `<p>${message}</p>`;
}


/*
  ========================================
  HANDLE PATIENT SIGNUP
  ========================================
  - Triggered when signup form is submitted.
  - Collects name, email, password, phone, and address.
  - Calls patientSignup() to send data to backend.
  - On success: shows alert, closes modal, and reloads page.
  - On failure: displays an error message.
*/
window.signupPatient = async function () {
  try {
    const data = {
      name: document.getElementById("name").value,
      email: document.getElementById("email").value,
      password: document.getElementById("password").value,
      phone: document.getElementById("phone").value,
      address: document.getElementById("address").value
    };

    const { success, message } = await patientSignup(data);

    if (success) {
      alert(message);
      document.getElementById("modal").style.display = "none";
      window.location.reload();
    } else {
      alert(message);
    }
  } catch (error) {
    console.error("❌ Signup failed:", error);
    alert("An error occurred while signing up. Please try again.");
  }
};


/*
  ========================================
  HANDLE PATIENT LOGIN
  ========================================
  - Triggered when login form is submitted.
  - Captures email and password.
  - Calls patientLogin() to authenticate.
  - On success:
      → Stores token in localStorage.
      → Calls selectRole('loggedPatient').
      → Redirects to loggedPatientDashboard.html.
  - On failure:
      → Displays alert message.
*/
window.loginPatient = async function () {
  try {
    const data = {
      email: document.getElementById("email").value,
      password: document.getElementById("password").value
    };

    const response = await patientLogin(data);
    if (response.ok) {
      const result = await response.json();
      localStorage.setItem('token', result.token);
      selectRole('loggedPatient');
      window.location.href = '/pages/loggedPatientDashboard.html';
    } else {
      alert('❌ Invalid credentials!');
    }
  } catch (error) {
    console.error("❌ Login failed:", error);
    alert("An error occurred while logging in. Please try again.");
  }
};


/*
  ========================================
  NOTES
  ========================================
  - Ensure modal IDs (patientSignup, patientLogin) exist in the HTML.
  - getDoctors() and filterDoctors() use async/await for proper flow.
  - createDoctorCard() ensures consistent UI for doctor cards.
  - Always show fallback messages for empty or failed API results.
*/

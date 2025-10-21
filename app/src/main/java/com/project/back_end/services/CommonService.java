package com.project.back_end.services;package com.project.back_end.services;package com.project.back_end.services;



import org.springframework.stereotype.Service;

import java.util.*;

import com.project.back_end.DTO.Login;import com.project.back_end.DTO.Login;

@Service

public class CommonService {import com.project.back_end.models.Admin;import com.project.back_end.models.Admin;



    // Admin authenticationimport com.project.back_end.models.Doctor;import com.project.back_end.models.Doctor;

    public boolean validateAdminLogin(String username, String password) {

        // Mock admin credentialsimport com.project.back_end.models.Patient;import com.project.back_end.models.Patient;

        return "admin".equals(username) && "admin123".equals(password);

    }import org.springframework.http.ResponseEntity;import org.springframework.http.ResponseEntity;



    // Doctor authentication  import org.springframework.stereotype.Service;import org.springframework.stereotype.Service;

    public boolean validateDoctorLogin(String username, String password) {

        // Mock doctor credentialsimport java.util.HashMap;import java.util.HashMap;

        return "doctor".equals(username) && "doctor123".equals(password);

    }import java.util.Map;import java.util.Map;



    // Patient authenticationimport java.util.ArrayList;import java.util.ArrayList;

    public boolean validatePatientLogin(String username, String password) {

        // Mock patient credentialsimport java.util.List;import java.util.List;

        return "patient".equals(username) && "patient123".equals(password);

    }



    // Search doctors functionality@Service@Service

    public List<Map<String, Object>> searchDoctors(String name) {

        List<Map<String, Object>> doctors = new ArrayList<>();public class CommonService {public class CommonService {

        

        // Mock doctor data

        Map<String, Object> doctor1 = new HashMap<>();

        doctor1.put("id", 1);    public Map<String, Object> validateToken(String token, String role) {    public Map<String, Object> validateToken(String token, String role) {

        doctor1.put("name", "Dr. John Smith");

        doctor1.put("specialization", "Cardiology");        Map<String, Object> result = new HashMap<>();        Map<String, Object> result = new HashMap<>();

        doctor1.put("experience", "10 years");

        doctor1.put("rating", 4.8);        result.put("valid", true);        result.put("valid", true);

        doctors.add(doctor1);

        result.put("role", role);        result.put("role", role);

        Map<String, Object> doctor2 = new HashMap<>();

        doctor2.put("id", 2);        return result;        return result;

        doctor2.put("name", "Dr. Sarah Johnson");

        doctor2.put("specialization", "Neurology");    }    }

        doctor2.put("experience", "8 years");

        doctor2.put("rating", 4.9);

        doctors.add(doctor2);

    public Map<String, Object> validateAdmin(Admin admin) {    public Map<String, Object> validateAdmin(Admin admin) {

        Map<String, Object> doctor3 = new HashMap<>();

        doctor3.put("id", 3);        Map<String, Object> result = new HashMap<>();        Map<String, Object> result = new HashMap<>();

        doctor3.put("name", "Dr. Michael Brown");

        doctor3.put("specialization", "Orthopedics");                

        doctor3.put("experience", "12 years");

        doctor3.put("rating", 4.7);        // Default admin credentials for testing        // Default admin credentials for testing

        doctors.add(doctor3);

        if ("admin".equals(admin.getUsername()) && "admin123".equals(admin.getPassword())) {        if ("admin".equals(admin.getUsername()) && "admin123".equals(admin.getPassword())) {

        Map<String, Object> doctor4 = new HashMap<>();

        doctor4.put("id", 4);            result.put("token", "admin-token-123");            result.put("token", "admin-token-123");

        doctor4.put("name", "Dr. Emily Davis");

        doctor4.put("specialization", "Pediatrics");            result.put("success", true);            result.put("success", true);

        doctor4.put("experience", "6 years");

        doctor4.put("rating", 4.6);            result.put("message", "Admin login successful");            result.put("message", "Admin login successful");

        doctors.add(doctor4);

        } else {        } else {

        Map<String, Object> doctor5 = new HashMap<>();

        doctor5.put("id", 5);            result.put("success", false);            result.put("success", false);

        doctor5.put("name", "Dr. Robert Wilson");

        doctor5.put("specialization", "Dermatology");            result.put("message", "Invalid admin credentials");            result.put("message", "Invalid admin credentials");

        doctor5.put("experience", "15 years");

        doctor5.put("rating", 4.8);        }        }

        doctors.add(doctor5);

                

        // Filter by name if provided

        if (name != null && !name.trim().isEmpty()) {        return result;        return result;

            return doctors.stream()

                .filter(doctor -> doctor.get("name").toString().toLowerCase()    }    }

                    .contains(name.toLowerCase()))

                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

        }

            public Map<String, Object> validateDoctorLogin(Login login) {    public Map<String, Object> validateDoctorLogin(Login login) {

        return doctors;

    }        Map<String, Object> result = new HashMap<>();        Map<String, Object> result = new HashMap<>();



    // Get doctor appointments                

    public List<Map<String, Object>> getDoctorAppointments(int doctorId) {

        List<Map<String, Object>> appointments = new ArrayList<>();        // Default doctor credentials for testing        // Default doctor credentials for testing

        

        Map<String, Object> appointment1 = new HashMap<>();        if ("doctor".equals(login.getUsername()) && "doctor123".equals(login.getPassword())) {        if ("doctor".equals(login.getUsername()) && "doctor123".equals(login.getPassword())) {

        appointment1.put("id", 1);

        appointment1.put("patientName", "Alice Brown");            result.put("token", "doctor-token-123");            result.put("token", "doctor-token-123");

        appointment1.put("date", "2025-10-22");

        appointment1.put("time", "10:00 AM");            result.put("success", true);            result.put("success", true);

        appointment1.put("status", "Scheduled");

        appointments.add(appointment1);            result.put("message", "Doctor login successful");            result.put("message", "Doctor login successful");



        Map<String, Object> appointment2 = new HashMap<>();        } else {        } else {

        appointment2.put("id", 2);

        appointment2.put("patientName", "Bob Wilson");            result.put("success", false);            result.put("success", false);

        appointment2.put("date", "2025-10-22");

        appointment2.put("time", "2:00 PM");            result.put("message", "Invalid doctor credentials");            result.put("message", "Invalid doctor credentials");

        appointment2.put("status", "Scheduled");

        appointments.add(appointment2);        }        }



        return appointments;                

    }

        return result;        return result;

    // Get patient appointments

    public List<Map<String, Object>> getPatientAppointments(String patientUsername) {    }    }

        List<Map<String, Object>> appointments = new ArrayList<>();

        

        Map<String, Object> appointment1 = new HashMap<>();

        appointment1.put("id", 1);    public ResponseEntity<Object> validatePatientLogin(Login login) {    public ResponseEntity<Object> validatePatientLogin(Login login) {

        appointment1.put("doctorName", "Dr. John Smith");

        appointment1.put("specialization", "Cardiology");        Map<String, Object> result = new HashMap<>();        Map<String, Object> result = new HashMap<>();

        appointment1.put("date", "2025-10-25");

        appointment1.put("time", "3:00 PM");                

        appointment1.put("status", "Scheduled");

        appointments.add(appointment1);        // Default patient credentials for testing        // Default patient credentials for testing



        return appointments;        if ("patient".equals(login.getUsername()) && "patient123".equals(login.getPassword())) {        if ("patient".equals(login.getUsername()) && "patient123".equals(login.getPassword())) {

    }

            result.put("token", "patient-token-123");            result.put("token", "patient-token-123");

    // Book appointment

    public Map<String, Object> bookAppointment(int doctorId, String patientUsername, String date, String time) {            result.put("success", true);            result.put("success", true);

        Map<String, Object> result = new HashMap<>();

        result.put("success", true);            result.put("message", "Patient login successful");            result.put("message", "Patient login successful");

        result.put("appointmentId", new Random().nextInt(1000) + 1);

        result.put("message", "Appointment booked successfully");        } else {        } else {

        return result;

    }            result.put("success", false);            result.put("success", false);



    // Add doctor (admin function)            result.put("message", "Invalid patient credentials");            result.put("message", "Invalid patient credentials");

    public Map<String, Object> addDoctor(Map<String, Object> doctorData) {

        Map<String, Object> result = new HashMap<>();        }        }

        result.put("success", true);

        result.put("doctorId", new Random().nextInt(1000) + 100);                

        result.put("message", "Doctor added successfully");

        return result;        return ResponseEntity.ok(result);        return ResponseEntity.ok(result);

    }

    }    }

    // Get all doctors (admin function)

    public List<Map<String, Object>> getAllDoctors() {

        return searchDoctors(null); // Return all doctors

    }    public boolean patientExists(Patient patient) {    public boolean patientExists(Patient patient) {



    // Delete doctor (admin function)        return false;        return false;

    public Map<String, Object> deleteDoctor(int doctorId) {

        Map<String, Object> result = new HashMap<>();    }    }

        result.put("success", true);

        result.put("message", "Doctor deleted successfully");

        return result;

    }    public ResponseEntity<Object> filterPatientAppointments(Long patientId, String condition, String name) {    public ResponseEntity<Object> filterPatientAppointments(Long patientId, String condition, String name) {



    // Generate appointment statistics (admin function)        Map<String, Object> result = new HashMap<>();        Map<String, Object> result = new HashMap<>();

    public Map<String, Object> generateAppointmentStatistics() {

        Map<String, Object> stats = new HashMap<>();        result.put("appointments", new HashMap<>());        result.put("appointments", new HashMap<>());

        stats.put("totalAppointments", 156);

        stats.put("completedAppointments", 142);        return ResponseEntity.ok(result);        return ResponseEntity.ok(result);

        stats.put("cancelledAppointments", 8);

        stats.put("upcomingAppointments", 6);    }    }

        stats.put("totalDoctors", 5);

        stats.put("totalPatients", 89);

        return stats;

    }    public Map<String, Object> addDoctor(Doctor doctor) {    public Map<String, Object> addDoctor(Doctor doctor) {

}
        Map<String, Object> result = new HashMap<>();        Map<String, Object> result = new HashMap<>();

                

        try {        try {

            // Basic validation            // Basic validation

            if (doctor.getName() == null || doctor.getName().trim().length() < 3) {            if (doctor.getName() == null || doctor.getName().trim().length() < 3) {

                result.put("success", false);                result.put("success", false);

                result.put("message", "Doctor name must be at least 3 characters long");                result.put("message", "Doctor name must be at least 3 characters long");

                return result;                return result;

            }            }

                        

            if (doctor.getEmail() == null || !doctor.getEmail().contains("@")) {            if (doctor.getEmail() == null || !doctor.getEmail().contains("@")) {

                result.put("success", false);                result.put("success", false);

                result.put("message", "Valid email address is required");                result.put("message", "Valid email address is required");

                return result;                return result;

            }            }

                        

            if (doctor.getPhone() == null || !doctor.getPhone().matches("\\d{10}")) {            if (doctor.getPhone() == null || !doctor.getPhone().matches("\\d{10}")) {

                result.put("success", false);                result.put("success", false);

                result.put("message", "Phone number must be exactly 10 digits");                result.put("message", "Phone number must be exactly 10 digits");

                return result;                return result;

            }            }

                        

            if (doctor.getSpecialty() == null || doctor.getSpecialty().trim().isEmpty()) {            if (doctor.getSpecialty() == null || doctor.getSpecialty().trim().isEmpty()) {

                result.put("success", false);                result.put("success", false);

                result.put("message", "Specialty is required");                result.put("message", "Specialty is required");

                return result;                return result;

            }            }

                        

            if (doctor.getPassword() == null || doctor.getPassword().length() < 6) {            if (doctor.getPassword() == null || doctor.getPassword().length() < 6) {

                result.put("success", false);                result.put("success", false);

                result.put("message", "Password must be at least 6 characters long");                result.put("message", "Password must be at least 6 characters long");

                return result;                return result;

            }            }

                        

            // In a real application, you would save this to a database            // In a real application, you would save this to a database

            // For now, we'll simulate success            // For now, we'll simulate success

            result.put("success", true);            result.put("success", true);

            result.put("message", "Doctor added successfully");            result.put("message", "Doctor added successfully");

            result.put("doctorId", generateDoctorId());            result.put("doctorId", generateDoctorId());

                        

        } catch (Exception e) {        } catch (Exception e) {

            result.put("success", false);            result.put("success", false);

            result.put("message", "An error occurred while adding the doctor");            result.put("message", "An error occurred while adding the doctor");

        }        }

                

        return result;        return result;

    }    }

        

    private Long generateDoctorId() {    private Long generateDoctorId() {

        // Simple ID generation for demo purposes        // Simple ID generation for demo purposes

        return System.currentTimeMillis() % 10000;        return System.currentTimeMillis() % 10000;

    }    }



    public List<Map<String, Object>> getDoctorAppointments() {    public List<Map<String, Object>> getDoctorAppointments() {

        List<Map<String, Object>> appointments = new ArrayList<>();        List<Map<String, Object>> appointments = new ArrayList<>();

                

        // Mock appointment data        // Mock appointment data

        Map<String, Object> apt1 = new HashMap<>();        Map<String, Object> apt1 = new HashMap<>();

        apt1.put("id", 1);        apt1.put("id", 1);

        apt1.put("patientName", "John Smith");        apt1.put("patientName", "John Smith");

        apt1.put("date", "2025-10-21");        apt1.put("date", "2025-10-19");

        apt1.put("time", "09:00 AM");        apt1.put("time", "09:00 AM");

        apt1.put("type", "Regular Checkup");        apt1.put("type", "Regular Checkup");

        apt1.put("status", "Scheduled");        apt1.put("status", "Scheduled");

        appointments.add(apt1);        appointments.add(apt1);

                

        Map<String, Object> apt2 = new HashMap<>();        Map<String, Object> apt2 = new HashMap<>();

        apt2.put("id", 2);        apt2.put("id", 2);

        apt2.put("patientName", "Sarah Johnson");        apt2.put("patientName", "Sarah Johnson");

        apt2.put("date", "2025-10-21");        apt2.put("date", "2025-10-19");

        apt2.put("time", "10:30 AM");        apt2.put("time", "10:30 AM");

        apt2.put("type", "Follow-up");        apt2.put("type", "Follow-up");

        apt2.put("status", "Scheduled");        apt2.put("status", "Scheduled");

        appointments.add(apt2);        appointments.add(apt2);

                

        Map<String, Object> apt3 = new HashMap<>();        Map<String, Object> apt3 = new HashMap<>();

        apt3.put("id", 3);        apt3.put("id", 3);

        apt3.put("patientName", "Michael Brown");        apt3.put("patientName", "Michael Brown");

        apt3.put("date", "2025-10-22");        apt3.put("date", "2025-10-20");

        apt3.put("time", "02:00 PM");        apt3.put("time", "02:00 PM");

        apt3.put("type", "Consultation");        apt3.put("type", "Consultation");

        apt3.put("status", "Scheduled");        apt3.put("status", "Scheduled");

        appointments.add(apt3);        appointments.add(apt3);

                

        return appointments;        return appointments;

    }    }



    public List<Map<String, Object>> searchDoctors(String searchTerm) {    public List<Map<String, Object>> searchDoctors(String searchTerm) {

        List<Map<String, Object>> doctors = new ArrayList<>();        List<Map<String, Object>> doctors = new ArrayList<>();

                

        // Mock doctor data        // Mock doctor data

        Map<String, Object> doc1 = new HashMap<>();        Map<String, Object> doc1 = new HashMap<>();

        doc1.put("id", 1);        doc1.put("id", 1);

        doc1.put("name", "Dr. Emily Wilson");        doc1.put("name", "Dr. Emily Wilson");

        doc1.put("specialty", "Cardiology");        doc1.put("specialty", "Cardiology");

        doc1.put("email", "emily.wilson@hospital.com");        doc1.put("email", "emily.wilson@hospital.com");

        doc1.put("phone", "1234567890");        doc1.put("phone", "1234567890");

        doc1.put("availability", "Mon-Fri 9:00 AM - 5:00 PM");        doc1.put("availability", "Mon-Fri 9:00 AM - 5:00 PM");

                

        Map<String, Object> doc2 = new HashMap<>();        Map<String, Object> doc2 = new HashMap<>();

        doc2.put("id", 2);        doc2.put("id", 2);

        doc2.put("name", "Dr. Robert Davis");        doc2.put("name", "Dr. Robert Davis");

        doc2.put("specialty", "Dermatology");        doc2.put("specialty", "Dermatology");

        doc2.put("email", "robert.davis@hospital.com");        doc2.put("email", "robert.davis@hospital.com");

        doc2.put("phone", "1234567891");        doc2.put("phone", "1234567891");

        doc2.put("availability", "Mon-Wed 10:00 AM - 4:00 PM");        doc2.put("availability", "Mon-Wed 10:00 AM - 4:00 PM");

                

        Map<String, Object> doc3 = new HashMap<>();        Map<String, Object> doc3 = new HashMap<>();

        doc3.put("id", 3);        doc3.put("id", 3);

        doc3.put("name", "Dr. Lisa Anderson");        doc3.put("name", "Dr. Lisa Anderson");

        doc3.put("specialty", "Pediatrics");        doc3.put("specialty", "Pediatrics");

        doc3.put("email", "lisa.anderson@hospital.com");        doc3.put("email", "lisa.anderson@hospital.com");

        doc3.put("phone", "1234567892");        doc3.put("phone", "1234567892");

        doc3.put("availability", "Tue-Thu 8:00 AM - 3:00 PM");        doc3.put("availability", "Tue-Thu 8:00 AM - 3:00 PM");

                

        Map<String, Object> doc4 = new HashMap<>();        if (searchTerm == null || searchTerm.trim().isEmpty()) {

        doc4.put("id", 4);            doctors.add(doc1);

        doc4.put("name", "Dr. Michael Chen");            doctors.add(doc2);

        doc4.put("specialty", "Orthopedics");            doctors.add(doc3);

        doc4.put("email", "michael.chen@hospital.com");        } else {

        doc4.put("phone", "1234567893");            String searchLower = searchTerm.toLowerCase();

        doc4.put("availability", "Mon-Fri 8:00 AM - 6:00 PM");            if (doc1.get("name").toString().toLowerCase().contains(searchLower) || 

                        doc1.get("specialty").toString().toLowerCase().contains(searchLower)) {

        Map<String, Object> doc5 = new HashMap<>();                doctors.add(doc1);

        doc5.put("id", 5);            }

        doc5.put("name", "Dr. Sarah Johnson");            if (doc2.get("name").toString().toLowerCase().contains(searchLower) || 

        doc5.put("specialty", "Neurology");                doc2.get("specialty").toString().toLowerCase().contains(searchLower)) {

        doc5.put("email", "sarah.johnson@hospital.com");                doctors.add(doc2);

        doc5.put("phone", "1234567894");            }

        doc5.put("availability", "Tue-Sat 9:00 AM - 4:00 PM");            if (doc3.get("name").toString().toLowerCase().contains(searchLower) || 

                        doc3.get("specialty").toString().toLowerCase().contains(searchLower)) {

        if (searchTerm == null || searchTerm.trim().isEmpty()) {                doctors.add(doc3);

            doctors.add(doc1);            }

            doctors.add(doc2);        }

            doctors.add(doc3);        

            doctors.add(doc4);        return doctors;

            doctors.add(doc5);    }

        } else {}
            String searchLower = searchTerm.toLowerCase();
            if (doc1.get("name").toString().toLowerCase().contains(searchLower) || 
                doc1.get("specialty").toString().toLowerCase().contains(searchLower)) {
                doctors.add(doc1);
            }
            if (doc2.get("name").toString().toLowerCase().contains(searchLower) || 
                doc2.get("specialty").toString().toLowerCase().contains(searchLower)) {
                doctors.add(doc2);
            }
            if (doc3.get("name").toString().toLowerCase().contains(searchLower) || 
                doc3.get("specialty").toString().toLowerCase().contains(searchLower)) {
                doctors.add(doc3);
            }
            if (doc4.get("name").toString().toLowerCase().contains(searchLower) || 
                doc4.get("specialty").toString().toLowerCase().contains(searchLower)) {
                doctors.add(doc4);
            }
            if (doc5.get("name").toString().toLowerCase().contains(searchLower) || 
                doc5.get("specialty").toString().toLowerCase().contains(searchLower)) {
                doctors.add(doc5);
            }
        }
        
        return doctors;
    }
}
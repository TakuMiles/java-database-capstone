package com.project.back_end.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.*;

@Document(collection = "prescriptions")
public class Prescription {

    @Id
    private String id;

    @NotNull(message = "Patient name cannot be null")
    @Size(min = 3, max = 100, message = "Patient name must be between 3 and 100 characters")
    private String patientName;

    @NotNull(message = "Appointment ID cannot be null")
    private Long appointmentId;

    @NotNull(message = "Medication cannot be null")
    @Size(min = 3, max = 100, message = "Medication name must be between 3 and 100 characters")
    private String medication;

    @NotNull(message = "Dosage cannot be null")
    @Size(min = 3, max = 20, message = "Dosage must be between 3 and 20 characters")
    private String dosage;

    @Size(max = 200, message = "Doctor notes cannot exceed 200 characters")
    private String doctorNotes;


    // Constructors
    public Prescription() {
    }

    public Prescription(String patientName, Long appointmentId, String medication, String dosage) {
        this.patientName = patientName;
        this.appointmentId = appointmentId;
        this.medication = medication;
        this.dosage = dosage;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }


// @Document annotation:
//    - Marks this class as a MongoDB document managed by Spring Data.
//    - The "collection" parameter specifies the MongoDB collection name ("prescriptions").

// 1. 'id' field:
//    - Type: private String
//    - Represents the unique identifier for each prescription.
//    - The @Id annotation marks it as the MongoDB _id field (auto-generated).

// 2. 'patientName' field:
//    - Type: private String
//    - Represents the patient's full name for whom the prescription is issued.
//    - The @NotNull annotation ensures that the field cannot be null.
//    - The @Size(min = 3, max = 100) annotation validates the name length.

// 3. 'appointmentId' field:
//    - Type: private Long
//    - Represents a reference to the appointment entity (relational link).
//    - The @NotNull annotation ensures this reference is required.

// 4. 'medication' field:
//    - Type: private String
//    - Represents the prescribed medication's name.
//    - The @NotNull annotation ensures a medication must be provided.
//    - The @Size(min = 3, max = 100) annotation ensures valid name length.

// 5. 'dosage' field:
//    - Type: private String
//    - Represents dosage details of the medication.
//    - The @NotNull annotation ensures a dosage must be specified.
//    - The @Size(min = 3, max = 20) annotation limits its character length.

// 6. 'doctorNotes' field:
//    - Type: private String
//    - Represents optional notes written by the doctor.
//    - The @Size(max = 200) annotation ensures it does not exceed 200 characters.

// 7. Getters and Setters:
//    - Standard methods to access and modify private fields.
//    - Required by frameworks such as Spring for data binding and serialization.

}

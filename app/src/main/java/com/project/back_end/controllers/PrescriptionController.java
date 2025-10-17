package com.project.back_end.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.project.back_end.services.PrescriptionService;
import com.project.back_end.services.CommonService;
import com.project.back_end.services.AppointmentService;
import com.project.back_end.models.Prescription;
import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("${api.path}" + "prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private CommonService service;

    @Autowired
    private AppointmentService appointmentService;
    
    // 1. Set Up the Controller Class:
    //    - Annotate the class with `@RestController` to define it as a REST API controller.
    //    - Use `@RequestMapping("${api.path}prescription")` to set the base path for all prescription-related endpoints.
    //    - This controller manages creating and retrieving prescriptions tied to appointments.

    // 2. Autowire Dependencies:
    //    - Inject `PrescriptionService` to handle logic related to saving and fetching prescriptions.
    //    - Inject the shared `CommonService` class for token validation and role-based access control.
    //    - Inject `AppointmentService` to update appointment status after a prescription is issued.

    // 3. Define the `savePrescription` Method:
    //    - Handles HTTP POST requests to save a new prescription for a given appointment.
    //    - Accepts a validated `Prescription` object in the request body and a doctor's token as a path variable.
    //    - Validates the token for the `"doctor"` role.
    //    - If the token is valid, updates the status of the corresponding appointment to reflect that a prescription has been added.
    //    - Delegates the saving logic to `PrescriptionService` and returns a response indicating success or failure.

    @PostMapping("/{token}")
    public ResponseEntity<String> savePrescription(@PathVariable String token, @Valid @RequestBody Prescription prescription) {
        Map<String, Object> validationResult = service.validateToken(token, "doctor");
        if (validationResult.isEmpty()) {
            try {
                // Update appointment status to indicate prescription has been added
                // appointmentService.updateAppointmentStatus(prescription.getAppointmentId(), "prescription_added");
                
                // Save the prescription
                // ResponseEntity<String> result = prescriptionService.savePrescription(prescription);
                // return result;
                
                // TODO: Implement actual service methods
                return ResponseEntity.ok("Prescription saved successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving prescription: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    // 4. Define the `getPrescription` Method:
    //    - Handles HTTP GET requests to retrieve a prescription by its associated appointment ID.
    //    - Accepts the appointment ID and a doctor's token as path variables.
    //    - Validates the token for the `"doctor"` role using the shared service.
    //    - If the token is valid, fetches the prescription using the `PrescriptionService`.
    //    - Returns the prescription details or an appropriate error message if validation fails.

    @GetMapping("/{appointmentId}/{token}")
    public ResponseEntity<String> getPrescription(@PathVariable Long appointmentId, @PathVariable String token) {
        Map<String, Object> validationResult = service.validateToken(token, "doctor");
        if (validationResult.isEmpty()) {
            try {
                // Fetch the prescription using the PrescriptionService
                // ResponseEntity<String> result = prescriptionService.getPrescriptionByAppointmentId(appointmentId);
                // return result;
                
                // TODO: Implement actual service method
                return ResponseEntity.ok("Prescription retrieved for appointment ID: " + appointmentId);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving prescription: " + e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }
}
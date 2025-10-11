## MySQL Database Design

1. patients
2. doctors
3. appointments
4. admin
5. clinic_locations
6. payments

### patients

patient_id: INT, Primary Key, Auto Increment

first_name: VARCHAR(100), Not Null

last_name: VARCHAR(100), Not Null

email: VARCHAR(150), Not Null, Unique

password_hash: VARCHAR(255), Not Null

phone: VARCHAR(20), Nullable

date_of_birth: DATE, Nullable

created_at: DATETIME, Not Null, Default Current_Timestamp

updated_at: DATETIME, Nullable, On Update Current_Timestamp

Notes:

Patient records should not be hard-deleted; use a status flag if needed to preserve appointment history.

Email and phone validation handled in application code.

### Doctors

doctor_id: INT, Primary Key, Auto Increment

first_name: VARCHAR(100), Not Null

last_name: VARCHAR(100), Not Null

email: VARCHAR(150), Not Null, Unique

password_hash: VARCHAR(255), Not Null

specialization: VARCHAR(150), Not Null

phone: VARCHAR(20), Nullable

clinic_location_id: INT, Foreign Key → clinic_locations(location_id)

availability_status: TINYINT, Default 1 (1 = Available, 0 = Unavailable)

created_at: DATETIME, Not Null, Default Current_Timestamp

Notes:

Each doctor belongs to one clinic location.

Overlapping appointments are prevented at the application level.

### Appointments

appointment_id: INT, Primary Key, Auto Increment

doctor_id: INT, Foreign Key → doctors(doctor_id)

patient_id: INT, Foreign Key → patients(patient_id)

appointment_time: DATETIME, Not Null

duration_minutes: INT, Default 60

clinic_location_id: INT, Foreign Key → clinic_locations(location_id)

status: TINYINT, Default 0 (0 = Scheduled, 1 = Completed, 2 = Cancelled)

notes: TEXT, Nullable

created_at: DATETIME, Not Null, Default Current_Timestamp

Notes:

Deleting a patient or doctor should not automatically delete appointments to preserve history.

Appointment overlap prevention should be handled in business logic.

### Admin 

admin_id: INT, Primary Key, Auto Increment

username: VARCHAR(100), Not Null, Unique

password_hash: VARCHAR(255), Not Null

email: VARCHAR(150), Not Null, Unique

role: VARCHAR(50), Default 'System Admin'

created_at: DATETIME, Not Null, Default Current_Timestamp

Notes:

Admin accounts have CRUD privileges over all entities.

## Clinic Location

location_id: INT, Primary Key, Auto Increment

name: VARCHAR(150), Not Null

address: VARCHAR(255), Not Null

city: VARCHAR(100), Not Null

phone: VARCHAR(20), Nullable

email: VARCHAR(150), Nullable

Notes:

A clinic can have multiple doctors.

Deletion should require reassignment of associated doctors first.

### Payment

payment_id: INT, Primary Key, Auto Increment

appointment_id: INT, Foreign Key → appointments(appointment_id)

patient_id: INT, Foreign Key → patients(patient_id)

amount: DECIMAL(10,2), Not Null

payment_method: VARCHAR(50), Not Null (e.g., Credit Card, PayPal, Cash)

payment_date: DATETIME, Not Null, Default Current_Timestamp

status: VARCHAR(50), Default 'Completed'

transaction_ref: VARCHAR(100), Unique

Notes:

Each payment is linked to a specific appointment and patient.

Refunds or adjustments should create new records for audit purposes.


## MongoDB Collection Design

1. prescriptions
2. feedback
3. logs

### Collection: prescriptions

{
  "_id": "ObjectId('650a1b2c3d4e5f6a7b8c9012')",
  "patient_id": 102,
  "patient_snapshot": { "first_name": "Jane", "last_name": "Doe", "dob": "1985-05-12" },
  "appointment_id": 51,
  "doctor_id": 12,
  "issued_at": "2025-10-11T10:23:00Z",
  "expires_on": "2026-04-11T00:00:00Z",
  "medications": [
    {
      "name": "Amoxicillin",
      "dosage": "500mg",
      "frequency": "8h",
      "route": "oral",
      "quantity": 21,
      "instructions": "Take for 7 days; complete full course"
    },
    {
      "name": "Paracetamol",
      "dosage": "500mg",
      "frequency": "6h PRN",
      "route": "oral",
      "quantity": 12
    }
  ],
  "refill_count": 1,
  "pharmacy": { "name": "Central Pharmacy", "address": "123 High St", "phone": "+44-20-1234-5678" },
  "attachments": [
    { "type": "scanned_pdf", "url": "s3://clinic/prescriptions/650a1b2c.pdf", "filename": "presc-51.pdf" }
  ],
  "tags": ["antibiotic", "acute"],
  "status": "active", 
  "schema_version": 1,
  "audit": [
    { "action": "created", "by": "doctor:12", "at": "2025-10-11T10:23:00Z" },
    { "action": "refill_added", "by": "pharmacy:7", "at": "2025-11-01T09:12:00Z" }
  ]
}

### Collection: feedback
{
  "_id": "ObjectId('650b2c3d4e5f6a7b8c901234')",
  "patient_id": 102,
  "doctor_id": 12,
  "appointment_id": 51,
  "rating": 4,
  "comments": "Very professional and explained everything clearly. Wait time was long.",
  "anonymous": false,
  "created_at": "2025-10-12T09:00:00Z",
  "attachments": [
    { "url": "https://cdn.example.com/feedback/650b2c.png", "filename": "wait-area.png", "mime": "image/png" }
  ],
  "tags": ["wait-time", "communication"],
  "visible_to_public": false,
  "response": { "admin_id": 1, "message": "Thanks for your feedback — we'll improve wait times.", "created_at": "2025-10-13T08:00:00Z" },
  "metadata": { "source": "web", "locale": "en-GB", "device": "Chrome" }
}

### Collection: logs

{
  "_id": "ObjectId('650c3d4e5f6a7b8c90123456')",
  "event_type": "message", 
  "actor": { "type": "patient", "id": 102 },
  "appointment_id": 51,
  "timestamp": "2025-10-11T11:00:00Z",
  "payload": {
    "message_id": "msg_20251011_0001",
    "thread_id": "thread_51_102_12",
    "from": { "type": "patient", "id": 102 },
    "to": { "type": "doctor", "id": 12 },
    "body": "Hi doctor — a quick question about the prescription dosage.",
    "attachments": [],
    "delivered": true,
    "read_at": null
  },
  "ip_address": "198.51.100.23",
  "device": "iPhone 12",
  "location": { "lat": 51.5074, "lon": -0.1278 },
  "severity": "info",
  "tags": ["chat", "clinic-1"],
  "retain_until": "2026-10-11T11:00:00Z"
}

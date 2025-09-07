import java.util.*;

// Patient Class
class Patient {
    String patientId;
    String patientName;
    int age;
    String gender;
    String contactInfo;
    List<String> medicalHistory;
    List<String> currentTreatments;

    // Static variables
    static int totalPatients = 0;

    public Patient(String patientId, String patientName, int age, String gender, String contactInfo) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
        this.contactInfo = contactInfo;
        this.medicalHistory = new ArrayList<>();
        this.currentTreatments = new ArrayList<>();
        totalPatients++;
    }

    public void updateTreatment(String treatment) {
        currentTreatments.add(treatment);
        medicalHistory.add("Treatment Added: " + treatment);
        System.out.println(patientName + " received treatment: " + treatment);
    }

    public void dischargePatient() {
        currentTreatments.clear();
        System.out.println(patientName + " has been discharged.");
    }

    public void displayInfo() {
        System.out.println("Patient: " + patientName + " (ID: " + patientId + ", Age: " + age + ", Gender: " + gender + ")");
        System.out.println("Contact: " + contactInfo);
        System.out.println("Medical History: " + medicalHistory);
        System.out.println("Current Treatments: " + currentTreatments);
    }
}

// Doctor Class
class Doctor {
    String doctorId;
    String doctorName;
    String specialization;
    List<String> availableSlots;
    int patientsHandled;
    double consultationFee;

    public Doctor(String doctorId, String doctorName, String specialization, double consultationFee, String[] slots) {
        this.doctorId = doctorId;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
        this.availableSlots = new ArrayList<>(Arrays.asList(slots));
        this.patientsHandled = 0;
    }

    public boolean bookSlot(String slot) {
        if (availableSlots.contains(slot)) {
            availableSlots.remove(slot);
            patientsHandled++;
            return true;
        }
        return false;
    }

    public void displayInfo() {
        System.out.println("Doctor: " + doctorName + " (Specialization: " + specialization + ")");
        System.out.println("Consultation Fee: $" + consultationFee);
        System.out.println("Available Slots: " + availableSlots);
        System.out.println("Patients Handled: " + patientsHandled);
    }
}

// Appointment Class
class Appointment {
    String appointmentId;
    Patient patient;
    Doctor doctor;
    String appointmentDate;
    String appointmentTime;
    String status;
    String type; // Consultation, Follow-up, Emergency

    // Static variables
    static int totalAppointments = 0;
    static String hospitalName = "CityCare Hospital";
    static double totalRevenue = 0;

    public Appointment(String appointmentId, Patient patient, Doctor doctor,
                       String appointmentDate, String appointmentTime, String type) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.status = "Scheduled";
        this.type = type;
        totalAppointments++;
    }

    public void scheduleAppointment() {
        if (doctor.bookSlot(appointmentTime)) {
            System.out.println("Appointment " + appointmentId + " scheduled with Dr. " + doctor.doctorName + " at " + appointmentTime);
        } else {
            System.out.println("Slot not available for Dr. " + doctor.doctorName);
        }
    }

    public void cancelAppointment() {
        this.status = "Cancelled";
        System.out.println("Appointment " + appointmentId + " has been cancelled.");
    }

    public double generateBill() {
        double rateMultiplier = 1.0;
        if (type.equalsIgnoreCase("Follow-up")) rateMultiplier = 0.5;
        else if (type.equalsIgnoreCase("Emergency")) rateMultiplier = 2.0;

        double bill = doctor.consultationFee * rateMultiplier;
        totalRevenue += bill;
        System.out.println("Bill Generated for " + patient.patientName + ": $" + bill + " [" + type + "]");
        return bill;
    }

    public void displayInfo() {
        System.out.println("Appointment ID: " + appointmentId + " | Patient: " + patient.patientName +
                " | Doctor: " + doctor.doctorName + " | Date: " + appointmentDate +
                " | Time: " + appointmentTime + " | Status: " + status + " | Type: " + type);
    }
}

// Hospital Management Utility
class HospitalManagement {
    public static void generateHospitalReport() {
        System.out.println("---- Hospital Report ----");
        System.out.println("Hospital: " + Appointment.hospitalName);
        System.out.println("Total Patients: " + Patient.totalPatients);
        System.out.println("Total Appointments: " + Appointment.totalAppointments);
        System.out.println("Total Revenue: $" + Appointment.totalRevenue);
    }

    public static void getDoctorUtilization(List<Doctor> doctors) {
        System.out.println("---- Doctor Utilization ----");
        for (Doctor d : doctors) {
            System.out.println(d.doctorName + " handled " + d.patientsHandled + " patients.");
        }
    }

    public static void getPatientStatistics(List<Patient> patients) {
        System.out.println("---- Patient Statistics ----");
        for (Patient p : patients) {
            System.out.println(p.patientName + " | Treatments: " + p.currentTreatments.size() + 
                               " | History Records: " + p.medicalHistory.size());
        }
    }
}

// Main Driver
public class HospitalSystem {
    public static void main(String[] args) {
        // Create Patients
        Patient p1 = new Patient("P001", "Alice", 30, "Female", "9876543210");
        Patient p2 = new Patient("P002", "Bob", 45, "Male", "8765432109");

        // Create Doctors
        Doctor d1 = new Doctor("D001", "Dr. Smith", "Cardiology", 500, new String[]{"10AM", "11AM", "2PM"});
        Doctor d2 = new Doctor("D002", "Dr. Johnson", "Neurology", 700, new String[]{"1PM", "3PM", "4PM"});

        // Schedule Appointments
        Appointment a1 = new Appointment("A101", p1, d1, "2025-09-01", "10AM", "Consultation");
        a1.scheduleAppointment();
        a1.generateBill();
        p1.updateTreatment("Heart Checkup");

        Appointment a2 = new Appointment("A102", p2, d2, "2025-09-01", "3PM", "Emergency");
        a2.scheduleAppointment();
        a2.generateBill();
        p2.updateTreatment("Brain MRI");

        // Cancel one appointment
        Appointment a3 = new Appointment("A103", p1, d2, "2025-09-02", "4PM", "Follow-up");
        a3.scheduleAppointment();
        a3.cancelAppointment();

        // Display details
        p1.displayInfo();
        p2.displayInfo();
        d1.displayInfo();
        d2.displayInfo();
        a1.displayInfo();
        a2.displayInfo();
        a3.displayInfo();

        // Reports
        List<Doctor> doctors = Arrays.asList(d1, d2);
        List<Patient> patients = Arrays.asList(p1, p2);

        HospitalManagement.generateHospitalReport();
        HospitalManagement.getDoctorUtilization(doctors);
        HospitalManagement.getPatientStatistics(patients);
    }
}

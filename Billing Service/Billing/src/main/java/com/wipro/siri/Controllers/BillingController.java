package com.wipro.siri.Controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.siri.Entites.Billing;
import com.wipro.siri.Services.BillingService;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    // Create a new bill (checks patient and appointment via Feign)
    @PostMapping
    public Billing createBill(@RequestBody Billing billing) {
        Billing createdBill = billingService.createBill(billing);

        // Format the billing date to only return yyyy-MM-dd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = createdBill.getBillingDate().format(formatter);
        createdBill.setBillingDate(LocalDate.parse(formattedDate));

        return createdBill;
    }

    // Get a bill by its ID
    @GetMapping("/{id}")
    public Billing getBillById(@PathVariable Long id) {
        return billingService.getBillById(id);
    }

    // Get all bills for a specific patient
    @GetMapping("/patient/{patientId}")
    public List<Billing> getBillsByPatient(@PathVariable Long patientId) {
        return billingService.getBillsByPatient(patientId);
    }

    // Get all bills for a specific appointment
    @GetMapping("/appointment/{appointmentId}")
    public List<Billing> getBillsByAppointment(@PathVariable Long appointmentId) {
        return billingService.getBillsByAppointment(appointmentId);
    }

    // Get all bills
    @GetMapping
    public List<Billing> getAllBills() {
        return billingService.getAllBills();
    }

    // Update the status of a bill (PAID, PENDING, CANCELLED)
    @PutMapping("/{id}/status/{status}")
    public Billing updateBillStatus(@PathVariable Long id, @PathVariable String status) {
        return billingService.updateBillStatus(id, status);
    }

    // Delete a bill by ID
    @DeleteMapping("/{id}")
    public void deleteBill(@PathVariable Long id) {
        billingService.deleteBill(id);
    }
}

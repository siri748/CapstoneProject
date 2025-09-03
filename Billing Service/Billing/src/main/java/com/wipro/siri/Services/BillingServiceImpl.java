package com.wipro.siri.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.siri.Entites.Billing;
import com.wipro.siri.Reposirty.BillingRepository;
import com.wipro.siri.dto.AppointmentDto;
import com.wipro.siri.dto.PatientDto;
import com.wipro.siri.feign.AppointmentClient;
import com.wipro.siri.feign.PatientClient;

@Service
public class BillingServiceImpl implements BillingService {

    private final BillingRepository billingRepository;
    private final PatientClient patientClient;
    private final AppointmentClient appointmentClient;

    public BillingServiceImpl(BillingRepository billingRepository,
                              PatientClient patientClient,
                              AppointmentClient appointmentClient) {
        this.billingRepository = billingRepository;
        this.patientClient = patientClient;
        this.appointmentClient = appointmentClient;
    }

    @Override
    public Billing createBill(Billing billing) {
        // Validate patient exists
        PatientDto patient = patientClient.getPatientById(billing.getPatientId());
        if (patient == null) throw new RuntimeException("Patient not found");

        // Validate appointment exists
        AppointmentDto appointment = appointmentClient.getAppointmentById(billing.getAppointmentId());
        if (appointment == null) throw new RuntimeException("Appointment not found");

        // Optionally, set default billing info
        billing.setStatus("PENDING");
        return billingRepository.save(billing);
    }

    @Override
    public Billing getBillById(Long id) {
        return billingRepository.findById(id).orElse(null);
    }

    @Override
    public List<Billing> getBillsByPatient(Long patientId) {
        return billingRepository.findByPatientId(patientId);
    }

    @Override
    public List<Billing> getBillsByAppointment(Long appointmentId) {
        return billingRepository.findByAppointmentId(appointmentId);
    }

    @Override
    public List<Billing> getAllBills() {
        return billingRepository.findAll();
    }

    @Override
    public Billing updateBillStatus(Long id, String status) {
        return billingRepository.findById(id).map(bill -> {
            bill.setStatus(status);
            return billingRepository.save(bill);
        }).orElse(null);
    }

    @Override
    public void deleteBill(Long id) {
        billingRepository.deleteById(id);
    }
}

import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatChipsModule } from '@angular/material/chips';
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-appointments',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatChipsModule,
    MatMenuModule
  ],
  templateUrl: './appointments.component.html',
  styleUrl: './appointments.component.scss'
})
export class AppointmentsComponent implements OnInit {
  
  displayedColumns: string[] = ['id', 'patient', 'doctor', 'date', 'time', 'type', 'status', 'actions'];
  dataSource: any[] = [];
  filteredDataSource: any[] = [];
  
  appointmentForm: FormGroup;
  isAddMode = false;
  selectedAppointment: any = null;
  searchTerm = '';

  appointmentTypes = ['Consultation', 'Follow-up', 'Emergency', 'Surgery', 'Check-up'];
  appointmentStatuses = ['Scheduled', 'Confirmed', 'In Progress', 'Completed', 'Cancelled'];

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar
  ) {
    this.appointmentForm = this.fb.group({
      patientId: ['', Validators.required],
      doctorId: ['', Validators.required],
      date: ['', Validators.required],
      time: ['', Validators.required],
      type: ['', Validators.required],
      notes: [''],
      status: ['Scheduled']
    });
  }

  ngOnInit(): void {
    this.loadAppointments();
  }

  loadAppointments(): void {
    // Mock data - replace with actual API call
    this.dataSource = [
      { id: 1, patient: 'John Doe', doctor: 'Dr. Smith', date: '2024-01-15', time: '09:00 AM', type: 'Consultation', status: 'Confirmed', notes: 'Regular checkup' },
      { id: 2, patient: 'Jane Smith', doctor: 'Dr. Johnson', date: '2024-01-15', time: '10:30 AM', type: 'Follow-up', status: 'Scheduled', notes: 'Post-surgery follow-up' },
      { id: 3, patient: 'Mike Wilson', doctor: 'Dr. Brown', date: '2024-01-15', time: '02:00 PM', type: 'Emergency', status: 'In Progress', notes: 'Chest pain' },
      { id: 4, patient: 'Sarah Davis', doctor: 'Dr. Wilson', date: '2024-01-16', time: '11:00 AM', type: 'Check-up', status: 'Scheduled', notes: 'Annual physical' }
    ];
    this.filteredDataSource = [...this.dataSource];
  }

  applyFilter(): void {
    if (!this.searchTerm.trim()) {
      this.filteredDataSource = [...this.dataSource];
      return;
    }
    
    this.filteredDataSource = this.dataSource.filter(appointment =>
      appointment.patient.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      appointment.doctor.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      appointment.type.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  openAddDialog(): void {
    this.isAddMode = true;
    this.selectedAppointment = null;
    this.appointmentForm.reset();
    this.appointmentForm.patchValue({ status: 'Scheduled' });
  }

  openEditDialog(appointment: any): void {
    this.isAddMode = false;
    this.selectedAppointment = appointment;
    this.appointmentForm.patchValue(appointment);
  }

  saveAppointment(): void {
    if (this.appointmentForm.valid) {
      const appointmentData = this.appointmentForm.value;
      
      if (this.isAddMode) {
        // Add new appointment
        const newAppointment = {
          ...appointmentData,
          id: this.dataSource.length + 1
        };
        this.dataSource.push(newAppointment);
        this.snackBar.open('Appointment scheduled successfully!', 'Close', { duration: 3000 });
      } else {
        // Update existing appointment
        const index = this.dataSource.findIndex(a => a.id === this.selectedAppointment.id);
        if (index !== -1) {
          this.dataSource[index] = { ...this.selectedAppointment, ...appointmentData };
          this.snackBar.open('Appointment updated successfully!', 'Close', { duration: 3000 });
        }
      }
      
      this.loadAppointments();
      this.closeDialog();
    }
  }

  deleteAppointment(appointment: any): void {
    if (confirm(`Are you sure you want to delete this appointment?`)) {
      const index = this.dataSource.findIndex(a => a.id === appointment.id);
      if (index !== -1) {
        this.dataSource.splice(index, 1);
        this.loadAppointments();
        this.snackBar.open('Appointment deleted successfully!', 'Close', { duration: 3000 });
      }
    }
  }

  closeDialog(): void {
    this.selectedAppointment = null;
    this.appointmentForm.reset();
  }

  getStatusColor(status: string): string {
    switch (status) {
      case 'Confirmed': return 'success';
      case 'Scheduled': return 'primary';
      case 'In Progress': return 'accent';
      case 'Completed': return 'success';
      case 'Cancelled': return 'warn';
      default: return 'primary';
    }
  }

  getTypeColor(type: string): string {
    switch (type) {
      case 'Emergency': return 'warn';
      case 'Surgery': return 'accent';
      case 'Consultation': return 'primary';
      case 'Follow-up': return 'success';
      case 'Check-up': return 'primary';
      default: return 'primary';
    }
  }
}

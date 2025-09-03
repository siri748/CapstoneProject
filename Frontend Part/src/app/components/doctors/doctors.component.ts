import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatChipsModule } from '@angular/material/chips';
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-doctors',
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
    MatChipsModule,
    MatMenuModule
  ],
  templateUrl: './doctors.component.html',
  styleUrl: './doctors.component.scss'
})
export class DoctorsComponent implements OnInit {
  
  displayedColumns: string[] = ['id', 'name', 'specialization', 'department', 'phone', 'email', 'status', 'actions'];
  dataSource: any[] = [];
  filteredDataSource: any[] = [];
  
  doctorForm: FormGroup;
  isAddMode = false;
  selectedDoctor: any = null;
  searchTerm = '';

  departments = [
    'Cardiology', 'Neurology', 'Orthopedics', 'Pediatrics', 
    'Oncology', 'Dermatology', 'Psychiatry', 'Emergency Medicine'
  ];

  specializations = [
    'Cardiologist', 'Neurologist', 'Orthopedic Surgeon', 'Pediatrician',
    'Oncologist', 'Dermatologist', 'Psychiatrist', 'Emergency Physician'
  ];

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar
  ) {
    this.doctorForm = this.fb.group({
      name: ['', Validators.required],
      specialization: ['', Validators.required],
      department: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      email: ['', [Validators.required, Validators.email]],
      licenseNumber: ['', Validators.required],
      experience: ['', [Validators.required, Validators.min(0)]],
      education: ['', Validators.required],
      address: ['']
    });
  }

  ngOnInit(): void {
    this.loadDoctors();
  }

  loadDoctors(): void {
    // Mock data - replace with actual API call
    this.dataSource = [
      { id: 1, name: 'Dr. John Smith', specialization: 'Cardiologist', department: 'Cardiology', phone: '1234567890', email: 'john.smith@hospital.com', status: 'Active', licenseNumber: 'MD12345', experience: 15, education: 'MBBS, MD Cardiology' },
      { id: 2, name: 'Dr. Sarah Johnson', specialization: 'Neurologist', department: 'Neurology', phone: '9876543210', email: 'sarah.johnson@hospital.com', status: 'Active', licenseNumber: 'MD67890', experience: 12, education: 'MBBS, MD Neurology' },
      { id: 3, name: 'Dr. Mike Brown', specialization: 'Orthopedic Surgeon', department: 'Orthopedics', phone: '5551234567', email: 'mike.brown@hospital.com', status: 'Active', licenseNumber: 'MD11111', experience: 18, education: 'MBBS, MS Orthopedics' },
      { id: 4, name: 'Dr. Emily Wilson', specialization: 'Pediatrician', department: 'Pediatrics', phone: '4449876543', email: 'emily.wilson@hospital.com', status: 'Inactive', licenseNumber: 'MD22222', experience: 8, education: 'MBBS, MD Pediatrics' }
    ];
    this.filteredDataSource = [...this.dataSource];
  }

  applyFilter(): void {
    if (!this.searchTerm.trim()) {
      this.filteredDataSource = [...this.dataSource];
      return;
    }
    
    this.filteredDataSource = this.dataSource.filter(doctor =>
      doctor.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      doctor.specialization.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      doctor.department.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      doctor.email.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  openAddDialog(): void {
    this.isAddMode = true;
    this.selectedDoctor = null;
    this.doctorForm.reset();
  }

  openEditDialog(doctor: any): void {
    this.isAddMode = false;
    this.selectedDoctor = doctor;
    this.doctorForm.patchValue(doctor);
  }

  saveDoctor(): void {
    if (this.doctorForm.valid) {
      const doctorData = this.doctorForm.value;
      
      if (this.isAddMode) {
        // Add new doctor
        const newDoctor = {
          ...doctorData,
          id: this.dataSource.length + 1,
          status: 'Active'
        };
        this.dataSource.push(newDoctor);
        this.snackBar.open('Doctor added successfully!', 'Close', { duration: 3000 });
      } else {
        // Update existing doctor
        const index = this.dataSource.findIndex(d => d.id === this.selectedDoctor.id);
        if (index !== -1) {
          this.dataSource[index] = { ...this.selectedDoctor, ...doctorData };
          this.snackBar.open('Doctor updated successfully!', 'Close', { duration: 3000 });
        }
      }
      
      this.loadDoctors();
      this.closeDialog();
    }
  }

  deleteDoctor(doctor: any): void {
    if (confirm(`Are you sure you want to delete ${doctor.name}?`)) {
      const index = this.dataSource.findIndex(d => d.id === doctor.id);
      if (index !== -1) {
        this.dataSource.splice(index, 1);
        this.loadDoctors();
        this.snackBar.open('Doctor deleted successfully!', 'Close', { duration: 3000 });
      }
    }
  }

  closeDialog(): void {
    this.selectedDoctor = null;
    this.doctorForm.reset();
  }

  getStatusColor(status: string): string {
    return status === 'Active' ? 'success' : 'warn';
  }

  getDepartmentColor(department: string): string {
    const colors = ['primary', 'accent', 'warn', 'success'];
    const index = this.departments.indexOf(department);
    return colors[index % colors.length];
  }
}

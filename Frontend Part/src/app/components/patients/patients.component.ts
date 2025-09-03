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
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { MatPaginatorModule, MatPaginator } from '@angular/material/paginator';
import { MatSortModule, MatSort } from '@angular/material/sort';
import { MatChipsModule } from '@angular/material/chips';
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBarModule, MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-patients',
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
    MatDialogModule,
    MatPaginatorModule,
    MatSortModule,
    MatChipsModule,
    MatMenuModule,
    MatSnackBarModule
  ],
  templateUrl: './patients.component.html',
  styleUrl: './patients.component.scss'
})
export class PatientsComponent implements OnInit {
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  displayedColumns: string[] = ['id', 'name', 'age', 'gender', 'phone', 'email', 'status', 'actions'];
  dataSource: any[] = [];
  filteredDataSource: any[] = [];
  
  patientForm: FormGroup;
  isAddMode = false;
  selectedPatient: any = null;
  searchTerm = '';

  constructor(
    private fb: FormBuilder,
    private dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {
    this.patientForm = this.fb.group({
      name: ['', Validators.required],
      age: ['', [Validators.required, Validators.min(0), Validators.max(150)]],
      gender: ['', Validators.required],
      phone: ['', [Validators.required, Validators.pattern('^[0-9]{10}$')]],
      email: ['', [Validators.required, Validators.email]],
      address: ['', Validators.required],
      bloodGroup: [''],
      emergencyContact: [''],
      medicalHistory: ['']
    });
  }

  ngOnInit(): void {
    this.loadPatients();
  }

  loadPatients(): void {
    // Mock data - replace with actual API call
    this.dataSource = [
      { id: 1, name: 'John Doe', age: 35, gender: 'Male', phone: '1234567890', email: 'john@example.com', status: 'Active', bloodGroup: 'O+', address: '123 Main St' },
      { id: 2, name: 'Jane Smith', age: 28, gender: 'Female', phone: '9876543210', email: 'jane@example.com', status: 'Active', bloodGroup: 'A+', address: '456 Oak Ave' },
      { id: 3, name: 'Mike Wilson', age: 42, gender: 'Male', phone: '5551234567', email: 'mike@example.com', status: 'Inactive', bloodGroup: 'B+', address: '789 Pine Rd' },
      { id: 4, name: 'Sarah Davis', age: 31, gender: 'Female', phone: '4449876543', email: 'sarah@example.com', status: 'Active', bloodGroup: 'AB+', address: '321 Elm St' }
    ];
    this.filteredDataSource = [...this.dataSource];
  }

  applyFilter(): void {
    if (!this.searchTerm.trim()) {
      this.filteredDataSource = [...this.dataSource];
      return;
    }
    
    this.filteredDataSource = this.dataSource.filter(patient =>
      patient.name.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      patient.email.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      patient.phone.includes(this.searchTerm)
    );
  }

  openAddDialog(): void {
    this.isAddMode = true;
    this.selectedPatient = null;
    this.patientForm.reset();
  }

  openEditDialog(patient: any): void {
    this.isAddMode = false;
    this.selectedPatient = patient;
    this.patientForm.patchValue(patient);
  }

  savePatient(): void {
    if (this.patientForm.valid) {
      const patientData = this.patientForm.value;
      
      if (this.isAddMode) {
        // Add new patient
        const newPatient = {
          ...patientData,
          id: this.dataSource.length + 1,
          status: 'Active'
        };
        this.dataSource.push(newPatient);
        this.snackBar.open('Patient added successfully!', 'Close', { duration: 3000 });
      } else {
        // Update existing patient
        const index = this.dataSource.findIndex(p => p.id === this.selectedPatient.id);
        if (index !== -1) {
          this.dataSource[index] = { ...this.selectedPatient, ...patientData };
          this.snackBar.open('Patient updated successfully!', 'Close', { duration: 3000 });
        }
      }
      
      this.loadPatients();
      this.closeDialog();
    }
  }

  deletePatient(patient: any): void {
    if (confirm(`Are you sure you want to delete ${patient.name}?`)) {
      const index = this.dataSource.findIndex(p => p.id === patient.id);
      if (index !== -1) {
        this.dataSource.splice(index, 1);
        this.loadPatients();
        this.snackBar.open('Patient deleted successfully!', 'Close', { duration: 3000 });
      }
    }
  }

  closeDialog(): void {
    this.selectedPatient = null;
    this.patientForm.reset();
  }

  getStatusColor(status: string): string {
    return status === 'Active' ? 'success' : 'warn';
  }
}

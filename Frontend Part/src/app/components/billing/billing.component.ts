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
import { MatChipsModule } from '@angular/material/chips';
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-billing',
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
  templateUrl: './billing.component.html',
  styleUrl: './billing.component.scss'
})
export class BillingComponent implements OnInit {
  
  displayedColumns: string[] = ['id', 'patient', 'services', 'amount', 'status', 'date', 'actions'];
  dataSource: any[] = [];
  filteredDataSource: any[] = [];
  
  billingForm: FormGroup;
  isAddMode = false;
  selectedBill: any = null;
  searchTerm = '';

  paymentStatuses = ['Pending', 'Paid', 'Overdue', 'Cancelled'];
  serviceTypes = ['Consultation', 'Laboratory', 'Radiology', 'Surgery', 'Medication', 'Room Charges'];

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar
  ) {
    this.billingForm = this.fb.group({
      patientId: ['', Validators.required],
      services: ['', Validators.required],
      amount: ['', [Validators.required, Validators.min(0)]],
      status: ['Pending'],
      dueDate: [''],
      notes: ['']
    });
  }

  ngOnInit(): void {
    this.loadBills();
  }

  loadBills(): void {
    // Mock data - replace with actual API call
    this.dataSource = [
      { id: 1, patient: 'John Doe', services: 'Consultation, Laboratory', amount: 250.00, status: 'Paid', date: '2024-01-15', dueDate: '2024-01-15', notes: 'Regular checkup and blood tests' },
      { id: 2, patient: 'Jane Smith', services: 'Surgery, Room Charges', amount: 2500.00, status: 'Pending', date: '2024-01-14', dueDate: '2024-01-21', notes: 'Appendectomy surgery' },
      { id: 3, patient: 'Mike Wilson', services: 'Radiology, Consultation', amount: 180.00, status: 'Overdue', date: '2024-01-10', dueDate: '2024-01-10', notes: 'X-ray and consultation' },
      { id: 4, patient: 'Sarah Davis', services: 'Medication, Follow-up', amount: 95.00, status: 'Paid', date: '2024-01-12', dueDate: '2024-01-12', notes: 'Prescription refill' }
    ];
    this.filteredDataSource = [...this.dataSource];
  }

  applyFilter(): void {
    if (!this.searchTerm.trim()) {
      this.filteredDataSource = [...this.dataSource];
      return;
    }
    
    this.filteredDataSource = this.dataSource.filter(bill =>
      bill.patient.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      bill.services.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
      bill.status.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }

  openAddDialog(): void {
    this.isAddMode = true;
    this.selectedBill = null;
    this.billingForm.reset();
    this.billingForm.patchValue({ status: 'Pending' });
  }

  openEditDialog(bill: any): void {
    this.isAddMode = false;
    this.selectedBill = bill;
    this.billingForm.patchValue(bill);
  }

  saveBill(): void {
    if (this.billingForm.valid) {
      const billData = this.billingForm.value;
      
      if (this.isAddMode) {
        // Add new bill
        const newBill = {
          ...billData,
          id: this.dataSource.length + 1,
          date: new Date().toISOString().split('T')[0]
        };
        this.dataSource.push(newBill);
        this.snackBar.open('Bill generated successfully!', 'Close', { duration: 3000 });
      } else {
        // Update existing bill
        const index = this.dataSource.findIndex(b => b.id === this.selectedBill.id);
        if (index !== -1) {
          this.dataSource[index] = { ...this.selectedBill, ...billData };
          this.snackBar.open('Bill updated successfully!', 'Close', { duration: 3000 });
        }
      }
      
      this.loadBills();
      this.closeDialog();
    }
  }

  deleteBill(bill: any): void {
    if (confirm(`Are you sure you want to delete this bill?`)) {
      const index = this.dataSource.findIndex(b => b.id === bill.id);
      if (index !== -1) {
        this.dataSource.splice(index, 1);
        this.loadBills();
        this.snackBar.open('Bill deleted successfully!', 'Close', { duration: 3000 });
      }
    }
  }

  closeDialog(): void {
    this.selectedBill = null;
    this.billingForm.reset();
  }

  getStatusColor(status: string): string {
    switch (status) {
      case 'Paid': return 'success';
      case 'Pending': return 'primary';
      case 'Overdue': return 'warn';
      case 'Cancelled': return 'warn';
      default: return 'primary';
    }
  }

  generateInvoice(bill: any): void {
    // Mock invoice generation
    this.snackBar.open(`Invoice generated for ${bill.patient}`, 'Close', { duration: 3000 });
  }

  markAsPaid(bill: any): void {
    const index = this.dataSource.findIndex(b => b.id === bill.id);
    if (index !== -1) {
      this.dataSource[index].status = 'Paid';
      this.loadBills();
      this.snackBar.open(`Payment recorded for ${bill.patient}`, 'Close', { duration: 3000 });
    }
  }
}

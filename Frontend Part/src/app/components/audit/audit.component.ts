import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatChipsModule } from '@angular/material/chips';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';

@Component({
  selector: 'app-audit',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatSelectModule,
    MatChipsModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  templateUrl: './audit.component.html',
  styleUrl: './audit.component.scss'
})
export class AuditComponent implements OnInit {
  
  displayedColumns: string[] = ['timestamp', 'user', 'action', 'module', 'details', 'ipAddress', 'status'];
  dataSource: any[] = [];
  filteredDataSource: any[] = [];
  
  searchTerm = '';
  selectedModule = '';
  selectedStatus = '';
  startDate: Date | null = null;
  endDate: Date | null = null;

  modules = ['All', 'Patients', 'Doctors', 'Appointments', 'Billing', 'System', 'Authentication'];
  statuses = ['All', 'Success', 'Failed', 'Warning', 'Info'];

  constructor() { }

  ngOnInit(): void {
    this.loadAuditLogs();
  }

  loadAuditLogs(): void {
    // Mock data - replace with actual API call
    this.dataSource = [
      { timestamp: '2024-01-15 09:30:15', user: 'admin@hospital.com', action: 'Login', module: 'Authentication', details: 'User logged in successfully', ipAddress: '192.168.1.100', status: 'Success' },
      { timestamp: '2024-01-15 09:35:22', user: 'admin@hospital.com', action: 'Create Patient', module: 'Patients', details: 'New patient John Doe added', ipAddress: '192.168.1.100', status: 'Success' },
      { timestamp: '2024-01-15 10:15:45', user: 'doctor.smith@hospital.com', action: 'View Patient Records', module: 'Patients', details: 'Accessed patient records for Jane Smith', ipAddress: '192.168.1.101', status: 'Success' },
      { timestamp: '2024-01-15 11:20:33', user: 'nurse.johnson@hospital.com', action: 'Update Appointment', module: 'Appointments', details: 'Modified appointment time for Mike Wilson', ipAddress: '192.168.1.102', status: 'Success' },
      { timestamp: '2024-01-15 14:05:18', user: 'admin@hospital.com', action: 'Delete Record', module: 'System', details: 'Attempted to delete system configuration', ipAddress: '192.168.1.100', status: 'Failed' },
      { timestamp: '2024-01-15 16:30:55', user: 'billing@hospital.com', action: 'Generate Invoice', module: 'Billing', details: 'Invoice #INV-001 generated for Sarah Davis', ipAddress: '192.168.1.103', status: 'Success' }
    ];
    this.filteredDataSource = [...this.dataSource];
  }

  applyFilter(): void {
    let filtered = [...this.dataSource];

    // Text search
    if (this.searchTerm.trim()) {
      filtered = filtered.filter(log =>
        log.user.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        log.action.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        log.details.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
        log.ipAddress.includes(this.searchTerm)
      );
    }

    // Module filter
    if (this.selectedModule && this.selectedModule !== 'All') {
      filtered = filtered.filter(log => log.module === this.selectedModule);
    }

    // Status filter
    if (this.selectedStatus && this.selectedStatus !== 'All') {
      filtered = filtered.filter(log => log.status === this.selectedStatus);
    }

    // Date range filter
    if (this.startDate) {
      filtered = filtered.filter(log => {
        const logDate = new Date(log.timestamp);
        return logDate >= this.startDate!;
      });
    }

    if (this.endDate) {
      filtered = filtered.filter(log => {
        const logDate = new Date(log.timestamp);
        return logDate <= this.endDate!;
      });
    }

    this.filteredDataSource = filtered;
  }

  clearFilters(): void {
    this.searchTerm = '';
    this.selectedModule = '';
    this.selectedStatus = '';
    this.startDate = null;
    this.endDate = null;
    this.filteredDataSource = [...this.dataSource];
  }

  exportLogs(): void {
    // Mock export functionality
    const csvContent = this.convertToCSV(this.filteredDataSource);
    const blob = new Blob([csvContent], { type: 'text/csv' });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'audit_logs.csv';
    a.click();
    window.URL.revokeObjectURL(url);
  }

  private convertToCSV(data: any[]): string {
    const headers = ['Timestamp', 'User', 'Action', 'Module', 'Details', 'IP Address', 'Status'];
    const csvRows = [headers.join(',')];
    
    data.forEach(row => {
      const values = [
        row.timestamp,
        row.user,
        row.action,
        row.module,
        `"${row.details}"`,
        row.ipAddress,
        row.status
      ];
      csvRows.push(values.join(','));
    });
    
    return csvRows.join('\n');
  }

  getStatusColor(status: string): string {
    switch (status) {
      case 'Success': return 'success';
      case 'Failed': return 'warn';
      case 'Warning': return 'accent';
      case 'Info': return 'primary';
      default: return 'primary';
    }
  }

  getModuleColor(module: string): string {
    const colors = ['primary', 'accent', 'warn', 'success'];
    const index = this.modules.indexOf(module);
    return colors[index % colors.length];
  }
}

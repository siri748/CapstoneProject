import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatChipsModule } from '@angular/material/chips';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatIconModule,
    MatButtonModule,
    MatTableModule,
    MatChipsModule,
    MatProgressBarModule,
    MatDividerModule
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  
  stats = [
    { title: 'Total Patients', value: '1,247', icon: 'people', color: 'primary', change: '+12%' },
    { title: 'Active Doctors', value: '89', icon: 'medical_services', color: 'accent', change: '+5%' },
    { title: 'Today\'s Appointments', value: '156', icon: 'event', color: 'warn', change: '+23%' },
    { title: 'Revenue (Monthly)', value: '$45,678', icon: 'attach_money', color: 'success', change: '+18%' }
  ];

  recentAppointments = [
    { patient: 'John Doe', doctor: 'Dr. Smith', time: '09:00 AM', status: 'Confirmed' },
    { patient: 'Jane Smith', doctor: 'Dr. Johnson', time: '10:30 AM', status: 'Pending' },
    { patient: 'Mike Wilson', doctor: 'Dr. Brown', time: '02:00 PM', status: 'Confirmed' },
    { patient: 'Sarah Davis', doctor: 'Dr. Wilson', time: '03:30 PM', status: 'Cancelled' }
  ];

  departmentStats = [
    { name: 'Cardiology', patients: 45, occupancy: 85 },
    { name: 'Neurology', patients: 32, occupancy: 72 },
    { name: 'Orthopedics', patients: 28, occupancy: 68 },
    { name: 'Pediatrics', patients: 38, occupancy: 78 }
  ];

  constructor() { }

  ngOnInit(): void { }

  getStatusColor(status: string): string {
    switch (status) {
      case 'Confirmed': return 'success';
      case 'Pending': return 'warning';
      case 'Cancelled': return 'danger';
      default: return 'primary';
    }
  }
}

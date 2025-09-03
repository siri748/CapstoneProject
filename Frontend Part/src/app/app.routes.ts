import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/dashboard',
    pathMatch: 'full'
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./components/dashboard/dashboard.component').then(m => m.DashboardComponent)
  },
  {
    path: 'patients',
    loadComponent: () => import('./components/patients/patients.component').then(m => m.PatientsComponent)
  },
  {
    path: 'doctors',
    loadComponent: () => import('./components/doctors/doctors.component').then(m => m.DoctorsComponent)
  },
  {
    path: 'appointments',
    loadComponent: () => import('./components/appointments/appointments.component').then(m => m.AppointmentsComponent)
  },
  {
    path: 'billing',
    loadComponent: () => import('./components/billing/billing.component').then(m => m.BillingComponent)
  },
  {
    path: 'audit',
    loadComponent: () => import('./components/audit/audit.component').then(m => m.AuditComponent)
  },
  {
    path: 'auth',
    loadComponent: () => import('./components/auth/auth.component').then(m => m.AuthComponent)
  },
  {
    path: '**',
    redirectTo: '/dashboard'
  }
];

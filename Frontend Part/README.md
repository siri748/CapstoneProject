# Hospital Management System - Frontend

A modern, responsive Angular frontend application for hospital management systems built with Angular Material and Bootstrap.

## 🚀 Features

- **Dashboard**: Overview with statistics, recent appointments, and department information
- **Patient Management**: CRUD operations for patient records
- **Doctor Management**: Doctor profiles, specializations, and department assignments
- **Appointment Scheduling**: Appointment booking and management system
- **Billing System**: Invoice generation and payment tracking
- **Audit Logs**: System activity monitoring and logging
- **Authentication**: Secure login and user management
- **Responsive Design**: Mobile-first approach with Bootstrap integration
- **Material Design**: Modern UI components from Angular Material

## 🛠️ Technology Stack

- **Angular 17**: Latest version with standalone components
- **Angular Material**: Material Design components
- **Bootstrap 5**: Responsive CSS framework
- **TypeScript**: Type-safe JavaScript
- **SCSS**: Advanced CSS preprocessing
- **RxJS**: Reactive programming library

## 📋 Prerequisites

- Node.js (v18 or higher)
- npm (v9 or higher)
- Angular CLI (v17 or higher)

## 🚀 Installation & Setup

### 1. Clone the Repository
```bash
git clone <repository-url>
cd hospital-management-frontend
```

### 2. Install Dependencies
```bash
npm install
```

### 3. Install Angular Material
```bash
ng add @angular/material
```

### 4. Install Bootstrap
```bash
npm install bootstrap @popperjs/core
```

### 5. Start Development Server
```bash
ng serve
```

The application will be available at `http://localhost:4200`

## 🏗️ Project Structure

```
src/
├── app/
│   ├── components/
│   │   ├── dashboard/          # Dashboard component
│   │   ├── patients/           # Patient management
│   │   ├── doctors/            # Doctor management
│   │   ├── appointments/       # Appointment system
│   │   ├── billing/            # Billing and invoicing
│   │   ├── audit/              # Audit logs
│   │   └── auth/               # Authentication
│   ├── app.component.ts        # Main app component
│   ├── app.component.html      # Main app template
│   ├── app.component.scss      # Main app styles
│   ├── app.config.ts           # App configuration
│   └── app.routes.ts           # Routing configuration
├── styles.scss                 # Global styles
└── index.html                  # Main HTML file
```

## 🎨 Component Details

### Dashboard Component
- Statistics cards with real-time data
- Recent appointments table
- Department overview with progress bars
- Quick action buttons

### Patients Component
- Patient listing with search and filter
- Add/Edit patient forms
- Patient status management
- Responsive data table

### Doctors Component
- Doctor profiles and specializations
- Department assignments
- License and experience tracking
- Status management

### Appointments Component
- Appointment scheduling
- Date and time pickers
- Status tracking
- Patient and doctor selection

### Billing Component
- Invoice generation
- Payment status tracking
- Service categorization
- Amount calculations

### Audit Component
- System activity logs
- User action tracking
- Filtering and search
- Export functionality

### Auth Component
- User login and registration
- Form validation
- Password management
- Role-based access

## 🔧 Configuration

### Angular Material Theme
The application uses the default Indigo-Pink theme. To customize:

1. Update `styles.scss`:
```scss
@import '@angular/material/prebuilt-themes/your-theme.css';
```

2. Available themes:
- `indigo-pink.css`
- `deeppurple-amber.css`
- `pink-bluegrey.css`
- `purple-green.css`

### Bootstrap Customization
Customize Bootstrap variables in `styles.scss`:
```scss
$primary: #1976d2;
$secondary: #ff4081;
$success: #4caf50;
$warning: #ff9800;
$danger: #f44336;
```

## 📱 Responsive Design

The application is built with a mobile-first approach:

- **Mobile**: Single column layout, collapsible sidebar
- **Tablet**: Two-column layout, expanded sidebar
- **Desktop**: Full layout with persistent sidebar

### Breakpoints
- Mobile: < 768px
- Tablet: 768px - 1024px
- Desktop: > 1024px

## 🚀 Building for Production

### Build Command
```bash
ng build --configuration production
```

### Build Output
The build artifacts will be stored in the `dist/hospital-management-frontend/` directory.

### Environment Configuration
Create environment files for different configurations:

```typescript
// environment.ts
export const environment = {
  production: false,
  apiUrl: 'http://localhost:8080/api'
};

// environment.prod.ts
export const environment = {
  production: true,
  apiUrl: 'https://your-api-domain.com/api'
};
```

## 🧪 Testing

### Unit Tests
```bash
ng test
```

### E2E Tests
```bash
ng e2e
```

### Code Coverage
```bash
ng test --code-coverage
```

## 📦 Deployment

### Static Hosting (Netlify, Vercel, GitHub Pages)
1. Build the application: `ng build --configuration production`
2. Deploy the `dist/hospital-management-frontend` folder

### Docker Deployment
```dockerfile
FROM nginx:alpine
COPY dist/hospital-management-frontend /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
```

### Apache/Nginx Configuration
```nginx
location / {
    try_files $uri $uri/ /index.html;
}
```

## 🔒 Security Features

- Form validation and sanitization
- XSS protection
- CSRF token support
- Secure authentication
- Role-based access control

## 📊 Performance Optimization

- Lazy loading of components
- OnPush change detection strategy
- Bundle size optimization
- Image optimization
- Service worker support (PWA ready)

## 🐛 Troubleshooting

### Common Issues

1. **Angular Material Icons Not Loading**
   - Ensure Material Icons font is loaded in `index.html`
   - Check network connectivity for Google Fonts

2. **Bootstrap Styles Not Applied**
   - Verify Bootstrap CSS is imported in `angular.json`
   - Check for CSS conflicts

3. **Build Errors**
   - Clear node_modules and reinstall: `rm -rf node_modules && npm install`
   - Update Angular CLI: `npm update -g @angular/cli`

4. **Routing Issues**
   - Ensure base href is set correctly in `index.html`
   - Check server configuration for SPA routing

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Contact the development team
- Check the documentation

## 🔄 Updates and Maintenance

### Regular Updates
- Keep Angular and dependencies updated
- Monitor security vulnerabilities
- Update Material Design components
- Maintain Bootstrap compatibility

### Performance Monitoring
- Use Angular DevTools for debugging
- Monitor bundle sizes
- Track Core Web Vitals
- Optimize based on user feedback

---

**Note**: This is a frontend application. You'll need to connect it to your backend API services for full functionality. Update the API endpoints in the services to match your backend configuration.

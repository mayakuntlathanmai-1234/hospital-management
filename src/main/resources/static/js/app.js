const API_BASE = "http://localhost:8080/api";

// --- LOGIN LOGIC ---
if (document.getElementById('loginForm')) {
    document.getElementById('loginForm').addEventListener('submit', (e) => {
        e.preventDefault();
        const user = document.getElementById('username').value;
        const pass = document.getElementById('password').value;
        
        if (user === "admin" && pass === "admin123") {
            localStorage.setItem('hospitalSession', 'active');
            window.location.href = "dashboard.html";
        } else {
            document.getElementById('errorMsg').style.display = "block";
        }
    });
}

function logout() {
    localStorage.removeItem('hospitalSession');
    window.location.href = "index.html";
}

// --- DASHBOARD DATA ---
async function updateDashboardCounts() {
    try {
        const patients = await fetch(`${API_BASE}/patients`).then(r => r.json());
        const doctors = await fetch(`${API_BASE}/doctors`).then(r => r.json());
        const apps = await fetch(`${API_BASE}/appointments`).then(r => r.json());

        document.getElementById('countPatients').innerText = patients.length;
        document.getElementById('countDoctors').innerText = doctors.length;
        document.getElementById('countAppointments').innerText = apps.length;
    } catch (e) { console.error("API Error", e); }
}

// --- PATIENT LOGIC ---
async function loadPatients() {
    const res = await fetch(`${API_BASE}/patients`);
    const data = await res.json();
    const tbody = document.querySelector('#patientTable tbody');
    tbody.innerHTML = data.map(p => `<tr><td>${p.id}</td><td>${p.name}</td><td>${p.age}</td><td>${p.disease}</td></tr>`).join('');
}

async function handlePatientSubmit(e) {
    e.preventDefault();
    const patient = {
        name: document.getElementById('pName').value,
        age: document.getElementById('pAge').value,
        disease: document.getElementById('pDisease').value
    };
    await fetch(`${API_BASE}/patients`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(patient)
    });
    alert("Patient Added Successfully!");
    loadPatients();
}

// --- DOCTOR LOGIC ---
async function loadDoctors() {
    const res = await fetch(`${API_BASE}/doctors`);
    const data = await res.json();
    const list = document.getElementById('doctorList');
    list.innerHTML = data.map(d => `
        <div class="card glass-card">
            <i class="fas fa-user-md fa-3x" style="color: #007bff;"></i>
            <h3 style="margin-top:10px;">${d.name}</h3>
            <p>${d.specialization}</p>
        </div>
    `).join('');
}

async function handleDoctorSubmit(e) {
    e.preventDefault();
    const doctor = {
        name: document.getElementById('dName').value,
        specialization: document.getElementById('dSpecial').value
    };
    await fetch(`${API_BASE}/doctors`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(doctor)
    });
    alert("Doctor Added Successfully!");
    loadDoctors();
}

// --- APPOINTMENT LOGIC ---
async function loadAppointmentSelections() {
    const ps = await fetch(`${API_BASE}/patients`).then(r => r.json());
    const ds = await fetch(`${API_BASE}/doctors`).then(r => r.json());
    
    document.getElementById('pSelect').innerHTML = ps.map(p => `<option value="${p.id}">${p.name}</option>`).join('');
    document.getElementById('dSelect').innerHTML = ds.map(d => `<option value="${d.id}">${d.name}</option>`).join('');
}

async function loadAppointments() {
    const res = await fetch(`${API_BASE}/appointments`);
    const data = await res.json();
    const tbody = document.querySelector('#appointmentTable tbody');
    tbody.innerHTML = data.map(a => `<tr><td>${a.id}</td><td>${a.patient.name}</td><td>${a.doctor.name}</td><td>${a.appointmentDate}</td></tr>`).join('');
}

async function handleAppointmentSubmit(e) {
    e.preventDefault();
    const app = {
        patient: { id: document.getElementById('pSelect').value },
        doctor: { id: document.getElementById('dSelect').value },
        appointmentDate: document.getElementById('aDate').value
    };
    await fetch(`${API_BASE}/appointments`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(app)
    });
    alert("Appointment Booked!");
    loadAppointments();
}

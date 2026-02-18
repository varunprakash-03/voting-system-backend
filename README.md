
---

# 🖥️ 2️⃣ BACKEND README (Spring Boot)

```markdown
# 🚀 Face Biometric Voting System – Backend

A secure Spring Boot backend server that handles authentication, vote management, and system operations for the biometric voting platform.

---

## 🚀 Features

- 🔐 JWT Authentication
- 👥 Role-Based Access (Admin / Voter)
- 🗳️ Secure Vote Casting API
- 📊 Result Management
- 🛡️ Input Validation & Security
- 🗄️ Database Integration

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Security
- JWT
- PostgreSQL / MySQL
- Maven

---

## 📂 Project Structure

src/main/java/
├── controller/
├── service/
├── repository/
├── model/
└── config/


---

## ⚙️ Setup & Installation

### 1️⃣ Clone Repository

```bash
git clone https://github.com/your-username/voting-system-backend.git
cd voting-system-backend
2️⃣ Configure Database
Update application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/voting_db
spring.datasource.username=your_username
spring.datasource.password=your_password

jwt.secret=your_secret_key
3️⃣ Run Application
./mvnw spring-boot:run
Server runs on:

http://localhost:8080
📡 API Endpoints
Method	Endpoint	Description
POST	/api/auth/login	Authenticate user
GET	/api/voter/{id}	Get voter info
POST	/api/vote	Cast vote
GET	/api/admin/results	View results
🔐 Security
BCrypt Password Encryption

JWT Token Validation

CORS Configuration

Protected Admin APIs

🧪 Testing
Use Postman or Swagger UI.

👨‍💻 Author
Varun Prakash
Backend Developer | Java & Spring Boot

📜 License
Academic / Learning Purpose


---

# 🎓 Online Examination System (JavaFX)

A comprehensive, desktop-based Student Management and Online Examination System developed using **JavaFX** and **MySQL**. This application allows educational institutes to manage exams, questions, and students efficiently while providing a secure environment for students to take exams with real-time timers.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/JavaFX-4285F4?style=for-the-badge&logo=java&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

---

## 🚀 Features

### 👨‍🎓 Student Portal
* **Secure Login:** Role-based authentication for students.
* **Dashboard:** View available exams and profile details.
* **Real-time Exam Interface:**
    * Countdown timer (auto-submit when time is up).
    * Dynamic loading of questions (MCQ) from the database.
    * "Save & Next" functionality to record answers.
    * Navigation between questions.

### 👩‍🏫 Teacher/Admin Portal
* **Exam Management:** Create and schedule new exams.
* **Question Bank:** Add multiple-choice questions (MCQs) to specific exams.
* **Results:** View student performance and marks.

### 🛠 Technical Highlights
* **MVC Architecture:** Clean separation of concern using Model-View-Controller pattern.
* **Dockerized Database:** MySQL database runs in a container for easy setup.
* **Persistent Data:** All users, exams, and results are stored in a MySQL database.
* **Responsive UI:** Built with JavaFX and SceneBuilder.

---

## 🛠️ Technologies Used

* **Programming Language:** Java (JDK 21)
* **UI Framework:** JavaFX
* **Database:** MySQL 8.0
* **Containerization:** Docker & Docker Compose
* **IDE:** IntelliJ IDEA
* **Build Tool:** Maven

---

## ⚙️ Setup & Installation

Follow these steps to set up the project locally.

### Prerequisites
* [Java JDK 21](https://www.oracle.com/java/technologies/downloads/) installed.
* [Docker Desktop](https://www.docker.com/products/docker-desktop/) installed and running.
* [IntelliJ IDEA](https://www.jetbrains.com/idea/) (Recommended).

### 1. Clone the Repository
```bash
git clone [https://github.com/ChalanaDilshan/Online-Exam-System-JavaFX.git](https://github.com/ChalanaDilshan/Online-Exam-System-JavaFX.git)
cd Online-Exam-System-JavaFX

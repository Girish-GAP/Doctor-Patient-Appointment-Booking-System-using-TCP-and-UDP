# Doctor-Patient Appointment Booking System using TCP and UDP

## Table of Contents

1. [Introduction](#introduction)
2. [What is TCP?](#what-is-tcp)
3. [What is UDP?](#what-is-udp)
4. [Project Overview](#project-overview)
5. [How the Project Works](#how-the-project-works)
6. [How to Run the Project](#how-to-run-the-project)
7. [Example Usage](#example-usage)
8. [Conclusion](#conclusion)

## Introduction

This project simulates a doctor-patient appointment booking system where the patient sends appointment details to the doctor using TCP (Transmission Control Protocol) and receives confirmation or rejection using UDP (User Datagram Protocol). The system demonstrates how TCP and UDP can be used together in client-server communication.

## What is TCP?

**TCP (Transmission Control Protocol)** is a connection-oriented protocol used to establish reliable communication between two devices over a network. TCP ensures that the data sent is received in the same order it was transmitted, without any loss, duplication, or corruption of data. It uses a three-way handshake process to establish the connection before data is transferred.

- **Key Characteristics of TCP:**
  - Reliable data transfer (ensures that packets are delivered and reassembled correctly).
  - Connection-oriented (requires the establishment of a connection before data transfer).
  - Error checking and acknowledgment of received data.
  - Slower but more reliable than UDP.

## What is UDP?

**UDP (User Datagram Protocol)** is a connectionless protocol that allows sending packets of data without establishing a connection. It does not guarantee the delivery, order, or error correction of data. UDP is suitable for applications where speed is more critical than reliability (e.g., real-time applications like gaming or video streaming).

- **Key Characteristics of UDP:**
  - Fast, connectionless communication.
  - No guarantee of data delivery or order.
  - No error recovery or acknowledgment.
  - Lightweight and efficient for certain use cases.

## Project Overview

In this project, we demonstrate the use of both TCP and UDP in a doctor-patient system.

- **TCP:** The patient uses TCP to send appointment details (like name, date, and time) to the doctor.
- **UDP:** The doctor uses UDP to respond with either an appointment confirmation or a denial.

### Components:

1. **DoctorTCPServer.java:** A TCP server that listens for appointment details from the patient.
2. **PatientTCPClient.java:** A TCP client that sends appointment details to the doctor.
3. **DoctorUDPServer.java:** A UDP server that sends appointment confirmation or rejection.
4. **PatientUDPClient.java:** A UDP client that listens for the doctor's confirmation.

## How the Project Works

1. **TCP Communication:**

   - The patient sends appointment details (name, date, time) using **TCP** to the doctor’s TCP server.
   - The doctor receives the details, and based on the input, decides to either accept or deny the appointment.

2. **UDP Communication:**
   - After processing the request, the doctor uses **UDP** to send a response (confirmation or denial).
   - The patient’s UDP client receives the doctor’s response.

## How to Run the Project

### Prerequisites:

- Java JDK installed (preferably version 8 or above).
- A terminal or command prompt to compile and run the Java files.

### Steps to run the project:

1. **Compile the Java Files:**
   Open a terminal or command prompt in the project directory and compile the server and client files:

   ```bash
   javac DoctorTCPServer.java PatientTCPClient.java DoctorUDPServer.java PatientUDPClient.java
   ```

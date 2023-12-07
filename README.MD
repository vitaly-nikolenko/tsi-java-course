# Student Manager Documentation

The **StudentManager** is a Java console application that allows users to manage a list of students using the `StudentService` class. Users can perform various actions such as adding students, listing students, searching for students by name, deleting students, and saving/loading the student list to/from a file.

## Table of Contents
- [Usage](#usage)
    - [Adding a Student](#adding-a-student)
    - [Listing Students](#listing-students)
    - [Searching for Students](#searching-for-students)
    - [Deleting a Student](#deleting-a-student)
    - [Saving to File](#saving-to-file)
    - [Loading from File](#loading-from-file)
    - [Exiting the Program](#exiting-the-program)
- [Project Structure Documentation](#project-structure-documentation)
  - [Directory Structure](#directory-structure)
  - [Explanation of Directories and Files](#explanation-of-directories-and-files)


## Usage

Explanation-of-Directories-and-Files

### Adding a Student
1. Choose option 1: **Add Student** from the menu.
2. Enter the student's name when prompted.
3. Enter the student's email address when prompted.
4. Enter the student's date of birth in the format YYYY-MM-DD when prompted.

### Listing Students
1. Choose option 2: **List Students** from the menu.

### Searching for Students
1. Choose option 3: **Search Student by Name** from the menu.
2. Enter the name of the student you want to search for.

### Deleting a Student
1. Choose option 4: **Delete student by it** from the menu.
2. Enter the student id to delete.

### Saving to File
1. Choose option 5: **Save Student List to File** from the menu.
2. Enter the desired file name to save the student list.

### Loading from File
1. Choose option 6: **Load Student List from File** from the menu.
2. Enter the file name to load the student list from.

### Exiting the Program
1. Choose option 7: **Exit** from the menu.

# Project Structure Documentation

This document provides an overview of the project structure for the **Student Management System**. The project follows a typical Java project structure with packages and classes organized in a hierarchical manner.

## Directory Structure

```plaintext
StudentManagementSystem
|-- src
|   |-- lv.tsi
|       |-- student
|           |-- model
|           |   |-- DuplicateRecordException.java
|           |   |-- Identity.java
|           |   |-- Student.java
|           |
|           |-- repository
|           |   |-- Repository.java
|           |   |-- StudentRepository.java
|           |
|           |-- service
|           |   |-- StudentService.java
|           |
|           |-- StudentManager.java
|
|-- test
|   |-- lv.tsi
|       |-- student
|           |-- repository
|           |   |-- StudentRepositoryTest.java
|           |
|           |-- service
|               |-- StudentServiceTest.java
|
|-- .gitignore
|-- README.md
```

## Explanation of Directories and Files

- **src**: Contains the source code for the application.
    - **lv.tsi.student.model**: Package for model classes.
        - `DuplicateRecordException.java`: Custom exception for duplicate records.
        - `Identity.java`: Interface representing an identity for entities.
        - `Student.java`: Class representing a student.
    - **lv.tsi.student.repository**: Package for repository classes.
        - `Repository.java`: Generic repository for managing records.
        - `StudentRepository.java`: Repository specific to managing student records.
    - **lv.tsi.student.service**: Package for service classes.
        - `StudentService.java`: Service class for managing student-related operations.
    - `StudentManager.java`: Main class for the student management system.

- **test**: Contains test classes for the application.
    - **lv.tsi.student.repository**: Test package for repository classes.
        - `StudentRepositoryTest.java`: Tests for the `StudentRepository` class.
    - **lv.tsi.student.service**: Test package for service classes.
        - `StudentServiceTest.java`: Tests for the `StudentService` class.

- **.gitignore**: Specifies intentionally untracked files to be ignored by Git.

- **README.md**: The main documentation file for the project.

---

**Note:** This documentation assumes basic familiarity with the command-line interface and the Java programming language.
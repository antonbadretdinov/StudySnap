<p align="center">
  <img src="app/src/main/res/mipmap-xxxhdpi/ic_launcher.webp" alt="StudySnap Icon" width="180"/>
</p>

<h1 align="center">StudySnap</h1>

<p align="center">
  A simple Android app built with Kotlin and Jetpack Compose
</p>

<p align="center">
  AI assistant for study notes
</p>

---

**StudySnap** is a simple Android application built with **Kotlin** and **Jetpack Compose**.  
It helps students work with lecture notes and study materials using AI.

The app allows a user to paste lecture text or personal notes and then choose one of several actions:
- **Summarize** the text
- **Explain it in simpler words**
- **Generate questions** for revision

## Why this project exists

Students often have to work with large amounts of text:
- lecture notes
- methodical materials
- theoretical explanations
- personal summaries

This can be time-consuming and difficult, especially when the material is long or written in complex language.

**StudySnap** was created to make learning easier and more accessible.  
The idea of the app is to provide a small AI assistant for educational notes, helping users:
- understand material faster
- repeat key information
- simplify difficult explanations
- prepare for tests and exams

## Main features

- Clean and simple interface
- Input field for lecture text or notes
- Character counter with input validation
- Three AI actions:
    - **Summary**
    - **Easier explanation**
    - **Question generation**
- Separate result screens for each action
- Navigation between screens using Jetpack Navigation

## Tech stack

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Navigation Compose**
- **MVVM architecture**
- **Hilt** for dependency injection

## Project structure

```text
presentation/
 ├── navigation/
 └── screens/
      ├── home/
      ├── summary/
      ├── easierExplain/
      └── makeQuestions/
```

## Project launch

- To run the project, add your own google-services.json file to the app/ directory and configure Firebase AI Logic in your Firebase project.
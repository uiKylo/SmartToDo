# Smart ToDo 📝

[![Build Debug APK](https://github.com/uiKylo/SmartToDo/actions/workflows/build.yml/badge.svg)](https://github.com/uiKylo/SmartToDo/actions/workflows/build.yml)
![Platform](https://img.shields.io/badge/Platform-Android-green?logo=android)
![Kotlin](https://img.shields.io/badge/Kotlin-2.0.21-purple?logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-BOM%202024.12.01-blue)
![Target SDK](https://img.shields.io/badge/Target%20SDK-35-orange)

A modern, minimal Android smart to-do list application built using **Jetpack Compose**, **Material 3**, and **Room Database**. Designed with focus on clean aesthetics, subtle animations, and intuitive task management.

---

## ✨ Features

- **Minimalist & Modern UI**: Clean typography, edge-to-edge layout, and rounded cards adhering to Material Design 3 guidelines.
- **Smart Priority System**: Categorize tasks into **Low**, **Medium**, and **High** priority with distinct color indicators and automatic sorting.
- **Dynamic Progress Tracking**: Real-time progress bar visualizing task completion status.
- **Filter Tabs**: Quickly toggle between **All**, **Active**, and **Done** tasks with interactive filter chips.
- **Swipe to Delete**: Smooth swipe-to-dismiss gesture with animated item transitions.
- **Dark Mode & Dynamic Color**: Full support for system dark theme and Android 12+ dynamic theming (Material You).
- **Offline-First Storage**: Local persistence powered by **Room Database** and reactive **Kotlin Flow**.
- **Automated CI/CD**: Pre-configured **GitHub Actions** workflow that builds and publishes the debug APK on every push.

---

## 🛠️ Tech Stack & Architecture

- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose) with [Material 3](https://m3.material.io/)
- **Architecture**: MVVM (Model-View-ViewModel) + Repository Pattern
- **State Management**: Kotlin `StateFlow` + Coroutines
- **Local Persistence**: [Room SQLite Database](https://developer.android.com/training/data-storage/room)
- **Build System**: Gradle with Kotlin DSL (`build.gradle.kts`)
- **CI/CD**: GitHub Actions (`build.yml`)

---

## 📦 How to Download the Debug APK

Every push or pull request to the `main` branch automatically triggers GitHub Actions to build a fresh debug APK.

1. Go to the repository's **[Actions](https://github.com/uiKylo/SmartToDo/actions)** tab.
2. Click on the latest workflow run under **Build Debug APK**.
3. Scroll down to the **Artifacts** section at the bottom.
4. Download the **`smart-todo-debug-apk`** zip file.
5. Extract the `.apk` file and install it directly on your Android phone or emulator.

---

## 🚀 Building Locally

### Prerequisites
- Android Studio Ladybug (2024.2.1+) or newer
- JDK 17
- Android SDK 35

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/uiKylo/SmartToDo.git
   cd SmartToDo
   ```
2. Open the project in Android Studio.
3. Sync Gradle and run the app on your connected device or emulator:
   ```bash
   ./gradlew assembleDebug
   ```
   The generated APK will be located at:
   `app/build/outputs/apk/debug/app-debug.apk`

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

# 📚 Muktokowlom CMP

<div align="center">

<img src="https://img.shields.io/badge/Kotlin-Compose%20Multiplatform-blueviolet?style=for-the-badge&logo=kotlin"/>
<img src="https://img.shields.io/badge/Platform-Android%20%7C%20iOS%20%7C%20Desktop-success?style=for-the-badge"/>
<img src="https://img.shields.io/badge/Architecture-Clean%20Architecture-orange?style=for-the-badge"/>
<img src="https://img.shields.io/badge/UI-Jetpack%20Compose-blue?style=for-the-badge"/>

### Modern Cross-Platform E-Book Application Built with Compose Multiplatform

</div>

---

# ✨ Overview

**Muktokowlom CMP** is a modern cross-platform digital E-Book application developed using **Compose Multiplatform**, supporting:

* 📱 Android
* 🍎 iOS
* 💻 Desktop

The application provides a smooth and user-friendly reading experience where users can:

* Browse books by categories
* Search books instantly
* Save favorite books
* Read free books
* Purchase premium books
* Download books for offline reading
* Track reading history
* Manage personal library

The platform also includes a powerful content ecosystem for writers and admins:

* Writers can upload and publish books
* Admin panel manages content moderation
* Writers earn rewards based on engagement such as:

  * Views
  * Reads
  * Reader interactions

Muktokowlom creates a complete digital publishing ecosystem for readers and writers.

---

# 🚀 Features

## 📖 Reader Features

* Browse books by category
* Advanced search functionality
* Favorite & bookmark system
* Offline reading support
* Paid & free book system
* Smooth reading experience
* Reading history tracking
* User authentication
* Profile management
* Download management
* Pagination support
* Real-time content updates

---

## ✍️ Writer Features

* Upload books/content
* Manage published books
* Track views & reader engagement
* Monetization/reward system
* Content analytics

---

## 🛠️ Admin Features

* Content moderation
* User management
* Book approval system
* Analytics dashboard
* Revenue management

---

# 🏗️ Tech Stack

| Technology             | Purpose                   |
| ---------------------- | ------------------------- |
| Compose Multiplatform  | Cross-platform UI         |
| Kotlin                 | Main programming language |
| Jetpack Compose        | Declarative UI            |
| MVVM                   | Architecture Pattern      |
| Clean Architecture     | Scalable code structure   |
| Ktor                   | Networking                |
| Koin                   | Dependency Injection      |
| Navigation3            | Navigation Management     |
| StateFlow / SharedFlow | Reactive state management |
| Room Database          | Local database            |
| DataStore Preference   | Local preference storage  |
| Paging 03              | Pagination handling       |

---

# 🧩 Architecture

The project follows:

# ✅ Clean Multi Module Architecture + MVVM

```text
Presentation Layer
       ↓
Domain Layer
       ↓
Data Layer
```

---

# 📂 Project Structure

```text
composeApp/
│
├── data/
│   ├── remote/
│   ├── local/
│   ├── repository/
│   ├── dto/
│   └── datasource/
│
├── domain/
│   ├── model/
│   ├── repository/
│   ├── usecase/
│   └── mapper/
│
├── presentation/
│   ├── screen/
│   ├── component/
│   ├── navigation/
│   ├── viewmodel/
│   └── state/
│
├── di/
│
├── utils/
│
└── platform/
```

---

# 🧠 Architecture Explanation

## 1️⃣ Data Layer

Responsible for handling:

* API requests
* Local database operations
* Repository implementations
* DTO models
* Data sources

### Includes

* Ktor API Service
* Room Database
* Remote & Local Data Source
* Repository Implementation

---

## 2️⃣ Domain Layer

Core business logic layer.

### Responsibilities

* UseCases
* Business rules
* Repository interfaces
* Domain models
* Data mappers

### Mapper

The mapper converts:

```text
DTO Model → Domain Model
Entity Model → Domain Model
```

This keeps the domain layer clean and independent.

---

## 3️⃣ Presentation Layer

Handles UI & user interactions.

### Includes

* Compose Screens
* ViewModels
* UI State Management
* Navigation
* Reusable Components

---

# 🔄 Data Flow

```text
UI Action
   ↓
ViewModel
   ↓
UseCase
   ↓
Repository
   ↓
Remote / Local Data Source
   ↓
API / Database
```

---

# 🧭 Navigation

The project uses:

# Navigation3 with Nested Navigation

### Benefits

* Modular navigation
* Type-safe navigation
* Nested graph support
* Better scalability
* Easier screen management

---

# 🌊 State Management

The application uses:

* StateFlow
* SharedFlow

### Why?

* Reactive UI updates
* Lifecycle awareness
* Better asynchronous handling
* Single source of truth

---

# 💉 Dependency Injection

The project uses:

# Koin

### Benefits

* Lightweight DI
* Easy setup
* Scalable architecture
* Better testability

---

# 🌐 Networking

The project uses:

# Ktor Client

### Features

* REST API communication
* Coroutine support
* Multiplatform compatibility
* Request/Response interceptors
* Authentication handling

---

# 🗄️ Local Storage

## Room Database

Used for:

* Offline books
* Favorites
* Reading history
* Cached content

---

## DataStore Preference

Used for:

* User preferences
* Theme settings
* Authentication tokens
* App configuration

---

# 📑 Pagination

The project uses:

# Paging 03

### Benefits

* Efficient large data loading
* Infinite scrolling
* Better performance
* Reduced memory usage

---

# 🎨 UI Design

Built with:

# Compose Multiplatform + Jetpack Compose

### Features

* Modern declarative UI
* Reusable components
* Responsive design
* Smooth animations
* Platform consistency

---

# 📱 Supported Platforms

| Platform | Support |
| -------- | ------- |
| Android  | ✅       |
| iOS      | ✅       |
| Desktop  | ✅       |

---

# ⚡ Performance Optimizations

* Lazy loading
* Pagination
* Local caching
* Offline-first support
* State optimization
* Modular architecture
* Reusable composables

---

# 🔐 Authentication System

The application supports:

* Login
* Registration
* Token management
* Secure session handling

---

# 📥 Offline Reading Support

Users can:

* Download purchased books
* Read without internet
* Access cached content
* Continue reading offline

---

# 🧪 Scalability

The architecture is designed for:

* Large-scale applications
* Feature modularization
* Easy maintenance
* Team collaboration
* Future expansion

---

# 📸 Screenshots (Android)

<p align="center">
  <img src="https://github.com/RxSajib/MuktoKowlom-CMP/blob/main/screenShot/Android%20Home.png?raw=true" width="250"/>
  <img src="https://github.com/RxSajib/MuktoKowlom-CMP/blob/main/screenShot/Android_Category.png?raw=true" width="250"/>
  <img src="https://github.com/RxSajib/MuktoKowlom-CMP/blob/main/screenShot/Search%20Android.png?raw=true" width="250"/>
   <img src="https://github.com/RxSajib/MuktoKowlom-CMP/blob/main/screenShot/Favorite%20Android.png?raw=true" width="250"/>
    <img src="https://github.com/RxSajib/MuktoKowlom-CMP/blob/main/screenShot/AndroidProfile.png?raw=true" width="250"/>
    <img src="https://github.com/RxSajib/MuktoKowlom-CMP/blob/main/screenShot/Android%20Login.png?raw=true" width="250"/>
    <img src="https://github.com/RxSajib/MuktoKowlom-CMP/blob/main/screenShot/Android%20Change%20Lan.png?raw=true" width="250"/>
</p>



# ⚙️ Setup & Installation

## Clone Repository

```bash
git clone https://github.com/RxSajib/MuktoKowlom-CMP.git
```

---

## Open Project

Open using:

* Android Studio Hedgehog or newer
* IntelliJ IDEA

---

## Run Android

```bash
./gradlew installDebug
```

---

## Run Desktop

```bash
./gradlew run
```

---

## Run iOS

Open the iOS project in Xcode and run.

---

# 🧱 Requirements

* Android Studio Latest Version
* Kotlin Multiplatform Plugin
* Xcode (For iOS)
* JDK 17+

---

# 📌 Future Improvements

* Audio books support
* AI recommendation system
* Social reading features
* Dark/Light dynamic themes
* Reader analytics
* Multi-language support
* Push notifications
* Cloud synchronization

---

# 🤝 Contributing

Contributions are welcome.

### Steps

1. Fork the repository
2. Create your feature branch
3. Commit your changes
4. Push your branch
5. Open a Pull Request

---

# 📄 License

```text
This project is licensed under the MIT License.
```

---

# 👨‍💻 Developer

### Sajib Roy

Compose Multiplatform & Android Developer

---

# ⭐ Support

If you like this project:

* Give this repository a ⭐
* Share with others
* Contribute to the project

---

<div align="center">

### 📚 Muktokowlom CMP — Modern Cross Platform Digital Reading Experience

</div>

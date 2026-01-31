# Mia 📈💰
Mia is an Android application developed to assist investors in managing their portfolios and declaring Income Tax. The project follows **Clean Architecture** and is structured into modules to ensure better organization and scalability.

## 📂 Project Structure
The project is divided into the following modules:
- **app**: The main module that integrates the other modules and contains the presentation layer.
- **designsystem**: Contains the reusable UI components of the project.
- **fixedincome**: The module responsible for managing fixed income investments.
- **stocks**: Responsible for features related to stocks and investments.

```text
Root Project/
├── build.gradle.kts            
├── settings.gradle.kts        
│
├── app/                      
│   ├── build.gradle.kts         
│   └── src/main/kotlin/lucas/momo/mia
│       ├── di/                  
│       ├── navigation/          
│       └── MainActivity.kt
│
├── designsystem/                        
│   ├── build.gradle.kts
│   └── src/main/kotlin/lucas/momo/designsystem/
│       ├── components/   
│       ├── theme/             
│       └── utils/            
│
└── fixedincome/                         
    ├── build.gradle.kts                 
    └── src/main/kotlin/lucas/momo/fixedincome/
        ├── data/                       
        │   ├── repository/              
        │   ├── remote/                 
        │   ├── local/                  
        │   └── mapper/                
        │
        ├── di/                          
        │   └── FixedIncomeModule.kt
        │
        ├── domain/                     
        │   ├── model/                   
        │   ├── repository/            
        │   └── usecase/               
        │
        └── presentation/               
            ├── list/                    
            ├── create/                  
            └── navigation/              
```

## 🚀 GitFlow & Commit Pattern
The project follows the atomic commit pattern and uses Gitmoji to identify the purpose of each commit:

| Emoji         | Significado                                      |
|--------------|------------------------------------------------|
| ✨ `:sparkles:` | Implementation of new features               |
| 🐛 `:bug:`    | Bug fixes                              |
| 🚀 `:rocket:` | New version releases                     |
| 🔧 `:wrench:` | Project configuration                      |
| 🔨 `:hammer:` | Adjustments to external tools (e.g., CI/CD)   |
| 🧪 `:test_tube:` | Creation or editing of tests                 |
| 📦 `:card_file_box:` | Project documentation                 |
| 🎉 `:tada:`  | Project kickoff                              |

## 🛠️ Tech Stack
- **Language:** Kotlin
  - **UI:** Jetpack Compose
  - **Dependency Injection:** Hilt
  - **Architecture:** Clean Architecture
  - **State Management:** Flow + ViewModel
  - **Networking:** Ktor
  - **Build System:** Gradle Kotlin DSL with Version Catalog

## 📄 License
This project is licensed under the **Apache-2.0** license.
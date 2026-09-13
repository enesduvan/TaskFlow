# 🚀 TaskFlow: Modern Görev & Zaman Yönetimi (Android Jetpack Compose)

[![Kotlin](https://img.shields.io/badge/Language-Kotlin%2017-7F52FF.svg?logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![Android SDK](https://img.shields.io/badge/Android%20SDK-API%2029%20--%2037-3DDC84.svg?logo=android&logoColor=white)](https://developer.android.com/)
[![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose%20%26%20Material%203-4285F4.svg?logo=google&logoColor=white)](https://developer.android.com/jetpack/compose)
[![Multi-Module](https://img.shields.io/badge/Architecture-Multi--Module%20Clean%20Architecture-purple.svg)](https://developer.android.com/topic/modularization)
[![Room Database](https://img.shields.io/badge/Local%20DB-Room%202.8%20%2B%20KSP-CC292B.svg)](https://developer.android.com/training/data-storage/room)
[![Firebase](https://img.shields.io/badge/Auth-Google%20Firebase%20Auth-FFCA28.svg?logo=firebase&logoColor=black)](https://firebase.google.com/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

**TaskFlow**, modern Android geliştirme standartları, **Jetpack Compose**, **Material 3** ve **Çoklu Modül (Multi-Module)** mimarisi kullanılarak geliştirilmiş, yüksek performanslı ve çevrimdışı öncelikli (Offline-First) bir **Görev, Planlama ve Zaman Yönetimi Mobil Uygulaması**dır.

Proje; bağımsız ve yeniden kullanılabilir bir kimlik doğrulama modülü (`:LoginModule`), KSP destekli reaktif Room SQLite veritabanı altyapısı ve bildirimsel (declarative) Compose arayüzü ile kurumsal seviyede bir mobil kod mimarisi sunar.

---

## ✨ Temel Özellikler & Mimari Avantajlar

### 🏗️ 1. Çoklu Modül (Multi-Module) Mimarisi
Proje, bağımsız sorumlulukları ayrıştıran ve derleme sürelerini optimize eden iki ana modülden oluşur:
- **`:app` Modülü:** Görev yönetim arayüzleri (`HomeScreen`, `AddTaskScreen`, `UpdateTaskScreen`, `DetailsScreen`), Compose NavHost rotalaması ve Room yerel veri katmanı.
- **`:LoginModule` Modülü:** Clean Architecture prensiplerine göre yapılandırılmış, her Android projesinde doğrudan yeniden kullanılabilir (reusable) bağımsız kimlik doğrulama kütüphanesi (`AuthRepository`, `AuthResult` sealed class, `LoginScreen`, `SignUpScreen`).

### 🎨 2. %100 Bildirimsel Jetpack Compose & Material 3
- XML arayüz dosyalarına ihtiyaç duymayan modern, reaktif ve deklaratif arayüz mimarisi.
- **Görev Kartları:** Öncelik derecesine göre dinamik renk vurgulamaları (Yüksek, Orta, Düşük), takvim ikonuyla biçimlendirilmiş son teslim tarihi ve tek tıkla durum değiştiren `Checkbox` bileşeni.
- **Dinamik Bildirimler:** Görev silme işlemlerinde `SnackbarHost` ve `savedStateHandle` ile anlık geri bildirim mesajları.

### 💾 3. Room 2.8 & KSP ile Çevrimdışı Öncelikli (Offline-First) Depolama
- **KSP (Kotlin Symbol Processing):** Hızlı ve modern derleme zamanı kod üretimi.
- **Asenkron Coroutine Desteği:** `suspend fun` işlemleri ile UI iş parçacığını asla bloke etmeyen veritabanı sorguları (`insert`, `update`, `delete`).
- **Reaktif Veri Akışı:** `LiveData` desteği ile görev listesinde yapılan tüm değişikliklerin anında ekrana yansıması.
- **Çift Yönlü Sıralama:** Görevleri tarihe göre (`ORDER BY Date ASC`) veya önceliğe göre (`ORDER BY Priority ASC`) dinamik filtreleme.

### 🔐 4. Firebase Authentication & Dinamik Başlangıç Kapısı
- Google Firebase Auth ile güvenli kullanıcı kaydı (`createUserWithEmailAndPassword`) ve girişi (`signInWithEmailAndPassword`).
- **Oturum Sürekliliği:** `MainActivity` içerisinde `currentUser != null` kontrolü ile oturumu açık olan kullanıcılar doğrudan `home_screen`'e, oturumu olmayanlar ise `login_screen`'e dinamik olarak yönlendirilir.

### 🧭 5. Jetpack Compose Navigation
- `rememberNavController` ile yönetilen tip güvenli ekran rotalaması.
- Rota argüman aktarımı ile görev ID'si üzerinden detay ve güncelleme ekranlarına (`details_screen/{taskId}`, `update_task_screen/{taskId}`) doğrudan erişim.

---

## 🏛️ Çoklu Modül & Sistem Mimarisi

```mermaid
flowchart TD
    subgraph MultiModule ["📦 Multi-Module Project Hierarchy"]
        subgraph LoginMod [":LoginModule (Clean Architecture Auth)"]
            LoginUI["Compose Screens\n(LoginScreen, SignUpScreen)"]
            LoginVM["ViewModels\n(LoginViewModel, SignUpViewModel)"]
            AuthRepo["AuthRepository Interface\n& AuthResult Sealed Class"]
            AuthRepoImpl["AuthRepositoryImpl"]
            FirebaseAuth["Google Firebase Auth"]
            
            LoginUI --> LoginVM --> AuthRepo --> AuthRepoImpl --> FirebaseAuth
        end

        subgraph AppMod [":app Module (Core Task Engine)"]
            NavHost["NavHost (Dinamik Başlangıç Yönlendirmesi)"]
            TaskScreens["Compose Presentation\n(HomeScreen, AddTask, UpdateTask, Details)"]
            TaskVM["ViewModels\n(HomeViewModel, AddTaskViewModel, TaskViewModel)"]
            TaskRepo["TaskRepository"]
            TaskDao["TaskDao (Room Interface & Coroutines)"]
            RoomDB[("Room SQLite Database\n(task_table)")]

            NavHost --> TaskScreens --> TaskVM --> TaskRepo --> TaskDao --> RoomDB
        end
    end

    AppMod -->|implementation project(':LoginModule')| LoginMod
    NavHost -->|Oturum Yoksa| LoginUI
    LoginUI -->|Başarılı Giriş| NavHost
```

---

## 🗄️ Veritabanı Şeması (`task_table`)

| Sütun Adı | Veri Tipi | Kısıtlama / Açıklama |
|---|---|---|
| `Id` | `Int` | `PRIMARY KEY`, `AutoGenerate = true` (Benzersiz Görev Kimliği) |
| `Task` | `String` | Görev Başlığı / Adı |
| `Description` | `String` | Görev Detayı / Açıklama Metni |
| `Date` | `String` | Bitiş / Son Teslim Tarihi |
| `Priority` | `String` | Öncelik Seviyesi (High / Medium / Low) |
| `Checked` | `Boolean` | Tamamlanma Durumu (`true`: Tamamlandı, `false`: Bekliyor) |

---

## 🛠️ Kullanılan Teknolojiler & Kütüphaneler

| Teknoloji | Versiyon / Rol |
|---|---|
| **Kotlin** | Temel Programlama Dili (JVM 17) |
| **Android SDK** | Min SDK 29 / Target SDK 36-37 |
| **Jetpack Compose** | Deklaratif Modern UI Motoru |
| **Material 3** | Material You Tasarım Sistemi & Genişletilmiş İkonlar |
| **Room Database** | `2.8.4` (Yerel SQLite Veritabanı) |
| **KSP** | `com.google.devtools.ksp` (Hızlı Kod Üretici) |
| **Google Firebase** | `BoM 34.16.0` & Firebase Authentication |
| **Navigation Compose** | Çoklu Ekran Rotası & Argüman Yönetimi |
| **Coroutines & LiveData** | Asenkron Veri İşleme ve Reaktif Durum Yönetimi |

---

## 🚀 Kurulum ve Çalıştırma

### Gereksinimler
- [Android Studio](https://developer.android.com/studio) (Koala / Ladybug veya üzeri)
- JDK 17
- Android SDK (API 29+)
- Fiziksel Android cihaz veya Android Virtual Device (AVD)

### Adımlar

1. **Repoyu Klonlayın:**
   ```bash
   git clone https://github.com/enesduvan/TaskFlow.git
   cd TaskFlow
   ```

2. **Android Studio ile Açın:**
   - Android Studio'yu başlatıp `Open` ile proje dizinini seçin.
   - Gradle bağımlılıklarının senkronize olmasını bekleyin (`Sync Project with Gradle Files`).

3. **Firebase Yapılandırması:**
   - Kendi Firebase Console projenizden indireceğiniz `google-services.json` dosyasını `app/` klasörü altına yerleştirin.
   - Firebase Authentication servisinde **Email/Password** sağlayıcısını aktif edin.

4. **Çalıştırın:**
   - Hedef cihazınızı seçip üst menüdeki yeşil **Run 'app'** (▶️) butonuna tıklayın.

---

## 📂 Proje Dizin Yapısı

```plaintext
TaskFlow/
├── README.md                             # Detaylı Proje Dokümantasyonu
├── .gitignore                            # Android & KSP Hariç Tutma Listesi
├── settings.gradle.kts                   # Multi-Module Tanımları (:app, :LoginModule)
├── app/                                  # Ana Görev Yönetim Modülü
│   ├── src/main/java/com/enesduvan/taskflow/
│   │   ├── MainActivity.kt               # Compose NavHost ve Rotalama
│   │   ├── presentation/                 # Compose Ekranları (Home, Add, Update, Details)
│   │   ├── roomDB/                       # Room SQLite Veritabanı, DAO, Entity & Repository
│   │   ├── viewmodel/                    # Görev ve Durum Yönetim Modelleri
│   │   └── ui/theme/                     # Material 3 Renk, Tema ve Tipografi
│   └── build.gradle.kts                  # App Modülü Derleme & Bağımlılık Ayarları
└── LoginModule/                          # Bağımsız Yeniden Kullanılabilir Auth Modülü
    ├── src/main/java/com/enesduvan/loginmodule/
    │   ├── data/                         # Firebase Auth Veri Gerçeklemesi
    │   ├── domain/                       # AuthRepository Arayüzü & AuthResult Sınıfları
    │   ├── presentation/                 # LoginScreen & SignUpScreen Compose Ekranları
    │   └── viewmodel/                    # Kimlik Doğrulama ViewModel'leri
    └── build.gradle.kts                  # LoginModule Bağımsız Kütüphane Ayarları
```

---

## 👨‍💻 Geliştirici

**Enes Duvan**
- GitHub: [@enesduvan](https://github.com/enesduvan)

---

## 📄 Lisans

Bu proje [MIT Lisansı](LICENSE) kapsamında açık kaynak olarak sunulmaktadır.

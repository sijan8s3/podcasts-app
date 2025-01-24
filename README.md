---

# **Podcasts App Documentation**

## **Overview**
The Podcasts App is a Kotlin-based Android application designed using Jetpack Compose. It allows users to explore podcasts, mark their favorite shows, and handle pagination efficiently for large datasets. The project is structured with clean architecture principles and leverages modern Android development practices.

---

## **Features**
- Display a list of podcasts retrieved from a remote API.
- Mark/unmark podcasts as favorites using DataStore.
- Infinite scrolling with pagination support.
- Robust error handling with retry mechanisms.
- Clean and modular codebase, adhering to MVI architecture.
- Jetpack Compose UI with Material Design 3 components.

---

## **Project Structure**
The project follows **Clean Architecture** principles and is organized into **feature-based modules** to ensure scalability, maintainability, and reusability. Each feature module encapsulates its own `data`, `domain`, and `presentation` layers.

### **Modules Overview**

1. **app**
    - The entry point of the application.
    - Sets up navigation, dependency injection, and theming.
    - Integrates all feature modules to create a cohesive application.

2. **core**
    - Contains shared utilities, models, and base classes.
    - Provides common functionality such as network utilities, error handling, and custom extensions.

3. **podcasts_list**
    - Handles the functionality for listing podcasts, including pagination, favorites, and error handling.

4. **di**
    - Contains Koin dependency injection modules for all app components.
    - Manages dependencies for each feature, ensuring loose coupling and scalability.

---

### **Feature-Based Modular Structure**
Each feature module (`podcasts_list`, `core`) is self-contained, consisting of the following layers:
1. **Data Layer**
    - Handles local and remote data sources.
    - Uses Ktor for API communication and DataStore for persistent storage.
    - Contains `LocalFavouritesRepository` and `RemotePodcastDataSource`.

2. **Domain Layer**
    - Defines business logic and repositories.
    - Includes entities such as `Podcast` and interfaces like `PodcastDataSource` and `FavouritesRepository`.

3. **Presentation Layer**
    - Responsible for the UI and user interactions.
    - Uses `PodcastsListViewModel` to manage the state.
    - Contains composable screens like `PodcastsListScreen` and utility functions for event handling.

---

## **API Integration**
The app integrates with a RESTful API to fetch podcast data.
- **Endpoint:** `/best_podcasts`
- **Response:** The API returns a paginated list of podcasts with metadata like `next_page_number` and `has_next`.

### Example API Response
```json
{
  "has_next": true,
  "next_page_number": 2,
  "podcasts": [
    {
      "id": 1,
      "title": "Podcast Title",
      "publisher": "Publisher Name",
      "image": "image_url",
      "thumbnail": "thumbnail_url",
      "description": "Podcast Description"
    }
  ]
}
```

---

## **Key Components**

### **1. State Management**
State is managed using `StateFlow` in `PodcastsListViewModel`.
The app uses a structured state management approach to handle interactions between the UI and ViewModel effectively:

1. **State:** Represents the UI's data, such as the list of podcasts, favorite status, and error messages. The ViewModel maintains the state as the single source of truth.
2. **Actions:** Encapsulate user-triggered events from the UI (e.g., refreshing podcasts, marking favorites, or retrying a failed request). Actions are passed to the ViewModel to invoke business logic.
3. **Events:** Handle one-time actions like showing toast messages or errors. These are consumed reactively by the UI.

This approach ensures a clear separation of concerns and makes the app scalable, testable, and easy to maintain.

### **2. Pagination**
Pagination is implemented using a lazy loading approach.
- **Logic:**
    - Triggers API calls when the user scrolls to the end of the list.
    - Uses a buffer threshold to prefetch additional items.

### **3. Favorites Handling**
Favorites are stored locally using Jetpack DataStore.
- **Key:** `favourite_podcasts`
- **Operations:**
    - Add a podcast to favorites: `addAsFavourite(podcastId)`
    - Remove a podcast from favorites: `removeFromFavourites(podcastId)`
    - Get the flow of favorite podcasts: `getFavouritePodcastsFlow()`

---

## **Tech Stack**
- **Programming Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** MVI with Clean Architecture principles
- **Libraries/Tools:**
    - **Ktor:** API networking.
    - **Koin:** Dependency injection.
    - **Kotlinx Serialization:** JSON parsing.
    - **Jetpack DataStore:** Persistent storage.
    - **Coroutines & Flow:** Asynchronous programming and reactive streams.
    - **StateFlow:** State management.
    - **Material Design 3:** UI components.

---

## **Setup and Installation**
### **Prerequisites**
- Android Studio Flamingo or higher.
- Minimum SDK: 33 (Android 13).
- Internet connection for API calls.

### **Steps**
1. Clone the repository:
   ```bash
   git clone https://github.com/sijan8s3/podcasts-app.git
   ```
2. Open the project in Android Studio.
3. Sync the Gradle files.
4. Set up the API base URL in a `build.gradle` file or `local.properties`:
   ```gradle
   BASE_URL="https://listen-api-test.listennotes.com/api/v2/"
   ```
5. Run the project on an emulator or physical device.

---

## **How to Use**
1. **Explore Podcasts:**
    - Launch the app to view a list of podcasts.
    - Scroll down to load more podcasts automatically.
2. **Mark as Favorite:**
    - Tap the favorite icon on any podcast to add/remove it from favorites.
3. **Retry Loading:**
    - If an error occurs, click the "Retry" button to reload the data.

---


## **Future Improvements**
1. **Caching:** Introduce local caching to reduce API calls.
2. **Unit Tests:** Add tests for ViewModel and repository logic.
3. **UI Enhancements:** Add animations for a better user experience.
4. **Theming:** Support light/dark themes.
5. **Multi-language Support:** Add localization for international users.

---
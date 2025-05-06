# Location Finder App (Android, Java, SQLite)

A basic Android application built in Java that functions as a personal location manager. It allows users to store, search, update, and delete location data using a local SQLite database.

## Features

- Add new locations with address, latitude, and longitude
- Search for saved locations using any single field (e.g., ID or address)
- Update existing entries in the database
- Delete locations from the list
- Minimalist UI built in XML with Java backend

## Technologies Used

- Java
- Android Studio
- SQLite (via SQLiteOpenHelper)
- XML Layouts

## Project Structure

```
app/
├── src/
│   └── main/
│       ├── java/com/example/locationfinder/
│       │   ├── MainActivity.java              # Core UI and logic
│       │   ├── LocationDatabaseHelper.java   # SQLite helper class
│       │   └── ui/                            # (if used for fragments or separate UI logic)
│       └── res/                               # Layouts, drawables, etc.
├── AndroidManifest.xml
```

## Setup & Usage

1. Open the project in Android Studio.
2. Let Gradle sync and install dependencies.
3. Build and run the app on an emulator or physical device.
4. Use the buttons to perform CRUD operations on stored locations.

## Notes

- All data is stored locally using SQLite and persists across sessions.
- The UI is designed for testing core database functionality, not for production.

## License

This project is for demonstration and educational purposes. No commercial use is implied.

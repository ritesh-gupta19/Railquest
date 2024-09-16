# RailQuest

RailQuest is an Android application that allows users to book train tickets, check train availability, and review past bookings. It simplifies the process of train ticket management through a user-friendly interface and API-powered functionalities.

## Features

- **Search for Trains**: Users can search for trains based on routes and availability.
- **Book Tickets**: Choose train seats based on preference (e.g., upper, lower, side upper, etc.).
- **View Past Bookings**: Access your past ticket bookings.
- **User Authentication**: Create an account and log in using a username and password.

## Technology Stack

- **Language**: Java, XML
- **IDE**: Android Studio
- **Database**: Firebase (for user data, bookings, etc.)
- **API Calls**: OkHttp3 is used to handle API requests from the RapidAPI platform.

## Installation

### Prerequisites

- **Android Studio**: Ensure you have Android Studio installed.
- **Firebase**: You'll need your own Firebase project to store user and ticket data.

### Steps

1. Clone or download the repository to your local machine.
2. Open the project in Android Studio.
3. Replace the Firebase database URL in the project files with your own Firebase URL:
    - Go to `app -> src -> main -> java -> com -> example -> railquest -> DBHandler.java` and change the Firebase URL to your own.
4. Build and run the project on an Android device or emulator.

### Firebase Setup

1. Create a Firebase project in your Firebase Console.
2. Enable Authentication (Email/Password).
3. Replace the `google-services.json` file in your project with the one from your Firebase project.
4. Update the Firebase Database URL in the project files as mentioned above.

## Usage

- **Sign Up**: First-time users must create a new account using a username and password.
- **Log In**: Log in to access features like ticket booking and viewing past bookings.
- **Book Tickets**: Search for trains, choose your seat preference, and complete your booking.
- **View Bookings**: Navigate to the "My Bookings" section to view all your past ticket bookings.
- **Check PNR**: Enter your PNR number to retrieve detailed ticket information.

## Dependencies

- **OkHttp3**: Used to make API requests for train details and booking information.
- **Firebase Authentication**: For user account creation and login.
- **Firebase Realtime Database**: To store user data, bookings, and PNR details.

## Notes

- Before installation, ensure you replace the provided Firebase database URL with your own. The URL used in the code may not work, and you will need your own Firebase project. The Firebase URL can be found in the file `app -> src -> main -> java -> com -> example -> railquest -> DBHandler.java`.

## Testing

No specific testing framework or methodology is used for this app.

## License

This project does not have a license. It is intended for personal and educational use.

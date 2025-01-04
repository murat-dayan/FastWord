# Fastword

FastWord is a word game where users can compete with each other. The app supports anonymous and Facebook login options and manages its data using **Supabase**.

## Features

### 1. **Login Module**
- **Anonymous Login:** Users can quickly try the app without creating an account.
- **Facebook Login:** Users can log in using their Facebook account.
- User session information is securely managed with **Supabase Authentication**.

### 2. **Game Module**
The user can search for an opponent, the opponent is found and a question is randomly selected from the table and users have to give the correct answer within the specified time.

### 3. **Shop Module**
Users can increase their energy by spending coins or emeralds

### 4. **Friends Module**
Users can send friend requests or accept requests and list their friends.

### 5. **Settings Module**
In-app settings

### 6. **Leaderboard module**
Users can see the rankings of their friends or everyone, listed according to scores

---

## Project Screenshots

<div align="center">

### Login Module Screens
<img src="https://github.com/user-attachments/assets/4517377e-6e16-45d4-a6b4-938670b8a626" alt="Login Screen" width="200"/>

---

### Game Module Screens
<img src="https://github.com/user-attachments/assets/c0f6db73-d99e-4269-8494-20d42fd30e38" alt="Game Screen" width="200"/>
<img src="https://github.com/user-attachments/assets/5b3d8ff7-68a8-4ff8-adec-74b9a10fdae0" alt="Match Screen" width="200"/>

---

### Friends Module Screen
<img src="https://github.com/user-attachments/assets/6d1d2e36-8def-4a89-b758-2203a2dd88e7" alt="Friends Screen" width="200"/>

### Shop Module Screen
<img src="https://github.com/user-attachments/assets/503b92cb-0f5a-4164-9871-1e90616c3cd4" alt="Shop Screen" width="200"/>

### Settings Module Screen
<img src="https://github.com/user-attachments/assets/dda0fe07-d63c-4049-b0d7-438bd0799b0f" alt="Settings Screen" width="200"/>

### LeaderBoard Module Screen
<img src="https://github.com/user-attachments/assets/6b36250e-d7e3-4216-8911-63ef0f9695ec" alt="LeaderBoard Screen" width="200"/>
</div>

---

## Technical Details

### **Technologies Used**
- **Language:** Kotlin
- **Architecture:** MVI (Model-View-Intent(Actions))
- **Database:** Supabase
- **Authentication:** Supabase Authentication, Facebook SDK
- **State Management:** StateFlow
- **UI:** Material Design
- **MultiModule:** 

---

## Installation and Usage

### 1. Clone the Repository
```bash
git clone https://github.com/murat-dayan/FastWord
cd FastWord
```

### 2. Facebook Login Configuration
- Create an app on [Facebook Developers](https://developers.facebook.com/).
- After completing the necessary configurations, add the `facebook_app_id` value to the `strings.xml` file.

### 3. Supabase Integration
- Create an account on [Supabase](https://supabase.com/).
- Add your project URL and Public Key values to the `supabaseConfig.kt` file.

### 4. Build and Run
- Open the project in Android Studio.
- Install the required SDKs and dependencies, then run the application.

---

## Contributing
To contribute, please create a **pull request**. Any contributions or suggestions are highly appreciated!

---

## License
This project is licensed under the MIT License. For more information, see the `LICENSE` file.

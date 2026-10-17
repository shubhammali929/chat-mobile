# Chat Mobile — Brave launcher (Android Studio source)

This is a small Android launcher app. Tapping its icon opens `https://chat.google.com/` explicitly in Brave (`com.brave.browser`), so Google Chat uses the cookies/session already stored by Brave.

## What it does
- Adds a separate **Chat Mobile** app icon on Android.
- Launches Google Chat in Brave directly.
- Uses Brave's existing sign-in session when that session is valid.
- Falls back to the default browser if Brave is not installed.

## Important limitation
Android apps cannot safely read or reuse Brave's private cookies inside their own WebView. For that reason, this app launches Brave instead of displaying Chat inside its own window. After launch, the browser owns the screen and session. This gives you a separate launcher icon, but it does not create an independent app window with Brave's session embedded inside it.

For a more app-like experience, you can also open `https://chat.google.com/` in Brave and check the browser menu for **Add to Home screen**. Availability and whether it opens standalone depend on Brave and Google's current site behavior.

This is not an official Google app and does not bypass Google Workspace or company device policies.

## Build
1. Open this `ChatMobile` folder in Android Studio.
2. Allow Gradle sync and install the requested Android SDK if prompted.
3. Choose **Build → Build Bundle(s) / APK(s) → Build APK(s)**.
4. Install the APK on your Android device.

The source project is provided; a compiled APK has not been built or device-tested in this environment.

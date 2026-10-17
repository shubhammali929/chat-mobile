# Build the APK without installing Android Studio

This repository includes a GitHub Actions workflow that builds a debug APK in GitHub's cloud.

1. Sign in to GitHub and create a new repository (for example, `chat-mobile`).
2. Upload the contents of this folder to the repository. Ensure `.github/workflows/build-apk.yml` is included and the default branch is named `main`.
3. Open the repository's **Actions** tab. If prompted, enable GitHub Actions.
4. Select **Build Chat Mobile APK** on the left and click **Run workflow**. Alternatively, the workflow runs automatically after a push to `main`.
5. Wait for the workflow to finish successfully, then open that workflow run.
6. Under **Artifacts**, download `ChatMobile-debug-apk` and unzip it to obtain `app-debug.apk`.
7. Transfer `app-debug.apk` to your Android phone, open it, and allow installation from that source if Android asks.

Notes:
- This creates a debug APK suitable for personal testing, not a Play Store release.
- The APK is unsigned for Play Store distribution, but Android debug builds are signed with the debug key so they can be installed for testing.
- The app launches `https://chat.google.com/` in Brave, reusing Brave's own signed-in session. It does not embed or copy Brave cookies.
- The project has not been compiled or device-tested in this environment.

# Build the "LexiGuess — Daily Word Puzzle" web game into an Android APK

This document tells you exactly how to turn the `game.html` in this folder into an
installable Android `.apk`. The method is **Ionic Capacitor** (free, offline-friendly,
fully command-line). Follow the steps in order.

> All the values you need (app name, package id, versions, icon, signing, etc.) live in
> the requirements file next to this one. Read it first.

---

## STEP 0 — Read the requirements file first (MANDATORY)

Open and read **`requirement_for_web_to_apk_31-wordle.txt`** in this same folder.
Use every value in it. If a required value is blank, use the documented default written
beside it (or ask the user only if there is no default). Do not skip this step.

## STEP 1 — Check the environment

Run these checks. If something is missing, install it (or switch to the fallback in STEP 7).

- `node -v`   → must be **22 or higher** (Capacitor 8 requirement)
- `java -version` → **JDK 21** (or the JDK bundled with Android Studio)
- `echo $ANDROID_HOME` → must point to an Android SDK
- `sdkmanager --version` → command-line tools must be on PATH

If the Android SDK is missing, install it headlessly and accept licenses:

```bash
yes | sdkmanager --licenses
sdkmanager "platform-tools" "platforms;android-36" "build-tools;36.0.0"
```

## STEP 2 — Scaffold the Capacitor project (run inside this folder)

```bash
# 2a. Put the game where Capacitor expects the web assets
mkdir -p www
cp game.html www/index.html      # index.html MUST contain a <head> tag

# 2b. Node project + Capacitor core/CLI
npm init -y
npm i @capacitor/core
npm i -D @capacitor/cli

# 2c. Initialise the native shell (values come from the .txt)
npx cap init "LexiGuess — Daily Word Puzzle" com.webgames.game31wordle --web-dir=www

# 2d. Add the Android platform and copy assets into it
npm i @capacitor/android
npx cap add android
npx cap sync android
```

## STEP 3 — Apply configuration from the requirements file

- **Version**: set `versionCode` / `versionName` in `android/app/build.gradle`.
- **Orientation**: set `android:screenOrientation` on the main `<activity>` in
  `android/app/src/main/AndroidManifest.xml` (value from the .txt).
- **Icon + splash**: place `assets/icon.png` (>= 1024x1024) and `assets/splash.png`
  (>= 2732x2732), then:
  ```bash
  npm i -D @capacitor/assets
  npx capacitor-assets generate --android
  ```
- **Keep it offline**: do **NOT** set any `server.url` in `capacitor.config.*`.
  The game must load its bundled local files (localStorage will work as-is).
- **Min/target SDK** (only if the .txt overrides defaults): edit `android/variables.gradle`
  (defaults: minSdk 24, target 36, compile 36).

## STEP 4 — Build the DEBUG APK (always do this first to verify)

```bash
cd android
./gradlew assembleDebug
```

Output: `android/app/build/outputs/apk/debug/app-debug.apk`
(install with `adb install -r <path>` to test).

## STEP 5 — Build the RELEASE APK (only if signing values are in the .txt)

```bash
# 5a. Create a keystore if one does not exist yet
keytool -genkey -v -keystore release.keystore -keyalg RSA -keysize 2048 \
  -validity 10000 -alias 31-wordle
```

Add a `signingConfigs.release` block in `android/app/build.gradle` (read the passwords
from environment variables or a `keystore.properties` file — never hard-code them), attach
`signingConfig signingConfigs.release` to the `release` build type, then:

```bash
cd android
./gradlew assembleRelease
```

Output: `android/app/build/outputs/apk/release/app-release.apk`

> Prefer `./gradlew assembleRelease` with a Gradle `signingConfig` over
> `npx cap build … --androidreleasetype APK`; the CLI signing path has known edge-case bugs.

## STEP 6 — Report back

Print the absolute path(s) of the produced APK(s), plus the package id and version that
were used. Confirm whether it was a debug or a signed release build.

## STEP 7 — Fallbacks (use only if STEP 1–5 cannot run here)

- **No Node / Capacitor unavailable** → scaffold a minimal native WebView Android project:
  a single Activity whose `WebView` loads `file:///android_asset/index.html`
  (enable `javaScriptEnabled` and `domStorageEnabled`, copy `game.html` into
  `app/src/main/assets/index.html`), then build with `./gradlew assembleDebug`.
- **Machine too small for the ~1–3 GB SDK / first build times out** → generate a free
  **GitHub Actions** workflow (`ubuntu-latest`, `setup-node@22`, `setup-java@21`,
  `npx cap sync android`, `./gradlew assembleDebug`, upload the APK as an artifact).

---

### Notes
- Use only original or properly licensed icon/splash art — no copyrighted logos or characters.
- The package id must be globally unique before any Google Play upload (change
  `com.example.*` to a domain you control). For Play, also build an `.aab`
  (`./gradlew bundleRelease`) and keep the keystore safe.
- Tooling versions current as of mid-2026 (Capacitor 8.x, Node 22, Gradle 8.14.3, SDK 36);
  re-verify with `npx cap --version` rather than trusting these forever.

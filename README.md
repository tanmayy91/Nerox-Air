# Nerox-Air (NexaMusic Lite Android)

A lightweight Android version inspired by NexaMusic.

## Build locally

```bash
./gradlew assembleDebug
```

The debug APK is generated under:

`app/build/outputs/apk/debug/`

## CI workflow

GitHub Actions workflow at `.github/workflows/build.yml` builds the app with:

- JDK 17
- Gradle wrapper
- `./gradlew assembleDebug`

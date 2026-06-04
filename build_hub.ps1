# Rebuild script for Game Hub
Write-Host "========================================"
Write-Host "Starting Game Hub Rebuild Process..."
Write-Host "========================================"

$hubDir = "C:\Users\user\Desktop\Game hub"
$wwwDir = Join-Path $hubDir "www"
$selectForApkDir = Join-Path $hubDir "select_for_apk"
$destApkPath = Join-Path $hubDir "Game Hub.apk"

$env:JAVA_HOME = "C:\Users\user\Downloads\android_studio\jbr"
$env:ANDROID_HOME = "C:\Users\user\AppData\Local\Android\Sdk"

# 1. Sync game sources to www
Write-Host "Synchronizing game sources..."
Get-ChildItem -Path $selectForApkDir -Directory | ForEach-Object {
    $folderName = $_.Name
    $folderPath = $_.FullName
    $destGameDir = Join-Path $wwwDir $folderName
    
    if (!(Test-Path $destGameDir)) {
        New-Item -ItemType Directory -Path $destGameDir | Out-Null
    }
    
    $htmlFile = Get-ChildItem -Path $folderPath -Filter "*.html" | Select-Object -First 1
    if ($htmlFile) {
        $destPath = Join-Path $destGameDir $htmlFile.Name
        Copy-Item $htmlFile.FullName $destPath -Force
    }
}

# 2. Run Capacitor Sync
Write-Host "Syncing assets to Android platform..."
cmd.exe /c "npx cap sync android"

# 3. Build Android APK
Write-Host "Compiling APK via Gradle..."
$androidBuildDir = Join-Path $hubDir "android"
cmd.exe /c "cd /d `"$androidBuildDir`" && .\gradlew.bat assembleDebug"

# 4. Copy to outputs
$apkSrcPath = Join-Path $androidBuildDir "app\build\outputs\apk\debug\app-debug.apk"
if (Test-Path $apkSrcPath) {
    Copy-Item $apkSrcPath $destApkPath -Force
    Write-Host "Success! New Game Hub APK copied to: $destApkPath" -ForegroundColor Green
} else {
    Write-Error "Build failed! Gradle output APK not found."
}

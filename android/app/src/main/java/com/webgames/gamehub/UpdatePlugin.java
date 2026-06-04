package com.webgames.gamehub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import androidx.core.content.FileProvider;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@CapacitorPlugin(name = "UpdatePlugin")
public class UpdatePlugin extends Plugin {

    @PluginMethod
    public void getAppVersion(PluginCall call) {
        try {
            Context context = getContext();
            String versionName = context.getPackageManager()
                .getPackageInfo(context.getPackageName(), 0).versionName;
            JSObject ret = new JSObject();
            ret.put("versionName", versionName != null ? versionName : "1.0.0");
            call.resolve(ret);
        } catch (Exception e) {
            call.reject("Error getting app version", e);
        }
    }

    @PluginMethod
    public void installApk(PluginCall call) {
        String apkUrl = call.getString("apkUrl");
        if (apkUrl == null || apkUrl.isEmpty()) {
            call.reject("APK URL is missing");
            return;
        }

        // Run asynchronously in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Context context = getContext();

                    // Check REQUEST_INSTALL_PACKAGES permission first if Oreo or newer
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        if (!context.getPackageManager().canRequestPackageInstalls()) {
                            JSObject ret = new JSObject();
                            ret.put("status", "need_permission");
                            call.resolve(ret);

                            // Launch system settings for install unknown apps
                            Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                            intent.setData(Uri.parse("package:" + context.getPackageName()));
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            return;
                        }
                    }

                    // Send progress start
                    notifyProgress(0);

                    // Download APK file
                    URL url = new URL(apkUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(15000);
                    connection.setReadTimeout(15000);
                    connection.connect();

                    if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        call.reject("Server returned HTTP " + connection.getResponseCode() + " " + connection.getResponseMessage());
                        return;
                    }

                    int fileLength = connection.getContentLength();
                    File cacheFile = new File(context.getCacheDir(), "GameHub_update.apk");
                    if (cacheFile.exists()) {
                        cacheFile.delete();
                    }

                    try (InputStream input = new BufferedInputStream(connection.getInputStream());
                         FileOutputStream output = new FileOutputStream(cacheFile)) {

                        byte[] data = new byte[8192];
                        long total = 0;
                        int count;
                        int lastPercent = 0;

                        while ((count = input.read(data)) != -1) {
                            total += count;
                            if (fileLength > 0) {
                                int percent = (int) (total * 100 / fileLength);
                                if (percent > lastPercent) {
                                    lastPercent = percent;
                                    notifyProgress(percent);
                                }
                            }
                            output.write(data, 0, count);
                        }
                    }

                    // Trigger install
                    File apkFile = cacheFile;
                    if (!apkFile.exists() || apkFile.length() == 0) {
                        call.reject("Downloaded file is empty or missing");
                        return;
                    }

                    Uri apkUri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", apkFile);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    context.startActivity(intent);

                    JSObject ret = new JSObject();
                    ret.put("status", "installing");
                    call.resolve(ret);

                } catch (Exception e) {
                    call.reject("Failed to download or install update", e);
                }
            }
        }).start();
    }

    private void notifyProgress(int percent) {
        JSObject progressObj = new JSObject();
        progressObj.put("percent", percent);
        notifyListeners("downloadProgress", progressObj);
    }
}

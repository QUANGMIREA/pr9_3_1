package com.example.pr9_3_1;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        verifyStoragePermissions();

        // Tạo và ghi tệp
        createAndWriteFile();

        // Xóa tệp
        //deleteFile();
    }

    private void createAndWriteFile() {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        File file = new File(storageDir, "example1233.txt");
        try {
            if (!file.exists()) {
                boolean created = file.createNewFile();
                if (created) {
                    FileWriter writer = new FileWriter(file);
                    writer.append("Hello, world!");
                    writer.flush();
                    writer.close();
                    Toast.makeText(this, "File created and written to", Toast.LENGTH_LONG).show();
                }
            }
        } catch (IOException e) {
            Toast.makeText(this, "Failed to create file", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void deleteFile() {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(storageDir, "example1233.txt");
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                Toast.makeText(this, "File deleted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to delete file", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void verifyStoragePermissions() {
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
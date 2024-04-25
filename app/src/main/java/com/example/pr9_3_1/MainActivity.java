package com.example.pr9_3_1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Проверяем наличие внешнего хранилища
        if (!isExternalStorageAvailable() || isExternalStorageReadOnly()) {
            Toast.makeText(this, "External Storage not available or read only", Toast.LENGTH_LONG).show();
            return;
        }

        // Создание и запись файла
        createAndWriteFile();

        // Удаление файла
        deleteFile();
    }

    private void createAndWriteFile() {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }

        File file = new File(storageDir, "example.txt");
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
            e.printStackTrace();
        }
    }

    private void deleteFile() {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(storageDir, "example.txt");
        if (file.exists()) {
            boolean deleted = file.delete();
            if (deleted) {
                Toast.makeText(this, "File deleted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to delete file", Toast.LENGTH_LONG).show();
            }
        }
    }

    public boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState);
    }

    public boolean isExternalStorageAvailable() {
        String extStorageState = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(extStorageState);
    }
}

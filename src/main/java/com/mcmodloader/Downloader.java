package com.mcmodloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader {
    private static String downloadSource = "https://github.com/Tedtot/MinecraftMods/archive/refs/heads/main.zip";

    public void runDownloader(String modsDirectory) {
        String downloadDirectory = "C:/Users/" + System.getProperty("user.name") + "/Downloads";
        System.out.println("Downloading files from GitHub...");
        try {
            String downloadedZip = downloadModsFromGithub(downloadDirectory);
            System.out.println("Downloaded " + (new File(downloadedZip)).getName());
            // Realistically we can extract straight to the designed location, but if
            // anything goes wrong we don't want to delete the mods and leave nothing
            extractModsFromZip(downloadedZip, downloadDirectory);
            removeOldMods(modsDirectory);
            assignNewMods(downloadDirectory, modsDirectory);

            File downloadedZipFile = new File(downloadedZip);
            if (!downloadedZipFile.isDirectory() &&
                    downloadedZipFile.getName().equals("main.zip")) {
                System.out.println("Deleting " + downloadedZipFile.getName());
                downloadedZipFile.delete();
            }

        } catch (Throwable t) {
        }
        System.out.println("Download complete!");
    }

    private void assignNewMods(String source, String destination) {
        for (File file : new File(source).listFiles()) {
            if (isFileExtensionJar(file)) {
                System.out.println("Moving " + file.getName());
                file.renameTo(new File(destination + "/" + file.getName()));
            }
        }
    }

    private void removeOldMods(String directory) {
        File modsDirectory = new File(directory);
        if (!modsDirectory.getName().equals("mods"))
            return;

        for (File file : modsDirectory.listFiles()) {
            // The directory check is kind of unnecessary, but don't want to mess things up
            if (!file.isDirectory() && isFileExtensionJar(file)) {
                System.out.println("Removing " + file.getName());
                file.delete();
            }
        }
    }

    private boolean isRedirected(Map<String, List<String>> header) {
        for (String hv : header.get(null)) {
            if (hv.contains(" 301 ") || hv.contains(" 302 "))
                return true;
        }
        return false;
    }

    // https://stackoverflow.com/a/13442063
    private String downloadModsFromGithub(String destination) throws Throwable {
        String fileName = assignFileNameFromDirectory(destination, downloadSource);

        URL url = new URL(downloadSource);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        Map<String, List<String>> header = http.getHeaderFields();
        while (isRedirected(header)) {
            downloadSource = header.get("Location").get(0);
            url = new URL(downloadSource);
            http = (HttpURLConnection) url.openConnection();
            header = http.getHeaderFields();
        }
        InputStream input = http.getInputStream();
        downloadFile(input, fileName);

        return fileName;
    }

    private void downloadFile(InputStream input, String fileDestinataion) throws Throwable {
        byte[] buffer = new byte[4096];
        int n = -1;
        OutputStream output = new FileOutputStream(new File(fileDestinataion));
        while ((n = input.read(buffer)) != -1) {
            output.write(buffer, 0, n);
        }
        output.close();
    }

    private void extractModsFromZip(String fileName, String destination) throws Throwable {
        try (ZipFile zipFile = new ZipFile(fileName)) {
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();

                if (entry.isDirectory())
                    continue;

                String modName = entry.getName();
                File modFile = new File(modName);
                if (!isFileExtensionJar(modFile) || modFile.getName().equals("mcmodloader.jar"))
                    continue;

                try (InputStream inputStream = zipFile.getInputStream(entry)) {
                    String modDestination = assignFileNameFromDirectory(destination, modName);
                    System.out.println("Extracting " + modName);
                    downloadFile(inputStream, modDestination);
                }
            }
        }
    }

    public boolean isFileExtensionJar(File file) {
        return getFileExtension(file.getName()).equals("jar");
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    private String assignFileNameFromDirectory(String destination, String directory) {
        return destination + "/" + directory.substring(directory.lastIndexOf("/") + 1);
    }

    public void setDownloadSource(String source) {
        downloadSource = source;
    }
}
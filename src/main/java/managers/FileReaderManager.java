package managers;

import Utils.ConfigFileUtils;

// Used for singleton approach

public class FileReaderManager {

    private static FileReaderManager fileReaderManager = new FileReaderManager();
    private static ConfigFileUtils fileUtils;

    private FileReaderManager() {
    }

    public static FileReaderManager getInstance() {
        return fileReaderManager;
    }

    public ConfigFileUtils getFileUtils() {
        return (fileUtils == null) ? fileUtils = new ConfigFileUtils() : fileUtils;
    }

}

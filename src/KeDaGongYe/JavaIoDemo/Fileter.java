package keDaGongYe.JavaIoDemo;

import java.io.File;
import java.io.FileFilter;

//E:/java  .java
public class Fileter implements FileFilter{

    @Override
    public boolean accept(File pathname) {
        return false;
    }
}

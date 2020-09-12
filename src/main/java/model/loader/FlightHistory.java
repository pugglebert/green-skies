package model.loader;

import javafx.stage.FileChooser;
import model.data.Route;
import model.data.Storage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FlightHistory {
  private final List<Route> buffer;

  public FlightHistory(String fileDir) {
    this.buffer = processFile(fileDir);
  }

  public String getFileDir() {
    FileChooser fileChooser = new FileChooser(); // opens a file local file browser
    File selectedFile = fileChooser.showOpenDialog(null); // TODO add window, implement to open
    String fileDir = selectedFile.toString(); // file dir name
    return fileDir;
  }

  public List<Route> processFile(String fileDir) {
    String errorMessage;
    Storage storage = new Storage();
    Loader loader = new Loader(storage); // buffer storage

    try {
      errorMessage = loader.loadFile(fileDir, "Route");
      System.out.println(errorMessage);
      return storage.getRoutes();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return storage.getRoutes();
  }

  public void removeUnchecked() {
    List<Route> entriesToRemove = new ArrayList();
    for (Route route : buffer) {
      if (!route.getSelect().isSelected()) {
        entriesToRemove.add(route);
      }
    }
    buffer.removeAll(entriesToRemove);
  }

  public List<Route> getBuffer() {
    return this.buffer;
  }

//  public static void main(String[] args) {
//    FlightHistory test = new FlightHistory();
//  }
}
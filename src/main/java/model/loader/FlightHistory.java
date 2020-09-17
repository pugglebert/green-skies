package model.loader;

import javafx.stage.FileChooser;
import model.data.Route;
import model.data.Storage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

//TODO: write comment for this class

public class FlightHistory {
  //TODO: write comments for these attributes

  private final List<Route> buffer;
  //TODO: write comment for this method

  public FlightHistory(String fileDir) {
    this.buffer = processFile(fileDir);
  }
  //TODO: write comment for this method

  public String getFileDir() {
    FileChooser fileChooser = new FileChooser(); // opens a file local file browser
    File selectedFile = fileChooser.showOpenDialog(null); // TODO add window, implement to open
    String fileDir = selectedFile.toString(); // file dir name
    return fileDir;
  }
  //TODO: write comment for this method

  public List<Route> processFile(String fileDir) {
    String errorMessage;
    Storage storage = new Storage();
    Loader loader = new Loader(storage); // buffer storage

    try {
      errorMessage = loader.loadFile(fileDir, "Route");
      return storage.getRoutes();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return storage.getRoutes();
  }
  //TODO: write comment for this method

  public void removeUnchecked() {
    List<Route> entriesToRemove = new ArrayList();
    for (Route route : buffer) {
      if (!route.getSelect().isSelected()) {
        entriesToRemove.add(route);
      }
    }
    buffer.removeAll(entriesToRemove);
  }
  //TODO: write comment for this method

  public List<Route> getBuffer() {
    return this.buffer;
  }

  //TODO remove?

//  public static void main(String[] args) {
//    FlightHistory test = new FlightHistory();
//  }
}

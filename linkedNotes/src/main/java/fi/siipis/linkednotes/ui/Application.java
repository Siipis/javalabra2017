/**
 * Application
 *
 * Controller class.
 * Handles events and updates the view as needed.
 * May also be called by event handlers in the view layer.
 */
package fi.siipis.linkednotes.ui;

import fi.siipis.linkednotes.core.*;
import fi.siipis.linkednotes.data.*;
import java.util.Optional;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.*;

/**
 *
 * @author Amalia Surakka
 */
public class Application extends javafx.application.Application {

    private View view;

    private Navigator navigator;

    private Library library;

    private Parser parser;

    private FileHandler fileHandler;

    public Application() {
        this.navigator = Navigator.getInstance();

        this.library = Library.getInstance();

        this.parser = Parser.getInstance();

        this.fileHandler = FileHandler.getInstance();

        library.sync();
    }

    /**
     * JavaFX launch method
     *
     * @param stage Stage
     */
    @Override
    public void start(Stage stage) {
        this.view = new View(this, stage);
    }

    @FXML
    public void createDirectory() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Create directory");
        dialog.setHeaderText(null);
        dialog.setContentText("Directory name:");
        dialog.initStyle(StageStyle.UTILITY);

        Optional<String> result = dialog.showAndWait();

        result.ifPresent((name) -> {
            name = this.normaliseName(name);

            if (name.isEmpty()) {
                return;
            }

            String path = navigator.getRootPath();

            String storagePath = path + "/" + name;

            if (fileHandler.createDirectory(storagePath)) {
                navigator.open(storagePath);

                view.viewWelcome();
            }
        });
    }

    @FXML
    public void createArticle() {
        if (!navigator.currentIsRoot()) {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Create article");
            dialog.setHeaderText(null);
            dialog.setContentText("Article name:");
            dialog.initStyle(StageStyle.UTILITY);

            Optional<String> result = dialog.showAndWait();

            result.ifPresent((name) -> {
                name = this.normaliseName(name);

                if (name.isEmpty() && library.findArticle(name) != null) {
                    return;
                }

                String path = navigator.getCurrentPath();

                if (fileHandler.isFile(path)) {
                    path = navigator.parentPath();
                }

                String storagePath = path + "/" + name + ".txt";

                if (fileHandler.createFile(storagePath)) {
                    Article article = library.findArticle(storagePath);

                    this.editArticle(article);
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText(null);
            alert.setContentText("Cannot create an article in root!");
            alert.initStyle(StageStyle.UTILITY);

            alert.show();
        }
    }

    @FXML
    public void closeApplication() {
        Platform.exit();
    }

    @FXML
    public void readArticle(Article article) {
        library.setCurrentArticle(article);

        SplitMap splitMap = new SplitMap(article);

        view.viewReader(splitMap);
    }

    @FXML
    public void editArticle(Article article) {
        library.setCurrentArticle(article);

        view.viewEditor(article);
    }

    @FXML
    public void saveArticle(Article article) {
        String content = parser.toFile(article);

        if (fileHandler.writeFile(article.getFilepath(), content)) {
            article.touchSaved();

            library.sync();

            this.readArticle(article);
        }
    }

    @FXML
    public void renameArticle(Article article) {
        TextInputDialog dialog = new TextInputDialog(article.getPlainName());
        dialog.setTitle("Rename article");
        dialog.setHeaderText(null);
        dialog.setContentText("New name:");
        dialog.initStyle(StageStyle.UTILITY);

        Optional<String> result = dialog.showAndWait();

        result.ifPresent((newName) -> {
            newName = this.normaliseName(newName);

            if (newName.isEmpty() && library.findArticle(newName) != null) {
                return;
            }

            String oldPath = article.getFilepath();
            String plainName = article.getPlainName();

            int prefix = oldPath.lastIndexOf(plainName);

            String newPath = oldPath.substring(0, prefix) + newName + ".txt";

            if (fileHandler.renameFile(oldPath, newPath)) {
                view.updateNavBar();

                article.setFilepath(newPath);
            }
        });
    }

    @FXML
    public void renameDirectory(String path) {
        TextInputDialog dialog = new TextInputDialog(Utils.plainFileName(path));
        dialog.setTitle("Rename directory");
        dialog.setHeaderText(null);
        dialog.setContentText("New name:");
        dialog.initStyle(StageStyle.UTILITY);

        Optional<String> result = dialog.showAndWait();

        result.ifPresent((newName) -> {
            newName = this.normaliseName(newName);

            if (newName.isEmpty()) {
                return;
            }

            String newPath = Utils.canonisePath(newName);

            if (fileHandler.renameDirectory(path, newPath)) {
                view.updateNavBar();
            }
        });
    }

    @FXML
    public void deleteArticle(Article article) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + article.getPlainName() + "?");
        alert.setTitle("Are you sure?");
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();

        result.ifPresent((response) -> {
            if (response == ButtonType.OK && fileHandler.deleteFile(article.getFilepath())) {
                if (library.getCurrentArticle() != null && library.getCurrentArticle().equals(article)) {
                    if (navigator.getCurrentPath().equals(library.getCurrentArticle().getFilepath())) {
                        navigator.open("..");
                    }

                    library.setCurrentArticle(null);

                    view.viewWelcome();
                }

                view.updateNavBar();
            }
        });
    }

    @FXML
    public void deleteDirectory(String path) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete " + Utils.plainFileName(path) + "?");
        alert.setTitle("Are you sure?");
        alert.setHeaderText(null);
        alert.initStyle(StageStyle.UTILITY);

        Optional<ButtonType> result = alert.showAndWait();

        result.ifPresent((response) -> {
            if (response == ButtonType.OK) {
                if (fileHandler.deleteFile(path)) {
                    if (!fileHandler.fileExists(navigator.getCurrentPath())) {
                        navigator.open(".");

                        view.viewWelcome();
                    }

                    view.updateNavBar();
                }
            }
        });
    }

    /**
     * Normalise the file name for storing
     *
     * @param name File name
     * @return Escaped file name
     */
    private String normaliseName(String name) {
        if (name.contains(".")) {
            name = name.substring(0, name.indexOf("."));
        }

        name = name.replaceAll("[^a-zA-Z0-9_-]", "_");

        return name;
    }
}

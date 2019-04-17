package View;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;

public class Model {
    private String address
            = "127.0.0.1";
    private int port
            = 8888;

    private Document currentDocument;

    public Model() {
        updateCurrentDocument();
    }


    /**
     * Sets currentDocument as a current opened file.
     */
    private void updateCurrentDocument() {
        Project[] projects = ProjectManager.getInstance().getOpenProjects();

        currentDocument = FileEditorManager.getInstance(projects[0]).getSelectedTextEditor().getDocument();
    }

    /**
     * @return a current opened file name.
     */
    public String getCurrentFileName() {
        updateCurrentDocument();

        VirtualFile currentFile = FileDocumentManager.getInstance().getFile(currentDocument);

        return currentFile.getName();
    }

    /**
     * @return a current opened file contents.
     */
    public String getCurrentFileContents() {
        updateCurrentDocument();

        return currentDocument.getText();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return this.port;
    }
}

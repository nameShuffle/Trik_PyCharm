package View;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.content.*;

public class ViewFactory implements ToolWindowFactory {
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        View trikToolWindow = new View(toolWindow);
        Presenter presenter = new Presenter(trikToolWindow, new Model());
        trikToolWindow.setPresenter(presenter);
        presenter.initializeTable();

        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(trikToolWindow.getContent(), "", false);

        toolWindow.getContentManager().addContent(content);
    }
}

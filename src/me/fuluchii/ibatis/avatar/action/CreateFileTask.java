package me.fuluchii.ibatis.avatar.action;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import me.fuluchii.ibatis.avatar.model.TableModel;
import me.fuluchii.ibatis.avatar.service.FileCreateService;

/**
 * @author fuluchii.zhao
 */
public class CreateFileTask implements Runnable {
    private Project project;
    private TableModel tableModel;

    public CreateFileTask(Project project, TableModel tableModel) {
        this.project = project;
        this.tableModel = tableModel;
    }

    @Override
    public void run() {
        FileCreateService fileCreateService = ServiceManager.getService(project, FileCreateService.class);
        fileCreateService.createFileForTable(tableModel);
    }
}

package me.fuluchii.ibatis.avatar.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import me.fuluchii.ibatis.avatar.generator.JavaBeanGenerator;
import me.fuluchii.ibatis.avatar.model.TableModel;

/**
 * @author fuluchii.zhao
 */
public class FileCreateService {

    private Project project;

    public FileCreateService(Project project){
        this.project = project;
    }

    public static FileCreateService getInstance(Project project){
        return ServiceManager.getService(project,FileCreateService.class);
    }

    public void createFileForTable(TableModel model){
        JavaBeanGenerator generator = ServiceManager.getService(project,JavaBeanGenerator.class);

        generator.buildJavaClass(model);


    }
}

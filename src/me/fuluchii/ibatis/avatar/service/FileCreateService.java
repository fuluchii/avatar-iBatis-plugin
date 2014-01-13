package me.fuluchii.ibatis.avatar.service;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import me.fuluchii.ibatis.avatar.generator.*;
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
        JavaDtoGenerator dtoGenerator = ServiceManager.getService(project,JavaDtoGenerator.class);
        DaoXMLGenerator daoXMLGenerator = ServiceManager.getService(project,DaoXMLGenerator.class);
        DaoClassGenerator daoClassGenerator = ServiceManager.getService(project,DaoClassGenerator.class);
        generator.buildJavaClass(model,model.getBeanName());
        dtoGenerator.buildJavaClass(model,model.getDtoName());
        SqlmapXMLGenerator sqlmapXMLGenerator = ServiceManager.getService(project,SqlmapXMLGenerator.class);
        sqlmapXMLGenerator.createXMLFile("MybatisTemplate.xml",model);
        daoXMLGenerator.createXMLFile("AvatarDaoRegister.xml",model);
        daoClassGenerator.createClassFile(model);
    }
}

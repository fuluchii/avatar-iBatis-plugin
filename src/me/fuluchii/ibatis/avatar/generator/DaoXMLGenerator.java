package me.fuluchii.ibatis.avatar.generator;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiPackage;
import com.intellij.util.xml.DomManager;
import me.fuluchii.ibatis.avatar.model.TableModel;

import java.util.Properties;

/**
 * @author fuluchii.zhao
 */
public class DaoXMLGenerator {

    protected Project project;

    protected JavaPsiFacade javaPsiFacade;

    protected PsiElementFactory psiElementFactory;

    protected DomManager domManager;

    DaoXMLGenerator(Project project){
        this.project = project;
        javaPsiFacade = JavaPsiFacade.getInstance(project);
        psiElementFactory = PsiElementFactory.SERVICE.getInstance(project);
        domManager = DomManager.getDomManager(project);

    }


    public void createXMLFile(String templateName,TableModel tableModel) {
        try {
            Properties properties = new Properties();
            properties.put("NAMESPACE",tableModel.getClassName());
            properties.put("DaoName",tableModel.getDaoName());
            properties.put("DaoClass",tableModel.getBasePackage()+"."+tableModel.getDaoName());
            String fileName = tableModel.getDaoRName();
            PsiPackage targetPackage = javaPsiFacade.findPackage(tableModel.getBasePackage());
            FileTemplate fileTemplate = FileTemplateManager.getInstance().getJ2eeTemplate(templateName);
            FileTemplateUtil.createFromTemplate(fileTemplate, fileName, properties, targetPackage.getDirectories()[0]);

        } catch (Exception e) {

        }

    }
}

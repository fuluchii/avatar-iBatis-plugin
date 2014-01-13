package me.fuluchii.ibatis.avatar.generator;

import com.intellij.ide.fileTemplates.FileTemplate;
import com.intellij.ide.fileTemplates.FileTemplateManager;
import com.intellij.ide.fileTemplates.FileTemplateUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.codeStyle.CodeStyleManager;
import com.intellij.psi.xml.XmlFile;
import com.intellij.psi.xml.XmlTag;
import com.intellij.util.xml.DomManager;
import me.fuluchii.ibatis.avatar.model.TableModel;

import java.util.Properties;

/**
 * @author fuluchii.zhao
 */
public class SqlmapXMLGenerator {

    protected Project project;

    protected JavaPsiFacade javaPsiFacade;

    protected PsiElementFactory psiElementFactory;

    protected DomManager domManager;

    SqlmapXMLGenerator(Project project){
        this.project = project;
        javaPsiFacade = JavaPsiFacade.getInstance(project);
        psiElementFactory = PsiElementFactory.SERVICE.getInstance(project);
        domManager = DomManager.getDomManager(project);

    }

    public void createXMLFile(String templateName,TableModel tableModel) {
        try {
            Properties properties = new Properties();
            properties.put("NAMESPACE",tableModel.getClassName());
            String fileName = tableModel.getSqlmapName();
            PsiPackage targetPackage = javaPsiFacade.findPackage(tableModel.getBasePackage());
            FileTemplate fileTemplate = FileTemplateManager.getInstance().getJ2eeTemplate(templateName);
            PsiElement xmlFile = FileTemplateUtil.createFromTemplate(fileTemplate, fileName, properties, targetPackage.getDirectories()[0]);
            PsiFileFactory.getInstance(project);

            XmlFile file = (XmlFile)xmlFile.getContainingFile();
            domManager = DomManager.getDomManager(project);
            domManager.getDomElement(file.getRootTag());
            XmlElementFactory xmlElementFactory = XmlElementFactory.getInstance(project);
            XmlTag tag = xmlElementFactory.createTagFromText("<resultMap></resultMap>");
            tag.setAttribute("id",tableModel.getClassName()+"Result");
            tag.setAttribute("class",tableModel.getBasePackage()+"."+tableModel.getBeanName());

            for (String colname : tableModel.getFiledMap().keySet()) {
                XmlTag result = xmlElementFactory.createTagFromText("<result></result>");
                result.setAttribute("column",colname);
                result.setAttribute("property",colname);
                tag.addSubTag(result,true);
            }

            file.getRootTag().addSubTag(tag,true);
            CodeStyleManager.getInstance(project).reformat(xmlFile);

        } catch (Exception e) {

        }

    }



}

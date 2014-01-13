package me.fuluchii.ibatis.avatar.generator;

import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import me.fuluchii.ibatis.avatar.model.TableModel;

/**
 * @author fuluchii.zhao
 */
public class DaoClassGenerator {
    private Project project;

    private PsiElementFactory psiElementFactory;

    private PsiClass target;

    DaoClassGenerator(Project project){
        this.project = project;

    }

    public void createClassFile(TableModel tableModel) {
        JavaPsiFacade javaPsiFacade = JavaPsiFacade.getInstance(project);

        PsiPackage targetPackage = javaPsiFacade.findPackage(tableModel.getBasePackage());
        psiElementFactory = PsiElementFactory.SERVICE.getInstance(project);
        target = JavaDirectoryService.getInstance().createClass(targetPackage.getDirectories()[0],tableModel.getDaoName());
    }
}
